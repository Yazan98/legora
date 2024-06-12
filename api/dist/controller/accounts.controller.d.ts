import { RiotBaseController } from "./riot.base.controller.js";
import { AccountsControllerImpl } from "./impl/accounts.controller.impl.js";
import { LoginRequestBody } from "../request/login.request.body.js";
import { UserRequestBody } from "../request/user.request.body.js";
import { AuthResponse } from "../response/custom/auth.response.js";
import { Express } from "express";
import { ProfileInfoResponse } from "../response/custom/profile.info.response.js";
export declare class AccountsController extends RiotBaseController implements AccountsControllerImpl {
    private accountsService;
    initControllerRoutes(app: Express): void;
    getProfileInfo(userId: number): Promise<ProfileInfoResponse>;
    onInsertUser(userRequestBody: UserRequestBody, platform: string): Promise<AuthResponse>;
    onLoginUser(loginRequestBody: LoginRequestBody, platform: string): Promise<AuthResponse>;
    getControllerUrl(): string;
}
