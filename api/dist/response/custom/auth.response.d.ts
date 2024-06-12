import { UserModel } from "../../models/user.model.js";
export interface AuthResponse {
    account: UserModel;
    auth: AuthInfo;
}
export interface AuthInfo {
    accessToken: string;
    refreshToken: string;
}
