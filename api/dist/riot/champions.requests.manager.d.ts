import { RiotChampionInstance } from "../response/riot/riot.champion.response..js";
export declare class ChampionsRequestsManager {
    static getChampionInfoByName(name: string): Promise<RiotChampionInstance>;
    static getChampionCoverImage(championKey: string): Promise<string>;
    static getFreeChampionIds(serverCode: string): Promise<Array<number>>;
}
