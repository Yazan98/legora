export interface TftMatchesResponse {
    id: string;
    date: string;
    placement: number;
    units: Array<TftMatchUnit>;
    augments: Array<TftMatchAugment>;
}
export interface TftMatchAugment {
    image: string;
    name: string;
}
export interface TftMatchUnit {
    image: string;
    items: Array<string>;
}
