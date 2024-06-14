import axios, {AxiosHeaders, AxiosRequestHeaders, RawAxiosRequestHeaders} from "axios";
import {TOKEN} from "../config/config.js";
import {ChampionModel, ChampionObject} from "../response/riot/champion.model.js";
import {TftAugment} from "../response/custom/tft.augment.js";
import {TftAugmentBody, TftAugmentsResponse} from "../response/riot/tft.augments.response.js";
import {TftChampion, TftChampionInfo, TftChampionsResponse, TftItemInfo} from "../response/riot/tft.match.info.js";

export class RiotRequestsManager {

    static getRequestHeader(): AxiosHeaders {
        let headers: RawAxiosRequestHeaders & AxiosHeaders;
        // @ts-ignore
        headers = {
            'X-Riot-Token': TOKEN,
            'User-Agent': 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko)',
            'Accept-Charset': 'application/x-www-form-urlencoded; charset=UTF-8',
            'Origin': 'https://developer.riotgames.com',
            'Content-Type': 'application/json',
            crossDomain: true,
        } as AxiosRequestHeaders;

        return headers
    }

    static async getCurrentAppVersion(): Promise<string> {
        let appVersion = "14.11.1";
        await axios.get<Array<string>>("https://ddragon.leagueoflegends.com/api/versions.json")
            .then((result) => {
                appVersion = result.data[0];
            })

        return Promise.resolve(appVersion);
    }

    static async getApplicationChampions(version: string): Promise<Array<ChampionObject>> {
        let championsList = new Array<ChampionObject>()
        await axios.get<ChampionModel>(`https://ddragon.leagueoflegends.com/cdn/${version}/data/en_US/champion.json`)
            .then((result) => {
                const champions: Map<string, ChampionObject> = result.data.data;
                for (const [key, value] of Object.entries(champions)) {
                    championsList.push(value);
                }
            })

        return Promise.resolve(championsList)
    }

    static async getTftAugments(): Promise<Array<TftAugment>> {
        let tftAugmentsList = new Array<TftAugment>()
        await axios.get<TftAugmentsResponse>(`https://ddragon.leagueoflegends.com/cdn/14.12.1/data/en_US/tft-augments.json`)
            .then((result) => {
                const augments: Map<string, TftAugmentBody> = result.data.data;
                for (const [key, value] of Object.entries(augments)) {
                    tftAugmentsList.push({
                        id: key,
                        name: value.name,
                        image: `https://ddragon.leagueoflegends.com/cdn/14.12.1/img/tft-augment/${value.image.full}`
                    });
                }
            })

        return Promise.resolve(tftAugmentsList)
    }

    static async getTftChampions(): Promise<Array<TftChampionInfo>> {
        let tftChampionsList = new Array<TftChampionInfo>()
        await axios.get<TftChampionsResponse>(`https://ddragon.leagueoflegends.com/cdn/14.12.1/data/en_US/tft-champion.json`)
            .then((result) => {
                const augments: Map<string, TftAugmentBody> = result.data.data;
                for (const [key, value] of Object.entries(augments)) {
                    tftChampionsList.push({
                        id: value.id,
                        name: value.name,
                        image: `https://ddragon.leagueoflegends.com/cdn/14.12.1/img/tft-champion/${value.image.full}`
                    });
                }
            })

        return Promise.resolve(tftChampionsList)
    }

    static async getTftItems(): Promise<Array<TftItemInfo>> {
        let tftChampionsList = new Array<TftChampionInfo>()
        await axios.get<TftChampionsResponse>(`https://ddragon.leagueoflegends.com/cdn/14.12.1/data/en_US/tft-item.json`)
            .then((result) => {
                const augments: Map<string, TftAugmentBody> = result.data.data;
                for (const [key, value] of Object.entries(augments)) {
                    tftChampionsList.push({
                        id: key,
                        name: value.name,
                        image: `https://ddragon.leagueoflegends.com/cdn/14.12.1/img/tft-item/${value.image.full}`
                    });
                }
            })

        return Promise.resolve(tftChampionsList)
    }

    static isRequestSuccess(status: number): boolean {
        return status >= 200 && status < 300
    }

}