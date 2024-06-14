import { SummonerProfile } from "../response/riot/summoner.profile.js";
import { ProfileRankInfo } from "../response/custom/profile.info.response.js";
export declare class SummonerAccountsManager {
    static getSummonerProfileByInfo(summonerName: string, region: string, serverCode: string): Promise<SummonerProfile>;
    static getTopMasteryChampionsIcons(serverCode: string, accountId: string): Promise<Array<ProfileRankInfo>>;
    static getMasteryPointsByAccountId(serverCode: string, accountId: string): Promise<number>;
}
