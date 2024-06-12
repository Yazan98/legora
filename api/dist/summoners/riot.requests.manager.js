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
                championsList.push(value);
            }
        });
        return Promise.resolve(championsList);
    }
    static isRequestSuccess(status) {
        return status >= 200 && status < 300;
    }
}
//# sourceMappingURL=riot.requests.manager.js.map