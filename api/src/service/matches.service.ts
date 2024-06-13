import {AppDataSource} from "../config/database.config.js";
import {UserModel} from "../models/user.model.js";
import {LolMatchesResponse} from "../response/custom/lol.matches.response.js";
import {MatchManager} from "../summoners/match.manager.js";
import {SummonerAccountsManager} from "../summoners/summoner.accounts.manager.js";

export class MatchesService {

    private userRepository = AppDataSource.getRepository(UserModel);

    async getLeagueOfLegendsMatchesByUserId(userId: number): Promise<Array<LolMatchesResponse>> {
        const user = await this.userRepository.findOne({ where: {id: userId } });
        const summonerInfo = await SummonerAccountsManager.getSummonerProfileByInfo(
            user.summonerName,
            user.summonerRegion,
            user.summonerServerCode
        );

        const matchesIds = await MatchManager.getLolMatchesIds(user.summonerRegion, summonerInfo.puuid);
        return await Promise.resolve(MatchManager.getMatchesByIds(matchesIds, user.summonerRegion, summonerInfo.puuid));
    }

}