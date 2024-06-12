export class RiotResponseGenerator {
    static onSendErrorResponse(errorMessage, code, response) {
        const errorBody = {
            errorMessage: errorMessage,
            status: code
        };
        response.status(code).send(errorBody);
    }
    static onSendBadRequestErrorResponse(errorMessage, response) {
        const errorBody = {
            errorMessage: errorMessage,
            status: RiotResponseGenerator.BAD_REQUEST_CODE
        };
        response.status(RiotResponseGenerator.BAD_REQUEST_CODE).send(errorBody);
    }
    static onSendSuccessResponse(isCreated, message, data, response) {
        let responseCode = 0;
        if (isCreated) {
            responseCode = RiotResponseGenerator.CREATED_CODE;
        }
        else {
            responseCode = RiotResponseGenerator.SUCCESS_CODE;
        }
        const errorBody = {
            message: message,
            status: responseCode,
            data: data
        };
        response.status(responseCode).send(errorBody);
    }
}
RiotResponseGenerator.CREATED_CODE = 201;
RiotResponseGenerator.SUCCESS_CODE = 200;
RiotResponseGenerator.BAD_REQUEST_CODE = 400;
RiotResponseGenerator.UN_AUTH_CODE = 403;
//# sourceMappingURL=riot.response.generator.js.map