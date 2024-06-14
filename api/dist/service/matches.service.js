import { AppDataSource } from "../config/database.config.js";
import { UserModel } from "../models/user.model.js";
import { MatchManager } from "../riot/match.manager.js";
import { SummonerAccountsManager } from "../riot/summoner.accounts.manager.js";
export class MatchesService {
    constructor() {
        this.userRepository = AppDataSource.getRepository(UserModel);
    }
    async getLeagueOfLegendsMatchesByUserId(userId) {
        const user = await this.userRepository.findOne({ where: { id: userId } });
        const summonerInfo = await SummonerAccountsManager.getSummonerProfileByInfo(user.summonerName, user.summonerRegion, user.summonerServerCode);
        const matchesIds = await MatchManager.getLolMatchesIds(user.summonerRegion, summonerInfo.puuid);
        return await Promise.resolve(MatchManager.getMatchesByIds(matchesIds, user.summonerRegion, summonerInfo.puuid));
    }
    async getTftMatchesByUserId(userId) {
        const user = await this.userRepository.findOne({ where: { id: userId } });
        const summonerInfo = await SummonerAccountsManager.getSummonerProfileByInfo(user.summonerName, user.summonerRegion, user.summonerServerCode);
        const matchesIds = await MatchManager.getTftMatchesIds(user.summonerRegion, summonerInfo.puuid);
        return await Promise.resolve(MatchManager.getTftMatchesByIds(matchesIds, user.summonerRegion, summonerInfo.puuid));
    }
    async getLeagueOfLegendsMatchInfoById(userId, matchId) {
        const user = await this.userRepository.findOne({ where: { id: userId } });
        const summonerInfo = await SummonerAccountsManager.getSummonerProfileByInfo(user.summonerName, user.summonerRegion, user.summonerServerCode);
        return await MatchManager.getLolMatchById(matchId, user.summonerRegion, summonerInfo.puuid, user.summonerServerCode);
    }
}
//# sourceMappingURL=matches.service.js.map