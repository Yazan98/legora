import {ChampionModel} from "../../response/custom/champion.model.js";

export interface ChampionsControllerImpl {
    getChampionsList(userId: number): Promise<Array<ChampionModel>>
    getTftChampionsList(): Promise<Array<ChampionModel>>
}