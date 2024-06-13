import { LolMatchesResponse } from "../response/custom/lol.matches.response.js";
export declare class MatchesService {
    private userRepository;
    getLeagueOfLegendsMatchesByUserId(userId: number): Promise<Array<LolMatchesResponse>>;
}
