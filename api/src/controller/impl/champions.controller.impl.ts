import {ChampionModel} from "../../response/custom/champion.model.js";
import {ChampionInfoResponse} from "../../response/custom/champion.info.response.js";

export interface ChampionsControllerImpl {

    getChampionsList(userId: number): Promise<Array<ChampionModel>>;

    getTftChampionsList(): Promise<Array<ChampionModel>>;

    getChampionInfoByName(key: string): Promise<ChampionInfoResponse>;

}
