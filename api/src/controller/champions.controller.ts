import {RiotBaseController} from "./riot.base.controller.js";
import {ChampionsControllerImpl} from "./impl/champions.controller.impl.js";
import {Express} from "express";
import {ChampionModel} from "../response/custom/champion.model.js";
import {RiotResponseGenerator} from "../response/riot.response.generator.js";
import {ChampionsService} from "../service/champions.service.js";

export class ChampionsController extends RiotBaseController implements ChampionsControllerImpl {

    private service = new ChampionsService();

    initControllerRoutes(app: Express) {
        this.onRegisterGetRequest(app, this.getControllerUrl() + '/', (request, response) => {
            this.getChampionsList(this.getUserId(request))
                .then((result) => {
                    RiotResponseGenerator.onSendSuccessResponse(true, "Champions Found Successfully!", result, response);
                })
                .catch((ex) => {
                    RiotResponseGenerator.onSendErrorResponse(ex.message, RiotResponseGenerator.BAD_REQUEST_CODE, response);
                })
        })
    }

    getControllerUrl(): string {
        return this.getControllerPrefixUrl() + "champions";
    }

    async getChampionsList(userId: number): Promise<Array<ChampionModel>> {
        return await this.service.getChampionsList(userId);
    }

}
