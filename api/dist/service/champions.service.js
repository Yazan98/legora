import { championsList, imagesVersion, tftChampions } from "../app.js";
import { ChampionsRequestsManager } from "../summoners/champions.requests.manager.js";
import { AppDataSource } from "../config/database.config.js";
import { UserModel } from "../models/user.model.js";
export class ChampionsService {
    constructor() {
        this.userRepository = AppDataSource.getRepository(UserModel);
    }
    async getChampionsList(userId) {
        const champions = championsList;
        const results = new Array();
        const user = await this.userRepository.findOne({
            where: {
                id: userId
            }
        });
        const freeChampionsIds = await ChampionsRequestsManager.getFreeChampionIds(user.summonerServerCode);
        for (let i = 0; i < champions.length; i++) {
            const champion = champions[i];
            results.push({
                id: Number(champion.key),
                name: champion.name,
                icon: `https://ddragon.leagueoflegends.com/cdn/${imagesVersion}/img/champion/${champion.name.replace(" ", "")}.png`,
                isFreeToPlay: freeChampionsIds.includes(Number(champion.key)),
                type: 'lol'
            });
        }
        return Promise.resolve(results);
    }
    async getTftChampionsList() {
        const champions = tftChampions;
        const results = new Array();
        for (let i = 0; i < champions.length; i++) {
            const champion = champions[i];
            results.push({
                id: Number(champion.id),
                name: champion.name,
                icon: champion.image,
                isFreeToPlay: false,
                type: 'tft'
            });
        }
        return Promise.resolve(results);
    }
}
//# sourceMappingURL=champions.service.js.map