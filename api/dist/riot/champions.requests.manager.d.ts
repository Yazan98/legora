export declare class ChampionsRequestsManager {
    static getChampionCoverImage(championKey: string): Promise<string>;
    static getFreeChampionIds(serverCode: string): Promise<Array<number>>;
}
