import axios from "axios";
import { TOKEN } from "../config/config.js";
export class RiotRequestsManager {
    static getRequestHeader() {
        let headers;
        headers = {
            'X-Riot-Token': TOKEN,
            'User-Agent': 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko)',
            'Accept-Charset': 'application/x-www-form-urlencoded; charset=UTF-8',
            'Origin': 'https://developer.riotgames.com',
            'Content-Type': 'application/json',
            crossDomain: true,
        };
        return headers;
    }
    static async getCurrentAppVersion() {
        let appVersion = "14.11.1";
        await axios.get("https://ddragon.leagueoflegends.com/api/versions.json")
            .then((result) => {
            appVersion = result.data[0];
        });
        return Promise.resolve(appVersion);
    }
    static async getApplicationChampions(version) {
        let championsList = new Array();
        await axios.get(`https://ddragon.leagueoflegends.com/cdn/${version}/data/en_US/champion.json`)
            .then((result) => {
            const champions = result.data.data;
            for (const [key, value] of Object.entries(champions)) {
                if (key.includes("Blanc") || key.includes("Nunu") || key.includes("Sante")) {
                    continue;
                }
                championsList.push(value);
            }
        });
        return Promise.resolve(championsList);
    }
    static async getTftAugments() {
        let tftAugmentsList = new Array();
        await axios.get(`https://ddragon.leagueoflegends.com/cdn/14.12.1/data/en_US/tft-augments.json`)
            .then((result) => {
            const augments = result.data.data;
            for (const [key, value] of Object.entries(augments)) {
                tftAugmentsList.push({
                    id: key,
                    name: value.name,
                    image: `https://ddragon.leagueoflegends.com/cdn/14.12.1/img/tft-augment/${value.image.full}`
                });
            }
        });
        return Promise.resolve(tftAugmentsList);
    }
    static async getTftChampions() {
        let tftChampionsList = new Array();
        await axios.get(`https://ddragon.leagueoflegends.com/cdn/14.12.1/data/en_US/tft-champion.json`)
            .then((result) => {
            const augments = result.data.data;
            for (const [key, value] of Object.entries(augments)) {
                tftChampionsList.push({
                    id: value.id,
                    name: value.name,
                    image: `https://ddragon.leagueoflegends.com/cdn/14.12.1/img/tft-champion/${value.image.full}`
                });
            }
        });
        return Promise.resolve(tftChampionsList);
    }
    static async getTftItems() {
        let tftChampionsList = new Array();
        await axios.get(`https://ddragon.leagueoflegends.com/cdn/14.12.1/data/en_US/tft-item.json`)
            .then((result) => {
            const augments = result.data.data;
            for (const [key, value] of Object.entries(augments)) {
                tftChampionsList.push({
                    id: key,
                    name: value.name,
                    image: `https://ddragon.leagueoflegends.com/cdn/14.12.1/img/tft-item/${value.image.full}`
                });
            }
        });
        return Promise.resolve(tftChampionsList);
    }
    static isRequestSuccess(status) {
        return status >= 200 && status < 300;
    }
}
//# sourceMappingURL=riot.requests.manager.js.map