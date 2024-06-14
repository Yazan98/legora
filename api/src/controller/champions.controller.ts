import {RiotBaseController} from "./riot.base.controller.js";
import {ChampionsControllerImpl} from "./impl/champions.controller.impl.js";
import {Express} from "express";
import {ChampionModel} from "../response/custom/champion.model.js";
import {RiotResponseGenerator} from "../response/riot.response.generator.js";
import {ChampionsService} from "../service/champions.service.js";
import {ChampionInfoResponse} from "../response/custom/champion.info.response.js";

export class ChampionsController extends RiotBaseController implements ChampionsControllerImpl {

    private service = new ChampionsService();

    initControllerRoutes(app: Express) {
        this.onRegisterGetRequest(app, this.getControllerUrl() + '/lol', (request, response) => {
            this.getChampionsList(this.getUserId(request))
                .then((result) => {
                    RiotResponseGenerator.onSendSuccessResponse(false, "Champions Found Successfully!", result, response);
                })
                .catch((ex) => {
                    RiotResponseGenerator.onSendErrorResponse(ex.message, RiotResponseGenerator.BAD_REQUEST_CODE, response);
                })
        })

        this.onRegisterGetRequest(app, this.getControllerUrl() + '/lol/:id', (request, response) => {
            this.getChampionInfoByName(request.params.id)
                .then((result) => {
                    RiotResponseGenerator.onSendSuccessResponse(false, "Champions Found Successfully!", result, response);
                })
                .catch((ex) => {
                    RiotResponseGenerator.onSendErrorResponse(ex.message, RiotResponseGenerator.BAD_REQUEST_CODE, response);
                })
        })

        this.onRegisterGetRequest(app, this.getControllerUrl() + '/tft', (request, response) => {
            this.getTftChampionsList()
                .then((result) => {
                    RiotResponseGenerator.onSendSuccessResponse(false, "Champions Found Successfully!", result, response);
                })
                .catch((ex) => {
                    RiotResponseGenerator.onSendErrorResponse(ex.message, RiotResponseGenerator.BAD_REQUEST_CODE, response);
                })
        })
    }

    getControllerUrl(): string {
        return this.getControllerPrefixUrl() + "champions";
    }

    async getChampionInfoByName(key: string): Promise<ChampionInfoResponse> {
        return await this.service.getChampionInfoByName(key);
    }

    async getTftChampionsList(): Promise<Array<ChampionModel>> {
        return await this.service.getTftChampionsList();
    }

    async getChampionsList(userId: number): Promise<Array<ChampionModel>> {
        return await this.service.getChampionsList(userId);
    }

}
