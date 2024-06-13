import axios from "axios";
import { RiotRequestsManager } from "./riot.requests.manager.js";
import { championsList, imagesVersion } from "../app.js";
export class MatchManager {
    static async isLolMatchesFound(region, accountId) {
        let isMatchesFound = false;
        await axios.get(`https://${region}.api.riotgames.com/lol/match/v5/matches/by-puuid/${accountId}/ids?start=0&count=2`, {
            headers: RiotRequestsManager.getRequestHeader()
        }).then(result => {
            if (RiotRequestsManager.isRequestSuccess(result.status)) {
                isMatchesFound = result.data.length > 1;
            }
        });
        return Promise.resolve(isMatchesFound);
    }
    static async getLolMatchesIds(region, accountId) {
        const response = new Array();
        await axios.get(`https://${region}.api.riotgames.com/lol/match/v5/matches/by-puuid/${accountId}/ids?start=0&count=10`, {
            headers: RiotRequestsManager.getRequestHeader()
        }).then(result => {
            if (RiotRequestsManager.isRequestSuccess(result.status)) {
                for (let i = 0; i < result.data.length; i++) {
                    response.push(result.data[i]);
                }
            }
        });
        return Promise.resolve(response);
    }
    static async isTftMatchesFound(region, accountId) {
        let isMatchesFound = false;
        await axios.get(`https://${region}.api.riotgames.com/tft/match/v1/matches/by-puuid/${accountId}/ids?start=0&count=20`, {
            headers: RiotRequestsManager.getRequestHeader()
        }).then(result => {
            if (RiotRequestsManager.isRequestSuccess(result.status)) {
                isMatchesFound = result.data.length > 1;
            }
        });
        return Promise.resolve(isMatchesFound);
    }
    static async getMatchesByIds(matchesIds, region, summonerId) {
        const matches = new Array();
        for (let i = 0; i < matchesIds.length; i++) {
            await axios.get(`https://${region}.api.riotgames.com/lol/match/v5/matches/${matchesIds[i]}`, {
                headers: RiotRequestsManager.getRequestHeader()
            }).then(result => {
                if (RiotRequestsManager.isRequestSuccess(result.status)) {
                    const gameInfo = result.data;
                    const playerInstance = gameInfo.info.participants.filter((item) => {
                        return item.puuid == summonerId;
                    })[0];
                    const champion = championsList.filter((item) => {
                        return item.key == `${playerInstance.championId}`;
                    })[0];
                    const items = new Array();
                    items.push(`https://ddragon.leagueoflegends.com/cdn/${imagesVersion}/img/item/${playerInstance.item0}.png`);
                    items.push(`https://ddragon.leagueoflegends.com/cdn/${imagesVersion}/img/item/${playerInstance.item1}.png`);
                    items.push(`https://ddragon.leagueoflegends.com/cdn/${imagesVersion}/img/item/${playerInstance.item2}.png`);
                    items.push(`https://ddragon.leagueoflegends.com/cdn/${imagesVersion}/img/item/${playerInstance.item3}.png`);
                    items.push(`https://ddragon.leagueoflegends.com/cdn/${imagesVersion}/img/item/${playerInstance.item4}.png`);
                    items.push(`https://ddragon.leagueoflegends.com/cdn/${imagesVersion}/img/item/${playerInstance.item5}.png`);
                    matches.push({
                        id: matchesIds[i],
                        creationTimestamp: this.formatTimestampToDate(gameInfo.info.gameCreation),
                        mode: gameInfo.info.gameMode,
                        duration: this.formatMinutesToMinutesAndSeconds(gameInfo.info.gameDuration),
                        kills: playerInstance.kills,
                        deaths: playerInstance.deaths,
                        assists: playerInstance.assists,
                        farm: playerInstance.totalMinionsKilled,
                        gold: playerInstance.goldEarned,
                        isVictory: playerInstance.win,
                        champion: {
                            name: champion.name,
                            image: `https://ddragon.leagueoflegends.com/cdn/${imagesVersion}/img/champion/${champion.name.replace(" ", "")}.png`
                        },
                        items: items
                    });
                }
            });
            await this.delay(100);
        }
        return Promise.resolve(matches);
    }
    static delay(ms) {
        return new Promise(resolve => setTimeout(resolve, ms));
    }
    static formatTimestampToDate(timestamp) {
        const date = new Date(timestamp);
        const day = date.getDate();
        const month = date.getMonth() + 1;
        const year = date.getFullYear();
        const formattedDate = `${String(month).padStart(2, '0')}/${String(day).padStart(2, '0')}/${year}`;
        return formattedDate;
    }
    static formatMinutesToMinutesAndSeconds(minutes) {
        const wholeMinutes = Math.floor(minutes);
        const remainingSeconds = Math.round((minutes - wholeMinutes) * 60);
        const formattedMinutes = String(wholeMinutes).padStart(2, '0');
        const formattedSeconds = String(remainingSeconds).padStart(2, '0');
        return `${formattedMinutes}:${formattedSeconds}`;
    }
}
//# sourceMappingURL=match.manager.js.map