import { AxiosHeaders } from "axios";
import { ChampionObject } from "../response/riot/champion.model.js";
export declare class RiotRequestsManager {
    static getRequestHeader(): AxiosHeaders;
    static getCurrentAppVersion(): Promise<string>;
    static getApplicationChampions(version: string): Promise<Array<ChampionObject>>;
    static isRequestSuccess(status: number): boolean;
}
