import axios from "axios";
import {imagesVersion} from "../app.js";
import {RiotChampionCoverResponse} from "../response/riot/riot.champion.cover.response.js";
import {FreeChampionsResponse} from "../response/riot/free.champions.response.js";
import {RiotRequestsManager} from "./riot.requests.manager.js";

export class ChampionsRequestsManager {

    static async getChampionCoverImage(championKey: string): Promise<string> {
        let championCover = "";
        await axios.get<RiotChampionCoverResponse>(`https://ddragon.leagueoflegends.com/cdn/${imagesVersion}/data/en_US/champion/${championKey}.json`)
            .then((result) => {
                for (const [key, value] of Object.entries(result.data.data)) {
                    championCover = `http://ddragon.leagueoflegends.com/cdn/img/champion/splash/${championKey}_${value.skins[0].num}.jpg`
                }
            })

        return Promise.resolve(championCover)
    }

    static async getFreeChampionIds(serverCode: string): Promise<Array<number>> {
        const resultsToReturn = new Array<number>()
        await axios.get<FreeChampionsResponse>(`https://${serverCode}.api.riotgames.com/lol/platform/v3/champion-rotations`, {
            headers: RiotRequestsManager.getRequestHeader()
        })
            .then((result) => {
                const ids = result.data.freeChampionIds;
                for (let i = 0; i < ids.length; i++) {
                    resultsToReturn.push(ids[i]);
                }
            })

        return Promise.resolve(resultsToReturn);
    }
}