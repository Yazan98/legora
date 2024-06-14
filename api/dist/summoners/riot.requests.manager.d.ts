import { AxiosHeaders } from "axios";
import { ChampionObject } from "../response/riot/champion.model.js";
import { TftAugment } from "../response/custom/tft.augment.js";
import { TftChampionInfo, TftItemInfo } from "../response/riot/tft.match.info.js";
export declare class RiotRequestsManager {
    static getRequestHeader(): AxiosHeaders;
    static getCurrentAppVersion(): Promise<string>;
    static getApplicationChampions(version: string): Promise<Array<ChampionObject>>;
    static getTftAugments(): Promise<Array<TftAugment>>;
    static getTftChampions(): Promise<Array<TftChampionInfo>>;
    static getTftItems(): Promise<Array<TftItemInfo>>;
    static isRequestSuccess(status: number): boolean;
}
