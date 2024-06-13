import { ChampionModel } from "../response/custom/champion.model.js";
export declare class ChampionsService {
    private userRepository;
    getChampionsList(userId: number): Promise<Array<ChampionModel>>;
}
