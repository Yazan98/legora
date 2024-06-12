import { Response } from 'express';
export declare class RiotResponseGenerator {
    static CREATED_CODE: number;
    static SUCCESS_CODE: number;
    static BAD_REQUEST_CODE: number;
    static UN_AUTH_CODE: number;
    static onSendErrorResponse(errorMessage: string, code: number, response: Response): void;
    static onSendBadRequestErrorResponse(errorMessage: string, response: Response): void;
    static onSendSuccessResponse(isCreated: boolean, message: string, data: any, response: Response): void;
}
