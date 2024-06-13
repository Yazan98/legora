import axios from "axios";
import { RiotRequestsManager } from "./riot.requests.manager.js";
import { championsList, imagesVersion } from "../app.js";
export class SummonerAccountsManager {
    static async getSummonerProfileByInfo(summonerName, region, serverCode) {
        const accountTagline = summonerName.replace("#", "/");
        let accountId = "";
        let profileName;
        let summonerInfo = null;
        await axios.get(`https://${region}.api.riotgames.com/riot/account/v1/accounts/by-riot-id/${accountTagline}`, {
            headers: RiotRequestsManager.getRequestHeader()
        }).then(result => {
            if (RiotRequestsManager.isRequestSuccess(result.status)) {
                accountId = result.data.puuid;
                profileName = result.data.gameName;
            }
        });
        if (!accountId) {
            return Promise.reject("Summoner Name Not Found");
        }
        await axios.get(`https://${serverCode}.api.riotgames.com/lol/summoner/v4/summoners/by-puuid/${accountId}`, {
            headers: RiotRequestsManager.getRequestHeader()
        }).then(result => {
            if (RiotRequestsManager.isRequestSuccess(result.status)) {
                summonerInfo = result.data;
            }
        }).catch((ex) => {
            console.error(ex);
            return Promise.reject(ex);
        });
        summonerInfo.name = profileName;
        return Promise.resolve(summonerInfo);
    }
    static async getTopMasteryChampionsIcons(serverCode, accountId) {
        const masteryChampions = new Array();
        await axios.get(`https://${serverCode}.api.riotgames.com/lol/champion-mastery/v4/champion-masteries/by-puuid/${accountId}/top?count=6`, {
            headers: RiotRequestsManager.getRequestHeader()
        }).then(result => {
            if (RiotRequestsManager.isRequestSuccess(result.status)) {
                for (let i = 0; i < result.data.length; i++) {
                    const masteryRecord = result.data[i];
                    const championInstance = championsList.filter((value) => {
                        return value.key === `${masteryRecord.championId}`;
                    });
                    masteryChampions.push({
                        icon: `https://ddragon.leagueoflegends.com/cdn/${imagesVersion}/img/champion/${championInstance[0].name.replace(" ", "")}.png`,
                        name: championInstance[0].name,
                        id: masteryRecord.championId,
                        level: masteryRecord.championLevel,
                        points: masteryRecord.championPoints,
                    });
                }
                result.data;
            }
        }).catch((ex) => {
            console.error(ex);
            return Promise.reject(ex);
        });
        return Promise.resolve(masteryChampions);
    }
    static async getMasteryPointsByAccountId(serverCode, accountId) {
        let scoreNumber = 0;
        await axios.get(`https://${serverCode}.api.riotgames.com/lol/champion-mastery/v4/scores/by-puuid/${accountId}`, {
            headers: RiotRequestsManager.getRequestHeader()
        }).then(result => {
            if (RiotRequestsManager.isRequestSuccess(result.status)) {
                scoreNumber = result.data;
            }
        }).catch((ex) => {
            console.error(ex);
            return Promise.reject(ex);
        });
        return Promise.resolve(scoreNumber);
    }
}
//# sourceMappingURL=summoner.accounts.manager.js.map