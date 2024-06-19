import axios from "axios";
import { RiotRequestsManager } from "./riot.requests.manager.js";
import { championsList, imagesVersion, tftAugments, tftChampions, tftItems } from "../app.js";
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
    static async getLolLastMatchHistoryId(region, accountId) {
        const response = new Array();
        await axios.get(`https://${region}.api.riotgames.com/lol/match/v5/matches/by-puuid/${accountId}/ids?start=0&count=2`, {
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
    static async getTftLastMatchHistoryId(region, accountId) {
        const response = new Array();
        await axios.get(`https://${region}.api.riotgames.com/tft/match/v1/matches/by-puuid/${accountId}/ids?start=0&count=2`, {
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
    static async getTftMatchesIds(region, accountId) {
        const response = new Array();
        await axios.get(`https://${region}.api.riotgames.com/tft/match/v1/matches/by-puuid/${accountId}/ids?start=0&count=10`, {
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
        await axios.get(`https://${region}.api.riotgames.com/tft/match/v1/matches/by-puuid/${accountId}/ids?start=0&count=10`, {
            headers: RiotRequestsManager.getRequestHeader()
        }).then(result => {
            if (RiotRequestsManager.isRequestSuccess(result.status)) {
                isMatchesFound = result.data.length > 1;
            }
        });
        return Promise.resolve(isMatchesFound);
    }
    static async getTftMatchesByIds(matchesIds, region, summonerId) {
        const matches = new Array();
        for (let i = 0; i < matchesIds.length; i++) {
            await axios.get(`https://${region}.api.riotgames.com/tft/match/v1/matches/${matchesIds[i]}`, {
                headers: RiotRequestsManager.getRequestHeader()
            }).then(result => {
                if (RiotRequestsManager.isRequestSuccess(result.status)) {
                    try {
                        const gameInfo = result.data;
                        const playerInstance = gameInfo.info.participants.filter((item) => {
                            return item.puuid == summonerId;
                        })[0];
                        const champions = new Array();
                        const augments = new Array();
                        playerInstance.units.forEach((item) => {
                            try {
                                const championInfo = tftChampions.filter((champion) => champion.id == item.character_id)[0];
                                const items = new Array();
                                item.itemNames.forEach((item) => {
                                    const itemToInsert = tftItems.filter((target) => target.id == item)[0];
                                    items.push(itemToInsert.image);
                                });
                                champions.push({
                                    image: championInfo.image,
                                    items: items
                                });
                            }
                            catch (ex) {
                                console.error(ex);
                            }
                        });
                        playerInstance.augments.forEach((item) => {
                            try {
                                const augment = tftAugments.filter((aug) => aug.id == item)[0];
                                augments.push({
                                    name: augment.name,
                                    image: augment.image,
                                });
                            }
                            catch (ex) {
                                console.error(ex);
                            }
                        });
                        matches.push({
                            id: matchesIds[i],
                            date: this.formatTimestampToDate(gameInfo.info.gameCreation),
                            placement: playerInstance.placement,
                            units: champions,
                            augments: augments
                        });
                    }
                    catch (ex) {
                        console.error(ex);
                    }
                }
            });
            await this.delay(100);
        }
        return Promise.resolve(matches);
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
    static async getLolMatchById(matchId, region, summonerId, serverCode) {
        let matchResponse = null;
        await axios.get(`https://${region}.api.riotgames.com/lol/match/v5/matches/${matchId}`, {
            headers: RiotRequestsManager.getRequestHeader()
        }).then(result => {
            if (RiotRequestsManager.isRequestSuccess(result.status)) {
                matchResponse = result.data;
            }
        });
        const players = new Array();
        const playerInstance = matchResponse.info.participants.filter((item) => {
            return item.puuid == summonerId;
        })[0];
        const champion = championsList.filter((item) => {
            return item.key == `${playerInstance.championId}`;
        })[0];
        matchResponse.info.participants.forEach((player) => {
            const items = new Array();
            const champion = championsList.filter((item) => {
                return item.key == `${player.championId}`;
            })[0];
            items.push(`https://ddragon.leagueoflegends.com/cdn/${imagesVersion}/img/item/${player.item0}.png`);
            items.push(`https://ddragon.leagueoflegends.com/cdn/${imagesVersion}/img/item/${player.item1}.png`);
            items.push(`https://ddragon.leagueoflegends.com/cdn/${imagesVersion}/img/item/${player.item2}.png`);
            items.push(`https://ddragon.leagueoflegends.com/cdn/${imagesVersion}/img/item/${player.item3}.png`);
            items.push(`https://ddragon.leagueoflegends.com/cdn/${imagesVersion}/img/item/${player.item4}.png`);
            items.push(`https://ddragon.leagueoflegends.com/cdn/${imagesVersion}/img/item/${player.item5}.png`);
            players.push({
                items: items,
                champion: {
                    name: champion.name,
                    image: `https://ddragon.leagueoflegends.com/cdn/${imagesVersion}/img/champion/${champion.name.replace(" ", "")}.png`,
                },
                kills: player.kills,
                assists: player.assists,
                deaths: player.deaths,
                farm: player.totalMinionsKilled,
                gold: player.goldEarned,
            });
        });
        return Promise.resolve({
            id: matchId,
            creationTimestamp: this.formatTimestampToDate(matchResponse.info.gameCreation),
            mode: matchResponse.info.gameMode,
            duration: this.formatMinutesToMinutesAndSeconds(matchResponse.info.gameDuration),
            isVictory: playerInstance.win,
            champion: {
                name: champion.name,
                image: `https://ddragon.leagueoflegends.com/cdn/${imagesVersion}/img/champion/${champion.name.replace(" ", "")}.png`
            },
            players: players
        });
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