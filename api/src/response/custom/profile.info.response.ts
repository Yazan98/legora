import {UserModel} from "../../models/user.model.js";

export interface ProfileInfoResponse {
    user: UserModel,
    summonerInfo: {
        level: number,
        masteryPoints: number,
        name: string,
        accountId: string,
        accountHash: string,
        profileImage: string,
        ranked: Array<ProfileRankInfo>,
        topChampionsMastery: Array<string>
    }
}

export interface ProfileRankInfo {
    name: string,
    icon: string,
    id: number,
    level: number
}
