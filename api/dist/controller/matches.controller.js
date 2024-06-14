import { RiotBaseController } from "./riot.base.controller.js";
import { MatchesService } from "../service/matches.service.js";
import { RiotResponseGenerator } from "../response/riot.response.generator.js";
export class MatchesController extends RiotBaseController {
    constructor() {
        super(...arguments);
        this.service = new MatchesService();
    }
    initControllerRoutes(app) {
        this.onRegisterGetRequest(app, this.getControllerUrl() + '/lol', (request, response) => {
            this.getLeagueOfLegendsMatchesByUserId(this.getUserId(request))
                .then((result) => {
                RiotResponseGenerator.onSendSuccessResponse(false, "Matches Found Successfully!", result, response);
            })
                .catch((ex) => {
                RiotResponseGenerator.onSendErrorResponse(ex.message, RiotResponseGenerator.BAD_REQUEST_CODE, response);
            });
        });
        this.onRegisterGetRequest(app, this.getControllerUrl() + '/tft', (request, response) => {
            this.getTftMatchesByUserId(this.getUserId(request))
                .then((result) => {
                RiotResponseGenerator.onSendSuccessResponse(false, "Matches Found Successfully!", result, response);
            })
                .catch((ex) => {
                RiotResponseGenerator.onSendErrorResponse(ex.message, RiotResponseGenerator.BAD_REQUEST_CODE, response);
            });
        });
    }
    getControllerUrl() {
        return this.getControllerPrefixUrl() + "matches";
    }
    async getTftMatchesByUserId(userId) {
        return await this.service.getTftMatchesByUserId(userId);
    }
    async getLeagueOfLegendsMatchesByUserId(userId) {
        return await this.service.getLeagueOfLegendsMatchesByUserId(userId);
    }
}
//# sourceMappingURL=matches.controller.js.map