import { NextFunction, Request, Response } from "express";
export declare class AuthMiddleware {
    private static PUBLIC_REQUESTS_URLS;
    static onValidateAuthHeaders(req: Request, res: Response, next: NextFunction): Promise<void>;
}
