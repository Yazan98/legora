import { RiotBaseController } from "./riot.base.controller.js";
import { RiotResponseGenerator } from "../response/riot.response.generator.js";
import { AccountsService } from "../service/accounts.service.js";
export class AccountsController extends RiotBaseController {
    constructor() {
        super(...arguments);
        this.accountsService = new AccountsService();
    }
    initControllerRoutes(app) {
        this.onRegisterPostRequest(app, this.getControllerUrl() + "/login", (req, res) => {
            this.onLoginUser(req.body, this.getPlatformValue(req))
                .then((result) => {
                RiotResponseGenerator.onSendSuccessResponse(true, "Account Logged In Successfully", result, res);
            })
                .catch((ex) => {
                RiotResponseGenerator.onSendErrorResponse(ex.message, RiotResponseGenerator.BAD_REQUEST_CODE, res);
            });
        });
        this.onRegisterPostRequest(app, this.getControllerUrl() + "/register", (request, response) => {
            this.onInsertUser(request.body, this.getPlatformValue(request))
                .then((result) => {
                RiotResponseGenerator.onSendSuccessResponse(true, "Account Updated Successfully", result, response);
            })
                .catch((ex) => {
                RiotResponseGenerator.onSendErrorResponse(ex.message, RiotResponseGenerator.BAD_REQUEST_CODE, response);
            });
        });
        this.onRegisterGetRequest(app, this.getControllerUrl() + '/info', (request, response) => {
            this.getProfileInfo(this.getUserId(request))
                .then((result) => {
                RiotResponseGenerator.onSendSuccessResponse(true, "Account Info Found Successfully!", result, response);
            })
                .catch((ex) => {
                RiotResponseGenerator.onSendErrorResponse(ex.message, RiotResponseGenerator.BAD_REQUEST_CODE, response);
            });
        });
    }
    async getProfileInfo(userId) {
        return this.accountsService.getProfileInfoById(userId);
    }
    async onInsertUser(userRequestBody, platform) {
        return this.accountsService.onCreateUserProfile(userRequestBody, platform);
    }
    async onLoginUser(loginRequestBody, platform) {
        return this.accountsService.onLoginAccount(loginRequestBody, platform);
    }
    getControllerUrl() {
        return this.getControllerPrefixUrl() + "accounts";
    }
}
//# sourceMappingURL=accounts.controller.js.map