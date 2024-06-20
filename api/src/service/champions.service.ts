import {ChampionModel} from "../response/custom/champion.model.js";
import {championsList, imagesVersion, tftChampions} from "../app.js";
import {ChampionsRequestsManager} from "../riot/champions.requests.manager.js";
import {AppDataSource} from "../config/database.config.js";
import {UserModel} from "../models/user.model.js";
import {ChampionInfoResponse, ChampionInfoResponseSpell} from "../response/custom/champion.info.response.js";
import {RiotChampionInstance, RiotChampionResponse} from "../response/riot/riot.champion.response..js";

export class ChampionsService {

    private userRepository = AppDataSource.getRepository(UserModel);

    async getChampionsList(userId: number): Promise<Array<ChampionModel>> {
        const champions = championsList;
        const results = new Array<ChampionModel>()
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
            })
        }

        return Promise.resolve(results);
    }

    async getTftChampionsList(): Promise<Array<ChampionModel>> {
        const champions = tftChampions;
        const results = new Array<ChampionModel>()

        for (let i = 0; i < champions.length; i++) {
            const champion = champions[i];
            results.push({
                id: Number(champion.id),
                name: champion.name,
                icon: champion.image,
                isFreeToPlay: false,
                type: 'tft'
            })
        }

        return Promise.resolve(results);
    }

    async getChampionInfoByName(key: string): Promise<ChampionInfoResponse> {
        const championInfo: RiotChampionInstance = await ChampionsRequestsManager.getChampionInfoByName(key);
        const spells = new Array<ChampionInfoResponseSpell>();
        const skins = new Array<string>();
        championInfo.spells.forEach((spell) => {
            spells.push({
                name: spell.name,
                description: spell.description,
                image: `https://ddragon.leagueoflegends.com/cdn/${imagesVersion}/img/spell/${spell.image.full}`
            });
        });

        championInfo.skins.forEach((skin) => {
            skins.push(`https://ddragon.leagueoflegends.com/cdn/img/champion/splash/${key}_${skin.num}.jpg`)
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