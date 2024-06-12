import { TokensManager } from "./tokens.manager.js";
import { RiotResponseGenerator } from "../response/riot.response.generator.js";
export class AuthMiddleware {
    static async onValidateAuthHeaders(req, res, next) {
        const requestUrl = req.url;
        const isRequestAuthIgnored = AuthMiddleware.PUBLIC_REQUESTS_URLS.includes(requestUrl);
        if (isRequestAuthIgnored) {
            next();
            return;
        }
        const authHeader = req.header("Authorization");
        if (!authHeader) {
            RiotResponseGenerator.onSendErrorResponse("Auth Header Missing", RiotResponseGenerator.UN_AUTH_CODE, res);
            return;
        }
        let userId = 0;
        if (requestUrl.includes('refresh')) {
            userId = TokensManager.verifyRefreshToken(authHeader);
        }
        else {
            userId = TokensManager.verifyAccessToken(authHeader);
        }
        if (userId <= 0) {
            RiotResponseGenerator.onSendErrorResponse("Not Allowed To Access This Request ...", RiotResponseGenerator.UN_AUTH_CODE, res);
            return;
        }
        next();
    }
}
AuthMiddleware.PUBLIC_REQUESTS_URLS = [
    "ping",
    "/api/v1/accounts/login",
    "/api/v1/accounts/register",
];
//# sourceMappingURL=auth.middleware.js.map