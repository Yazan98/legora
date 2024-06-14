import { RiotBaseController } from "./riot.base.controller.js";
import { RiotResponseGenerator } from "../response/riot.response.generator.js";
import { HomeService } from "../service/home.service.js";
export class HomeController extends RiotBaseController {
    constructor() {
        super(...arguments);
        this.homeService = new HomeService();
    }
    initControllerRoutes(app) {
        this.onRegisterGetRequest(app, this.getControllerUrl() + '/feed', (request, response) => {
            this.getHomeFeedTree(this.getUserId(request))
                .then((result) => {
                RiotResponseGenerator.onSendSuccessResponse(false, "Feed Found Successfully!", result, response);
            })
                .catch((ex) => {
                RiotResponseGenerator.onSendErrorResponse(ex.message, RiotResponseGenerator.BAD_REQUEST_CODE, response);
            });
        });
    }
    getControllerUrl() {
        return this.getControllerPrefixUrl() + "home";
    }
    async getHomeFeedTree(userId) {
        return await this.homeService.getHomeFeedTree(userId);
    }
}
//# sourceMappingURL=home.controller.js.map