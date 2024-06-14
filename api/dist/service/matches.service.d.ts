import { LolMatchesResponse, LolMatchInfo } from "../response/custom/lol.matches.response.js";
import { TftMatchesResponse } from "../response/custom/tft.matches.response.js";
export declare class MatchesService {
    private userRepository;
    getLeagueOfLegendsMatchesByUserId(userId: number): Promise<Array<LolMatchesResponse>>;
    getTftMatchesByUserId(userId: number): Promise<Array<TftMatchesResponse>>;
    getLeagueOfLegendsMatchInfoById(userId: number, matchId: string): Promise<LolMatchInfo>;
}
