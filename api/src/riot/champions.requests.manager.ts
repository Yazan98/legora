import axios from "axios";
import {imagesVersion} from "../app.js";
import {RiotChampionCoverResponse} from "../response/riot/riot.champion.cover.response.js";
import {FreeChampionsResponse} from "../response/riot/free.champions.response.js";
import {RiotRequestsManager} from "./riot.requests.manager.js";
import {RiotChampionInstance, RiotChampionResponse} from "../response/riot/riot.champion.response..js";

export class ChampionsRequestsManager {

    static async getChampionInfoByName(name: string): Promise<RiotChampionInstance> {
        let info: RiotChampionInstance = null;
        await axios.get<RiotChampionResponse>(`https://ddragon.leagueoflegends.com/cdn/${imagesVersion}/data/en_US/champion/${name}.json`)
            .then((result) => {
                for (const [key, value] of Object.entries(result.data.data)) {
                    info = value;
                }
            })

        return Promise.resolve(info)
    }

    static async getChampionCoverImage(championKey: string): Promise<string> {
        let championCover = "";
        await axios.get<RiotChampionCoverResponse>(`https://ddragon.leagueoflegends.com/cdn/${imagesVersion}/data/en_US/champion/${championKey}.json`)
            .then((result) => {
                for (const [key, value] of Object.entries(result.data.data)) {
                    championCover = `https://ddragon.leagueoflegends.com/cdn/img/champion/splash/${championKey}_${value.skins[0].num}.jpg`
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

    static getChampionIcon(championIcon: string): string {
        const isLinkForcedToLowerCase = championIcon.includes("'");
        const imageToLoad = championIcon
            .replace(".", "")
            .replace("'", "")
            .replace("&", "")

        // Extract the first character and convert it to uppercase.
        const firstChar = imageToLoad.charAt(0).toUpperCase();

        // Extract the rest of the string.
        const restOfString = imageToLoad.slice(1);

        // Concatenate the first character (capitalized) with the rest of the string.
        const image = firstChar + restOfString.trim().replace(" ", "");
        if (isLinkForcedToLowerCase) {
            const imagePath = image.toLowerCase();
            const firstChar = imagePath.charAt(0).toUpperCase();
            const restOfString = imagePath.slice(1);
            return firstChar + restOfString.trim().replace(" ", "");
        } else {
            return image;
        }
    }

}