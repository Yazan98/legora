import {SummonerProfile} from "../response/riot/summoner.profile.js";
import axios from "axios";
import {RiotRequestsManager} from "./riot.requests.manager.js";
import {SummonerIdsResponse} from "../response/riot/summoner.ids.response.js";
import {ProfileRankInfo} from "../response/custom/profile.info.response.js";
import {MasteryChampionTopItemResponse} from "../response/riot/mastery.champion.top.item.response.js";
import {championsList, imagesVersion} from "../app.js";

export class SummonerAccountsManager {

    public static async getSummonerProfileByInfo(summonerName: string, region: string, serverCode: string): Promise<SummonerProfile> {
        const accountTagline = summonerName.replace("#", "/")
        let accountId = "";
        let profileName: string;
        let summonerInfo: SummonerProfile = null;
        await axios.get<SummonerIdsResponse>(`https://${region}.api.riotgames.com/riot/account/v1/accounts/by-riot-id/${accountTagline}`, {
            headers: RiotRequestsManager.getRequestHeader()
        }).then(result => {
            if (RiotRequestsManager.isRequestSuccess(result.status)) {
                accountId = result.data.puuid;
                profileName = result.data.gameName;
            }
        })

        if (!accountId) {
            return Promise.reject("Summoner Name Not Found");
        }

        await axios.get<SummonerProfile>(`https://${serverCode}.api.riotgames.com/lol/summoner/v4/summoners/by-puuid/${accountId}`, {
            headers: RiotRequestsManager.getRequestHeader()
        }).then(result => {
            if (RiotRequestsManager.isRequestSuccess(result.status)) {
                summonerInfo = result.data;
            }
        }).catch((ex) => {
            console.error(ex)
            return Promise.reject(ex);
        })

        summonerInfo.name = profileName;
        return Promise.resolve(summonerInfo);
    }

    static async getTopMasteryChampionsIcons(serverCode: string, accountId: string): Promise<Array<ProfileRankInfo>> {
        const masteryChampions = new Array<ProfileRankInfo>();
        await axios.get<Array<MasteryChampionTopItemResponse>>(`https://${serverCode}.api.riotgames.com/lol/champion-mastery/v4/champion-masteries/by-puuid/${accountId}/top?count=6`, {
            headers: RiotRequestsManager.getRequestHeader()
        }).then(result => {
            if (RiotRequestsManager.isRequestSuccess(result.status)) {
                for (let i = 0; i < result.data.length; i++) {
                    const masteryRecord = result.data[i];
                    const championInstance = championsList.filter((value) => {
                        return value.key === `${masteryRecord.championId}`
                    })

                    masteryChampions.push({
                        icon: `https://ddragon.leagueoflegends.com/cdn/${imagesVersion}/img/champion/${championInstance[0].name.replace(" ", "")}.png`,
                        name: championInstance[0].name,
                        id: masteryRecord.championId,
                        level: masteryRecord.championLevel
                    })
                }
                result.data
            }
        }).catch((ex) => {
            console.error(ex)
            return Promise.reject(ex);
        })

        return Promise.resolve(masteryChampions);
    }

    static async getMasteryPointsByAccountId(serverCode: string, accountId: string): Promise<number> {
        let scoreNumber = 0;
        await axios.get<number>(`https://${serverCode}.api.riotgames.com/lol/champion-mastery/v4/scores/by-puuid/${accountId}`, {
            headers: RiotRequestsManager.getRequestHeader()
        }).then(result => {
            if (RiotRequestsManager.isRequestSuccess(result.status)) {
                scoreNumber = result.data;
            }
        }).catch((ex) => {
            console.error(ex)
            return Promise.reject(ex);
        })

        return Promise.resolve(scoreNumber);
    }

}
