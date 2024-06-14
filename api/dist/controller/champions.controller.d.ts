import { RiotBaseController } from "./riot.base.controller.js";
import { ChampionsControllerImpl } from "./impl/champions.controller.impl.js";
import { Express } from "express";
import { ChampionModel } from "../response/custom/champion.model.js";
export declare class ChampionsController extends RiotBaseController implements ChampionsControllerImpl {
    private service;
    initControllerRoutes(app: Express): void;
    getControllerUrl(): string;
    getTftChampionsList(): Promise<Array<ChampionModel>>;
    getChampionsList(userId: number): Promise<Array<ChampionModel>>;
}
