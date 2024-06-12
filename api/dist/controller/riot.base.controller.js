import { Router } from "express";
import { TokensManager } from "../config/tokens.manager.js";
import { RiotResponseGenerator } from "../response/riot.response.generator.js";
const router = Router();
export class RiotBaseController {
    getPlatformValue(request) {
        return request.header("Apps-Platform");
    }
    getLanguage(request) {
        return request.header("Apps-Language");
    }
    getUserId(request) {
        return TokensManager.verifyAccessToken(request.header("Authorization"));
    }
    onRegisterPostRequest(app, url, onRequestBody) {
        app.post(url, (request, response) => {
            if (!request.body) {
                RiotResponseGenerator.onSendBadRequestErrorResponse("Request Body Missing", response);
                return;
            }
            onRequestBody(request, response);
        });
    }
    onRegisterGetRequest(app, url, onRequestBody) {
        app.get(url, (request, response) => {
            onRequestBody(request, response);
        });
    }
    onRegisterPutRequest(app, url, onRequestBody) {
        app.put(url, (request, response) => {
            onRequestBody(request, response);
        });
    }
    onRegisterDeleteRequest(app, url, onRequestBody) {
        app.delete(url, (request, response) => {
            onRequestBody(request, response);
        });
    }
    getControllerPrefixUrl() {
        return "/api/v1/";
    }
}
//# sourceMappingURL=riot.base.controller.js.map