import axios from "axios";
import { RiotRequestsManager } from "./riot.requests.manager.js";
export class MatchManager {
    static async isLolMatchesFound(region, accountId) {
        let isMatchesFound = false;
        await axios.get(`https://${region}.api.riotgames.com/lol/match/v5/matches/by-puuid/${accountId}/ids?start=0&count=2`, {
            headers: RiotRequestsManager.getRequestHeader()
        }).then(result => {
            if (RiotRequestsManager.isRequestSuccess(result.status)) {
                isMatchesFound = result.data.length > 1;
            }
        });
        return Promise.resolve(isMatchesFound);
    }
    static async isTftMatchesFound(region, accountId) {
        let isMatchesFound = false;
        await axios.get(`https://${region}.api.riotgames.com/tft/match/v1/matches/by-puuid/${accountId}/ids?start=0&count=20`, {
            headers: RiotRequestsManager.getRequestHeader()
        }).then(result => {
            if (RiotRequestsManager.isRequestSuccess(result.status)) {
                isMatchesFound = result.data.length > 1;
            }
        });
        return Promise.resolve(isMatchesFound);
    }
}
//# sourceMappingURL=match.manager.js.map