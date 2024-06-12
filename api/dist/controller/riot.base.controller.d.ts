import { Express, Request, Response } from "express";
export declare abstract class RiotBaseController {
    abstract initControllerRoutes(app: Express): any;
    abstract getControllerUrl(): string;
    getPlatformValue<T>(request: Request<T>): string;
    getLanguage<T>(request: Request<T>): string;
    getUserId<T>(request: Request<T>): number;
    onRegisterPostRequest<T>(app: Express, url: string, onRequestBody: (request: Request<T>, response: Response) => any): void;
    onRegisterGetRequest(app: Express, url: string, onRequestBody: (request: Request, response: Response) => any): void;
    onRegisterPutRequest(app: Express, url: string, onRequestBody: (request: Request, response: Response) => any): void;
    onRegisterDeleteRequest<T>(app: Express, url: string, onRequestBody: (request: Request<T>, response: Response) => any): void;
    getControllerPrefixUrl(): string;
}
