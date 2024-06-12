import axios, {AxiosHeaders, AxiosRequestHeaders, RawAxiosRequestHeaders} from "axios";
import {TOKEN} from "../config/config.js";
import {ChampionModel, ChampionObject} from "../response/riot/champion.model.js";

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

    static isRequestSuccess(status: number): boolean {
        return status >= 200 && status < 300
    }

}