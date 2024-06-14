import { RiotBaseController } from "./riot.base.controller.js";
import { HomeControllerImpl } from "./impl/home.controller.impl.js";
import { HomeWidget } from "../home/home.widget.js";
import { Express } from "express";
export declare class HomeController extends RiotBaseController implements HomeControllerImpl {
    private homeService;
    initControllerRoutes(app: Express): void;
    getControllerUrl(): string;
    getHomeFeedTree(userId: number): Promise<Array<HomeWidget>>;
}
