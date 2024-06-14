import { RiotBaseController } from "./riot.base.controller.js";
import { ChampionsControllerImpl } from "./impl/champions.controller.impl.js";
import { Express } from "express";
import { ChampionModel } from "../response/custom/champion.model.js";
import { ChampionInfoResponse } from "../response/custom/champion.info.response.js";
export declare class ChampionsController extends RiotBaseController implements ChampionsControllerImpl {
    private service;
    initControllerRoutes(app: Express): void;
    getControllerUrl(): string;
    getChampionInfoByName(key: string): Promise<ChampionInfoResponse>;
    getTftChampionsList(): Promise<Array<ChampionModel>>;
    getChampionsList(userId: number): Promise<Array<ChampionModel>>;
}
