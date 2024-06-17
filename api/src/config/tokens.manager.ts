import {UserModel} from "../models/user.model.js";
import jwt from 'jsonwebtoken'

export class TokensManager {

    private static PRIVATE_KEY = "41a5a1df3a3fdf52cd984853e829fff40473be3fbd38c3d6b8df5c375aec61ae"

    static onGenerateAccessToken(user: UserModel): string {
        return jwt.sign({
            email: user.email,
            id: user.id,
            type: "access_token"
        }, TokensManager.PRIVATE_KEY, {
            expiresIn: "1d",
            algorithm: "HS256",
            issuer: "Legora Api"
        });
    }

    static onGenerateRefreshToken(user: UserModel): string {
        return jwt.sign({
            email: user.email,
            id: user.id,
            type: "refresh_token"
        }, TokensManager.PRIVATE_KEY, {
            algorithm: "HS256",
            issuer: "Legora Api"
        });
    }

    static verifyAccessToken(token: string): number {
        try {
            const tokenPayload = jwt.verify(token, TokensManager.PRIVATE_KEY);

            // @ts-ignore
            const type = tokenPayload.type;
            if (type !== "access_token") {
                return 0;
            }

            console.log(`Token Content : ${tokenPayload}`)

            // @ts-ignore
            return tokenPayload.id;
        } catch (error) {
            console.error(error)
            return 0
        }
    }

    static verifyRefreshToken(token: string): number {
        try {
            const tokenPayload = jwt.verify(token, TokensManager.PRIVATE_KEY);

            // @ts-ignore
            const type = tokenPayload.type;
            if (type !== "refresh_token") {
                return 0;
            }

            // @ts-ignore
            return tokenPayload.id;
        } catch (error) {
            return 0
        }
    }

}