import { TftAugmentBodyImage } from "./tft.augments.response.js";
export declare class TftMatchInfo {
    info: TftMatchDetails;
}
export interface TftMatchDetails {
    gameCreation: number;
    participants: Array<TftProfile>;
}
export interface TftProfile {
    placement: number;
    puuid: string;
    augments: Array<string>;
    companion: TftPlayerCompanion;
    level: number;
    traits: Array<TftPlayerTraits>;
    units: Array<TftChampion>;
}
export interface TftChampion {
    character_id: string;
    tier: number;
    itemNames: Array<string>;
}
export interface TftChampionInfo {
    id: string;
    name: string;
    image: string;
}
export interface TftItemInfo {
    id: string;
    name: string;
    image: string;
}
export interface TftChampionsResponse {
    data: Map<string, TftChampionInstance>;
}
export interface TftChampionInstance {
    id: string;
    name: string;
    image: TftAugmentBodyImage;
}
export interface TftPlayerTraits {
    name: string;
    num_units: number;
}
export interface TftPlayerCompanion {
    skin_ID: number;
    species: string;
}
