import { TftAugmentBodyImage } from "./tft.augments.response.js";
export interface RiotChampionResponse {
    data: Map<string, RiotChampionInstance>;
}
export interface RiotChampionInstance {
    id: string;
    name: string;
    key: string;
    title: string;
    image: TftAugmentBodyImage;
    skins: Array<RiotChampionSkin>;
    lore: string;
    allytips: Array<string>;
    enemytips: Array<string>;
    tags: Array<string>;
    partype: string;
    info: RiotChampionInstanceInfo;
    spells: Array<RiotChampionSpell>;
    passive: RiotChampionInstancePassive;
}
export interface RiotChampionInstancePassive {
    name: string;
    description: string;
    image: TftAugmentBodyImage;
}
export interface RiotChampionInstanceInfo {
    attack: number;
    defense: number;
    magic: number;
    difficulty: number;
}
export interface RiotChampionSpell {
    id: string;
    name: string;
    description: string;
    image: TftAugmentBodyImage;
}
export interface RiotChampionSkin {
    id: string;
    num: number;
}
