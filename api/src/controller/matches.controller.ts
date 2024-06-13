import {RiotBaseController} from "./riot.base.controller.js";
import {MatchesControllerImpl} from "./impl/matches.controller.impl.js";
import {Express} from "express";
import {LolMatchesResponse} from "../response/custom/lol.matches.response.js";
import {MatchesService} from "../service/matches.service.js";
import {RiotResponseGenerator} from "../response/riot.response.generator.js";

export class MatchesController extends RiotBaseController implements MatchesControllerImpl {

    private service = new MatchesService();

    initControllerRoutes(app: Express) {
        this.onRegisterGetRequest(app, this.getControllerUrl() + '/lol', (request, response) => {
            this.getLeagueOfLegendsMatchesByUserId(this.getUserId(request))
                .then((result) => {
                    RiotResponseGenerator.onSendSuccessResponse(false, "Matches Found Successfully!", result, response);
                })
                .catch((ex) => {
                    RiotResponseGenerator.onSendErrorResponse(ex.message, RiotResponseGenerator.BAD_REQUEST_CODE, response);
                })
        })
    }

    getControllerUrl(): string {
        return this.getControllerPrefixUrl() + "matches";
    }

    async getLeagueOfLegendsMatchesByUserId(userId: number): Promise<Array<LolMatchesResponse>> {
        return await this.service.getLeagueOfLegendsMatchesByUserId(userId);
    }
}