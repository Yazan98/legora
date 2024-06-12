import { Request, Response, NextFunction } from 'express';
export declare class AppsHeadersMiddleware {
    static onValidateAppsHeaders(req: Request, res: Response, next: NextFunction): void;
}
