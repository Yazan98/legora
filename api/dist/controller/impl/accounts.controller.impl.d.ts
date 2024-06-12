import { UserRequestBody } from "../../request/user.request.body.js";
import { LoginRequestBody } from "../../request/login.request.body.js";
import { AuthResponse } from "../../response/custom/auth.response.js";
import { ProfileInfoResponse } from "../../response/custom/profile.info.response.js";
export interface AccountsControllerImpl {
    onInsertUser(userRequestBody: UserRequestBody, platform: string): Promise<AuthResponse>;
    onLoginUser(loginRequestBody: LoginRequestBody, platform: string): Promise<AuthResponse>;
    getProfileInfo(userId: number): Promise<ProfileInfoResponse>;
}
