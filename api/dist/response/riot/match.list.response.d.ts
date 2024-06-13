export interface MatchListResponse {
    matchId: string;
    info: RiotMatchInfo;
}
export interface RiotMatchInfo {
    gameCreation: number;
    gameDuration: number;
    gameMode: string;
    participants: Array<RiotMatchPlayer>;
}
export interface RiotMatchPlayer {
    kills: number;
    deaths: number;
    assists: number;
    item0: number;
    item1: number;
    item2: number;
    item3: number;
    item4: number;
    item5: number;
    totalMinionsKilled: number;
    goldEarned: number;
    puuid: string;
    win: boolean;
    championId: number;
}
