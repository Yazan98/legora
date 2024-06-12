import 'reflect-metadata';
import { UserRequestBody } from "../request/user.request.body.js";
import { AuthResponse } from "../response/custom/auth.response.js";
import { LoginRequestBody } from "../request/login.request.body.js";
import { ProfileInfoResponse } from "../response/custom/profile.info.response.js";
export declare class AccountsService {
    private userRepository;
    onCreateUserProfile(requestBody: UserRequestBody, platform: string): Promise<AuthResponse>;
    onLoginAccount(loginRequestBody: LoginRequestBody, platform: string): Promise<AuthResponse>;
    private getUserModelByQuery;
    getProfileInfoById(userId: number): Promise<ProfileInfoResponse>;
}
