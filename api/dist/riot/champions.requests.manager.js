import axios from "axios";
import { imagesVersion } from "../app.js";
import { RiotRequestsManager } from "./riot.requests.manager.js";
export class ChampionsRequestsManager {
    static async getChampionInfoByName(name) {
        let info = null;
        await axios.get(`https://ddragon.leagueoflegends.com/cdn/${imagesVersion}/data/en_US/champion/${name}.json`)
            .then((result) => {
            for (const [key, value] of Object.entries(result.data.data)) {
                info = value;
            }
        });
        return Promise.resolve(info);
    }
    static async getChampionCoverImage(championKey) {
        let championCover = "";
        await axios.get(`https://ddragon.leagueoflegends.com/cdn/${imagesVersion}/data/en_US/champion/${championKey}.json`)
            .then((result) => {
            for (const [key, value] of Object.entries(result.data.data)) {
                championCover = `http://ddragon.leagueoflegends.com/cdn/img/champion/splash/${championKey}_${value.skins[0].num}.jpg`;
            }
        });
        return Promise.resolve(championCover);
    }
    static async getFreeChampionIds(serverCode) {
        const resultsToReturn = new Array();
        await axios.get(`https://${serverCode}.api.riotgames.com/lol/platform/v3/champion-rotations`, {
            headers: RiotRequestsManager.getRequestHeader()
        })
            .then((result) => {
            const ids = result.data.freeChampionIds;
            for (let i = 0; i < ids.length; i++) {
                resultsToReturn.push(ids[i]);
            }
        });
        return Promise.resolve(resultsToReturn);
    }
}
//# sourceMappingURL=champions.requests.manager.js.map