import { LolMatchesResponse, LolMatchInfo } from "../../response/custom/lol.matches.response.js";
import { TftMatchesResponse } from "../../response/custom/tft.matches.response.js";
export interface MatchesControllerImpl {
    getLeagueOfLegendsMatchesByUserId(userId: number): Promise<Array<LolMatchesResponse>>;
    getLeagueOfLegendsMatchInfoById(userId: number, id: string): Promise<LolMatchInfo>;
    getTftMatchesByUserId(userId: number): Promise<Array<TftMatchesResponse>>;
}
