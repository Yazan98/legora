export interface SuccessResponse<T> {
    status: number;
    message: string;
    data: T;
}
