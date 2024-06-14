import { LolMatchesResponse } from "../response/custom/lol.matches.response.js";
import { TftMatchesResponse } from "../response/custom/tft.matches.response.js";
export declare class MatchManager {
    static isLolMatchesFound(region: string, accountId: string): Promise<boolean>;
    static getLolMatchesIds(region: string, accountId: string): Promise<Array<string>>;
    static getTftMatchesIds(region: string, accountId: string): Promise<Array<string>>;
    static isTftMatchesFound(region: string, accountId: string): Promise<boolean>;
    static getTftMatchesByIds(matchesIds: Array<string>, region: string, summonerId: string): Promise<Array<TftMatchesResponse>>;
    static getMatchesByIds(matchesIds: Array<string>, region: string, summonerId: string): Promise<Array<LolMatchesResponse>>;
    static delay(ms: number): Promise<void>;
    static formatTimestampToDate(timestamp: number): string;
    static formatMinutesToMinutesAndSeconds(minutes: number): string;
}
