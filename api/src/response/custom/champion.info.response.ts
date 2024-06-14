import {RiotChampionInstanceInfo} from "../riot/riot.champion.response..js";

export interface ChampionInfoResponse {
    name: string,
    title: string,
    description: string,
    coverImage: string,
    info: RiotChampionInstanceInfo,
    passive: ChampionInfoResponsePassive,
    spells: Array<ChampionInfoResponseSpell>,
    allyTips: Array<string>,
    enemiesTips: Array<string>,
    skins: Array<string>,
}

export interface ChampionInfoResponsePassive {
    name: string,
    image: string,
    description: string
}

export interface ChampionInfoResponseSpell {
    name: string,
    image: string,
    description: string
}