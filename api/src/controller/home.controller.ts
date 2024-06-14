import {RiotBaseController} from "./riot.base.controller.js";
import {HomeControllerImpl} from "./impl/home.controller.impl.js";
import {HomeWidget} from "../home/home.widget.js";
import {Express} from "express";
import {RiotResponseGenerator} from "../response/riot.response.generator.js";
import {HomeService} from "../service/home.service.js";

export class HomeController extends RiotBaseController implements HomeControllerImpl {

    private homeService = new HomeService();

    initControllerRoutes(app: Express) {
        this.onRegisterGetRequest(app, this.getControllerUrl() + '/feed', (request, response) => {
            this.getHomeFeedTree(this.getUserId(request))
                .then((result) => {
                    RiotResponseGenerator.onSendSuccessResponse(true, "Feed Found Successfully!", result, response);
                })
                .catch((ex) => {
                    RiotResponseGenerator.onSendErrorResponse(ex.message, RiotResponseGenerator.BAD_REQUEST_CODE, response);
                })
        })
    }

    getControllerUrl(): string {
        return this.getControllerPrefixUrl() + "home";
    }

    async getHomeFeedTree(userId: number): Promise<Array<HomeWidget>> {
        return await this.homeService.getHomeFeedTree(userId);
    }

}