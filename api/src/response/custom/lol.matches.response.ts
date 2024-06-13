
export interface LolMatchesResponse {
    id: string,
    creationTimestamp: string,
    mode: string,
    duration: string,
    kills: number,
    deaths: number,
    assists: number,
    farm: number,
    gold: number,
    isVictory: boolean,
    items: Array<string>,
    champion: LolMatchChampion
}

export type LolMatchChampion = {
    name: string,
    image: string
};