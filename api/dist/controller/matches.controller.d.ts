import { RiotBaseController } from "./riot.base.controller.js";
import { MatchesControllerImpl } from "./impl/matches.controller.impl.js";
import { Express } from "express";
import { LolMatchesResponse } from "../response/custom/lol.matches.response.js";
export declare class MatchesController extends RiotBaseController implements MatchesControllerImpl {
    private service;
    initControllerRoutes(app: Express): void;
    getControllerUrl(): string;
    getLeagueOfLegendsMatchesByUserId(userId: number): Promise<Array<LolMatchesResponse>>;
}