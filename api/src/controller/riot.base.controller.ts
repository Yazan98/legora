import { Express, Router, Request, Response } from "express"
import {app} from "firebase-admin";
import {TokensManager} from "../config/tokens.manager.js";
import {RiotResponseGenerator} from "../response/riot.response.generator.js";

const router = Router();

export abstract class RiotBaseController {

    abstract initControllerRoutes(app: Express)

    abstract getControllerUrl(): string

    getPlatformValue<T>(request: Request<T>): string {
        return request.header("Apps-Platform")
    }

    getLanguage<T>(request: Request<T>): string {
        return request.header("Apps-Language")
    }

    getUserId<T>(request: Request<T>): number {
        return TokensManager.verifyAccessToken(request.header("Authorization"))
    }

    onRegisterPostRequest<T>(app: Express, url: string, onRequestBody: (request: Request<T>, response: Response) => any) {
        app.post(url, (request: Request<T>, response) => {
            if (!request.body) {
                RiotResponseGenerator.onSendBadRequestErrorResponse("Request Body Missing", response);
                return
            }

            onRequestBody(request, response)
        })
    }

    onRegisterGetRequest(app: Express, url: string, onRequestBody: (request: Request, response: Response) => any) {
        app.get(url, (request, response) => {
            onRequestBody(request, response)
        })
    }

    onRegisterPutRequest(app: Express, url: string, onRequestBody: (request: Request, response: Response) => any) {
        app.put(url, (request, response) => {
            onRequestBody(request, response)
        })
    }

    onRegisterDeleteRequest<T>(app: Express, url: string, onRequestBody: (request: Request<T>, response: Response) => any) {
        app.delete(url, (request: Request<T>, response) => {
            onRequestBody(request, response)
        })
    }

    getControllerPrefixUrl(): string {
        return "/api/v1/"
    }

}