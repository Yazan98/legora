import { UserModel } from "../models/user.model.js";
export declare class TokensManager {
    private static PRIVATE_KEY;
    static onGenerateAccessToken(user: UserModel): string;
    static onGenerateRefreshToken(user: UserModel): string;
    static verifyAccessToken(token: string): number;
    static verifyRefreshToken(token: string): number;
}
