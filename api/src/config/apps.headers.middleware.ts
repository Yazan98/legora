import { Express, Request, Response, NextFunction } from 'express';
import {RiotResponseGenerator} from "../response/riot.response.generator.js";

export class AppsHeadersMiddleware {

    static onValidateAppsHeaders(req: Request, res: Response, next: NextFunction): void {
        const requestUrl = req.url
        if (requestUrl.includes('ping')) {
            next();
            return;
        }

        const languageHeader = req.header("Apps-Language");
        const appVersion= req.header('Apps-Version')
        const appPlatform = req.header('Apps-Platform')

        if (!languageHeader) {
            RiotResponseGenerator.onSendErrorResponse("Language Header Missing", RiotResponseGenerator.BAD_REQUEST_CODE, res)
            return
        }

        if (!appVersion) {
            RiotResponseGenerator.onSendErrorResponse("Version Header Missing", RiotResponseGenerator.BAD_REQUEST_CODE, res)
            return
        }

        if (!appPlatform) {
            RiotResponseGenerator.onSendErrorResponse("Platform Header Missing", RiotResponseGenerator.BAD_REQUEST_CODE, res)
            return
        }

        next();
    }
}