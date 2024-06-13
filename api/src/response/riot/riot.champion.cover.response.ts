export interface RiotChampionCoverResponse {
    data: Map<string, RiotChampionCoverItem>
}

export interface RiotChampionCoverItem {
    skins: Array<RiotChampionCoverImage>
}

export interface RiotChampionCoverImage {
    num: number
}