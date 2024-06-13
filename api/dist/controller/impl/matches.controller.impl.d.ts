import { LolMatchesResponse } from "../../response/custom/lol.matches.response.js";
export interface MatchesControllerImpl {
    getLeagueOfLegendsMatchesByUserId(userId: number): Promise<Array<LolMatchesResponse>>;
}
