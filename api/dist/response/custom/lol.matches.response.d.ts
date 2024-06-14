export interface LolMatchesResponse {
    id: string;
    creationTimestamp: string;
    mode: string;
    duration: string;
    kills: number;
    deaths: number;
    assists: number;
    farm: number;
    gold: number;
    isVictory: boolean;
    items: Array<string>;
    champion: LolMatchChampion;
}
export interface LolMatchInfo {
    id: string;
    creationTimestamp: string;
    mode: string;
    duration: string;
    isVictory: boolean;
    champion: LolMatchChampion;
    players: Array<LolMarchPlayerInfo>;
}
export interface LolMarchPlayerInfo {
    kills: number;
    deaths: number;
    assists: number;
    farm: number;
    gold: number;
    champion: LolMatchChampion;
    items: Array<string>;
}
export type LolMatchChampion = {
    name: string;
    image: string;
};
