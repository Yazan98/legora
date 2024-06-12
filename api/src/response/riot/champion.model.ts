export interface ChampionModel {
    type: string,
    version: string,
    data: Map<string, ChampionObject>
}

export interface ChampionObject {
    version: string,
    id: string,
    key: string,
    name: string,
    title: string,
    blurb: string
}
