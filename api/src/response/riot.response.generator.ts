import { Response } from 'express';
import {ErrorResponse} from "./models/error.response.js";
import {SuccessResponse} from "./models/success.response.js";

export class RiotResponseGenerator {

    public static CREATED_CODE = 201
    public static SUCCESS_CODE = 200
    public static BAD_REQUEST_CODE = 400
    public static UN_AUTH_CODE = 403

    static onSendErrorResponse(errorMessage: string, code: number, response: Response) {
        const errorBody: ErrorResponse = {
            errorMessage: errorMessage,
            status: code
        }

        response.status(code).send(errorBody);
    }

    static onSendBadRequestErrorResponse(errorMessage: string, response: Response) {
        const errorBody: ErrorResponse = {
            errorMessage: errorMessage,
            status: RiotResponseGenerator.BAD_REQUEST_CODE
        }

        response.status(RiotResponseGenerator.BAD_REQUEST_CODE).send(errorBody);
    }

    static onSendSuccessResponse(
        isCreated: boolean,
        message: string,
        data: any,
        response: Response
    ) {
        let responseCode = 0
        if (isCreated) {
            responseCode = RiotResponseGenerator.CREATED_CODE
        } else {
            responseCode = RiotResponseGenerator.SUCCESS_CODE
        }

        const errorBody: SuccessResponse<any> = {
            message: message,
            status: responseCode,
            data: data
        }

        response.status(responseCode).send(errorBody);
    }

}