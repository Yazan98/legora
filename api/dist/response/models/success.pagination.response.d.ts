export interface SuccessPaginationResponse<T> {
    items: Array<T>;
    paginationInfo: PaginationInfo;
}
export interface PaginationInfo {
    numberOfResults: number;
}
