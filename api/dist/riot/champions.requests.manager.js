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
                championCover = `https://ddragon.leagueoflegends.com/cdn/img/champion/splash/${championKey}_${value.skins[0].num}.jpg`;
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
    static getChampionIcon(championIcon) {
        const isLinkForcedToLowerCase = championIcon.includes("'");
        const imageToLoad = championIcon
            .replace(".", "")
            .replace("'", "")
            .replace("&", "");
        const firstChar = imageToLoad.charAt(0).toUpperCase();
        const restOfString = imageToLoad.slice(1);
        const image = firstChar + restOfString.trim().replace(" ", "");
        if (isLinkForcedToLowerCase) {
            const imagePath = image.toLowerCase();
            const firstChar = imagePath.charAt(0).toUpperCase();
            const restOfString = imagePath.slice(1);
            return firstChar + restOfString.trim().replace(" ", "");
        }
        else {
            return image;
        }
    }
}
//# sourceMappingURL=champions.requests.manager.js.map