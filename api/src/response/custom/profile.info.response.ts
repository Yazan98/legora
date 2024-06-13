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
        coverImage: string,
        summonerHighlightName: string,
        serverHighlightName: string,
        isLolMatchesFound: Boolean,
        isTftMatchesFound: Boolean,
        topChampionsMastery: Array<string>,
        widgets: Array<ProfileWidget>,
    }
}

export type ProfileWidget = {
    name: string,
    image: string,
    link: string,
};

export interface ProfileRankInfo {
    name: string,
    icon: string,
    id: number,
    level: number,
    points: number,
}
