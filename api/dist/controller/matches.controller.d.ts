import { RiotBaseController } from "./riot.base.controller.js";
import { MatchesControllerImpl } from "./impl/matches.controller.impl.js";
import { Express } from "express";
import { LolMatchesResponse, LolMatchInfo } from "../response/custom/lol.matches.response.js";
import { TftMatchesResponse } from "../response/custom/tft.matches.response.js";
export declare class MatchesController extends RiotBaseController implements MatchesControllerImpl {
    private service;
    initControllerRoutes(app: Express): void;
    getControllerUrl(): string;
    getLeagueOfLegendsMatchInfoById(userId: number, id: string): Promise<LolMatchInfo>;
    getTftMatchesByUserId(userId: number): Promise<Array<TftMatchesResponse>>;
    getLeagueOfLegendsMatchesByUserId(userId: number): Promise<Array<LolMatchesResponse>>;
}
