import {RiotBaseController} from "./riot.base.controller.js";
import {AccountsControllerImpl} from "./impl/accounts.controller.impl.js";
import {LoginRequestBody} from "../request/login.request.body.js";
import {RiotResponseGenerator} from "../response/riot.response.generator.js";
import {UserRequestBody} from "../request/user.request.body.js";
import {AuthResponse} from "../response/custom/auth.response.js";
import {Express} from "express";
import {AccountsService} from "../service/accounts.service.js";
import {ProfileInfoResponse} from "../response/custom/profile.info.response.js";

export class AccountsController extends RiotBaseController implements AccountsControllerImpl {

    private accountsService = new AccountsService();

    initControllerRoutes(app: Express) {
        this.onRegisterPostRequest<LoginRequestBody>(app, this.getControllerUrl() + "/login", (req, res) => {
            this.onLoginUser(req.body, this.getPlatformValue(req))
                .then((result) => {
                    RiotResponseGenerator.onSendSuccessResponse(true, "Account Logged In Successfully", result, res);
                })
                .catch((ex) => {
                    RiotResponseGenerator.onSendErrorResponse(ex.message, RiotResponseGenerator.BAD_REQUEST_CODE, res);
                })
        })

        this.onRegisterPostRequest<UserRequestBody>(app, this.getControllerUrl() + "/register", (request, response) => {
            this.onInsertUser(request.body, this.getPlatformValue(request))
                .then((result) => {
                    RiotResponseGenerator.onSendSuccessResponse(true, "Account Updated Successfully", result, response);
                })
                .catch((ex) => {
                    RiotResponseGenerator.onSendErrorResponse(ex.message, RiotResponseGenerator.BAD_REQUEST_CODE, response);
                })
        })

        this.onRegisterGetRequest(app, this.getControllerUrl() + '/info', (request, response) => {
            this.getProfileInfo(this.getUserId(request))
                .then((result) => {
                    RiotResponseGenerator.onSendSuccessResponse(true, "Account Info Found Successfully!", result, response);
                })
                .catch((ex) => {
                    RiotResponseGenerator.onSendErrorResponse(ex.message, RiotResponseGenerator.BAD_REQUEST_CODE, response);
                })
        })
    }

    async getProfileInfo(userId: number): Promise<ProfileInfoResponse> {
        return this.accountsService.getProfileInfoById(userId);
    }

    async onInsertUser(userRequestBody: UserRequestBody, platform: string): Promise<AuthResponse> {
        return this.accountsService.onCreateUserProfile(userRequestBody, platform);
    }

    async onLoginUser(loginRequestBody: LoginRequestBody, platform: string): Promise<AuthResponse> {
        return this.accountsService.onLoginAccount(loginRequestBody, platform);
    }

    getControllerUrl(): string {
        return this.getControllerPrefixUrl() + "accounts";
    }

}