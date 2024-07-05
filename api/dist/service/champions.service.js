import { championsList, imagesVersion, tftChampions } from "../app.js";
import { ChampionsRequestsManager } from "../riot/champions.requests.manager.js";
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
            if (champion.name.includes("Blanc") || champion.name.includes("Nunu") || champion.name.includes("Sante")) {
                continue;
            }
            results.push({
                id: Number(champion.key),
                name: champion.name,
                icon: `https://ddragon.leagueoflegends.com/cdn/${imagesVersion}/img/champion/${ChampionsRequestsManager.getChampionIcon(champion.name)}.png`,
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
                id: i,
                name: champion.name,
                icon: champion.image,
                isFreeToPlay: false,
                type: 'tft'
            });
        }
        return Promise.resolve(results);
    }
    async getChampionInfoByName(key) {
        const championInfo = await ChampionsRequestsManager.getChampionInfoByName(key);
        const spells = new Array();
        const skins = new Array();
        championInfo.spells.forEach((spell) => {
            spells.push({
                name: spell.name,
                description: spell.description,
                image: `https://ddragon.leagueoflegends.com/cdn/${imagesVersion}/img/spell/${spell.image.full}`
            });
        });
        championInfo.skins.forEach((skin) => {
            skins.push(`https://ddragon.leagueoflegends.com/cdn/img/champion/splash/${key}_${skin.num}.jpg`);
        });
        return Promise.resolve({
            name: championInfo.name,
            title: championInfo.title,
            description: championInfo.lore,
            coverImage: `https://ddragon.leagueoflegends.com/cdn/img/champion/splash/${key}_${championInfo.skins[0].num}.jpg`,
            passive: {
                name: championInfo.passive.name,
                description: championInfo.passive.description,
                image: `https://ddragon.leagueoflegends.com/cdn/img/champion/passive/${key}_${championInfo.passive.image.full}`,
            },
            allyTips: championInfo.allytips,
            enemiesTips: championInfo.enemytips,
            spells: spells,
            skins: skins,
            info: championInfo.info
        });
    }
}
//# sourceMappingURL=champions.service.js.map