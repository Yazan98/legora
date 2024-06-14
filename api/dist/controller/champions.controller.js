import { RiotBaseController } from "./riot.base.controller.js";
import { RiotResponseGenerator } from "../response/riot.response.generator.js";
import { ChampionsService } from "../service/champions.service.js";
export class ChampionsController extends RiotBaseController {
    constructor() {
        super(...arguments);
        this.service = new ChampionsService();
    }
    initControllerRoutes(app) {
        this.onRegisterGetRequest(app, this.getControllerUrl() + '/lol', (request, response) => {
            this.getChampionsList(this.getUserId(request))
                .then((result) => {
                RiotResponseGenerator.onSendSuccessResponse(true, "Champions Found Successfully!", result, response);
            })
                .catch((ex) => {
                RiotResponseGenerator.onSendErrorResponse(ex.message, RiotResponseGenerator.BAD_REQUEST_CODE, response);
            });
        });
        this.onRegisterGetRequest(app, this.getControllerUrl() + '/tft', (request, response) => {
            this.getTftChampionsList()
                .then((result) => {
                RiotResponseGenerator.onSendSuccessResponse(true, "Champions Found Successfully!", result, response);
            })
                .catch((ex) => {
                RiotResponseGenerator.onSendErrorResponse(ex.message, RiotResponseGenerator.BAD_REQUEST_CODE, response);
            });
        });
    }
    getControllerUrl() {
        return this.getControllerPrefixUrl() + "champions";
    }
    async getTftChampionsList() {
        return await this.service.getTftChampionsList();
    }
    async getChampionsList(userId) {
        return await this.service.getChampionsList(userId);
    }
}
//# sourceMappingURL=champions.controller.js.map