import axios from "axios";
import {RiotRequestsManager} from "./riot.requests.manager.js";
import {LolMarchPlayerInfo, LolMatchesResponse, LolMatchInfo} from "../response/custom/lol.matches.response.js";
import {MatchListResponse} from "../response/riot/match.list.response.js";
import {championsList, imagesVersion, tftAugments, tftChampions, tftItems} from "../app.js";
import {TftMatchAugment, TftMatchesResponse, TftMatchUnit} from "../response/custom/tft.matches.response.js";
import {TftMatchInfo} from "../response/riot/tft.match.info.js";
import {SummonerAccountsManager} from "./summoner.accounts.manager.js";

export class MatchManager {
    static async isLolMatchesFound(region: string, accountId: string): Promise<boolean> {
        let isMatchesFound = false;
        await axios.get<Array<string>>(`https://${region}.api.riotgames.com/lol/match/v5/matches/by-puuid/${accountId}/ids?start=0&count=2`, {
            headers: RiotRequestsManager.getRequestHeader()
        }).then(result => {
            if (RiotRequestsManager.isRequestSuccess(result.status)) {
                isMatchesFound = result.data.length > 1;
            }
        })

        return Promise.resolve(isMatchesFound);
    }

    static async getLolMatchesIds(region: string, accountId: string): Promise<Array<string>> {
        const response = new Array<string>();
        await axios.get<Array<string>>(`https://${region}.api.riotgames.com/lol/match/v5/matches/by-puuid/${accountId}/ids?start=0&count=10`, {
            headers: RiotRequestsManager.getRequestHeader()
        }).then(result => {
            if (RiotRequestsManager.isRequestSuccess(result.status)) {
                for (let i = 0; i < result.data.length; i++) {
                    response.push(result.data[i]);
                }
            }
        })

        return Promise.resolve(response);
    }

    static async getLolLastMatchHistoryId(region: string, accountId: string): Promise<Array<string>> {
        const response = new Array<string>();
        await axios.get<Array<string>>(`https://${region}.api.riotgames.com/lol/match/v5/matches/by-puuid/${accountId}/ids?start=0&count=2`, {
            headers: RiotRequestsManager.getRequestHeader()
        }).then(result => {
            if (RiotRequestsManager.isRequestSuccess(result.status)) {
                for (let i = 0; i < result.data.length; i++) {
                    response.push(result.data[i]);
                }
            }
        })

        return Promise.resolve(response);
    }

    static async getTftLastMatchHistoryId(region: string, accountId: string): Promise<Array<string>> {
        const response = new Array<string>();
        await axios.get<Array<string>>(`https://${region}.api.riotgames.com/tft/match/v1/matches/by-puuid/${accountId}/ids?start=0&count=2`, {
            headers: RiotRequestsManager.getRequestHeader()
        }).then(result => {
            if (RiotRequestsManager.isRequestSuccess(result.status)) {
                for (let i = 0; i < result.data.length; i++) {
                    response.push(result.data[i]);
                }
            }
        })

        return Promise.resolve(response);
    }

    static async getTftMatchesIds(region: string, accountId: string): Promise<Array<string>> {
        const response = new Array<string>();
        await axios.get<Array<string>>(`https://${region}.api.riotgames.com/tft/match/v1/matches/by-puuid/${accountId}/ids?start=0&count=10`, {
            headers: RiotRequestsManager.getRequestHeader()
        }).then(result => {
            if (RiotRequestsManager.isRequestSuccess(result.status)) {
                for (let i = 0; i < result.data.length; i++) {
                    response.push(result.data[i]);
                }
            }
        })

        return Promise.resolve(response);
    }

    static async isTftMatchesFound(region: string, accountId: string): Promise<boolean> {
        let isMatchesFound = false;
        await axios.get<Array<string>>(`https://${region}.api.riotgames.com/tft/match/v1/matches/by-puuid/${accountId}/ids?start=0&count=10`, {
            headers: RiotRequestsManager.getRequestHeader()
        }).then(result => {
            if (RiotRequestsManager.isRequestSuccess(result.status)) {
                isMatchesFound = result.data.length > 1;
            }
        })

        return Promise.resolve(isMatchesFound);
    }

    static async getTftMatchesByIds(matchesIds: Array<string>, region: string, summonerId: string): Promise<Array<TftMatchesResponse>> {
        const matches = new Array<TftMatchesResponse>();
        for (let i = 0; i < matchesIds.length; i++) {
            await axios.get<TftMatchInfo>(`https://${region}.api.riotgames.com/tft/match/v1/matches/${matchesIds[i]}`, {
                headers: RiotRequestsManager.getRequestHeader()
            }).then(result => {
                if (RiotRequestsManager.isRequestSuccess(result.status)) {
                    try {
                        const gameInfo = result.data;
                        const playerInstance = gameInfo.info.participants.filter((item) => {
                            return item.puuid == summonerId;
                        })[0];

                        const champions = new Array<TftMatchUnit>();
                        const augments = new Array<TftMatchAugment>();
                        playerInstance.units.forEach((item) => {
                            try {
                                const championInfo = tftChampions.filter((champion) => champion.id == item.character_id)[0];
                                const items = new Array<string>();
                                item.itemNames.forEach((item) => {
                                    const itemToInsert = tftItems.filter((target) => target.id == item)[0];
                                    items.push(itemToInsert.image);
                                });

                                champions.push({
                                    image: championInfo.image,
                                    items: items
                                })
                            } catch (ex) {
                                console.error(ex)
                            }
                        })

                        playerInstance.augments.forEach((item) => {
                            try {
                                const augment = tftAugments.filter((aug) => aug.id == item)[0];
                                augments.push({
                                    name: augment.name,
                                    image: augment.image,
                                })
                            } catch (ex) {
                                console.error(ex)
                            }
                        })

                        matches.push({
                            id: matchesIds[i],
                            date: this.formatTimestampToDate(gameInfo.info.gameCreation),
                            placement: playerInstance.placement,
                            units: champions,
                            augments: augments
                        })
                    } catch (ex) {
                        console.error(ex)
                    }
                }
            })
            await this.delay(100);
        }
        return Promise.resolve(matches);
    }

    static async getMatchesByIds(matchesIds: Array<string>, region: string, summonerId: string): Promise<Array<LolMatchesResponse>> {
        const matches = new Array<LolMatchesResponse>();
        for (let i = 0; i < matchesIds.length; i++) {
            await axios.get<MatchListResponse>(`https://${region}.api.riotgames.com/lol/match/v5/matches/${matchesIds[i]}`, {
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

                    const items = new Array<string>();
                    items.push(`https://ddragon.leagueoflegends.com/cdn/${imagesVersion}/img/item/${playerInstance.item0}.png`)
                    items.push(`https://ddragon.leagueoflegends.com/cdn/${imagesVersion}/img/item/${playerInstance.item1}.png`)
                    items.push(`https://ddragon.leagueoflegends.com/cdn/${imagesVersion}/img/item/${playerInstance.item2}.png`)
                    items.push(`https://ddragon.leagueoflegends.com/cdn/${imagesVersion}/img/item/${playerInstance.item3}.png`)
                    items.push(`https://ddragon.leagueoflegends.com/cdn/${imagesVersion}/img/item/${playerInstance.item4}.png`)
                    items.push(`https://ddragon.leagueoflegends.com/cdn/${imagesVersion}/img/item/${playerInstance.item5}.png`)

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
                    })
                }
            })
            await this.delay(100);
        }

        return Promise.resolve(matches);
    }

    static async getLolMatchById(matchId: string, region: string, summonerId: string, serverCode: string): Promise<LolMatchInfo> {
        let matchResponse: MatchListResponse = null;
        await axios.get<MatchListResponse>(`https://${region}.api.riotgames.com/lol/match/v5/matches/${matchId}`, {
            headers: RiotRequestsManager.getRequestHeader()
        }).then(result => {
            if (RiotRequestsManager.isRequestSuccess(result.status)) {
                matchResponse = result.data;
            }
        })

        const players = new Array<LolMarchPlayerInfo>();
        const playerInstance = matchResponse.info.participants.filter((item) => {
            return item.puuid == summonerId;
        })[0];

        const champion = championsList.filter((item) => {
            return item.key == `${playerInstance.championId}`;
        })[0];

        matchResponse.info.participants.forEach((player) => {
                const items = new Array<string>();
                const champion = championsList.filter((item) => {
                    return item.key == `${player.championId}`;
                })[0];

                items.push(`https://ddragon.leagueoflegends.com/cdn/${imagesVersion}/img/item/${player.item0}.png`)
                items.push(`https://ddragon.leagueoflegends.com/cdn/${imagesVersion}/img/item/${player.item1}.png`)
                items.push(`https://ddragon.leagueoflegends.com/cdn/${imagesVersion}/img/item/${player.item2}.png`)
                items.push(`https://ddragon.leagueoflegends.com/cdn/${imagesVersion}/img/item/${player.item3}.png`)
                items.push(`https://ddragon.leagueoflegends.com/cdn/${imagesVersion}/img/item/${player.item4}.png`)
                items.push(`https://ddragon.leagueoflegends.com/cdn/${imagesVersion}/img/item/${player.item5}.png`)


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
                })
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

    static delay(ms: number): Promise<void> {
        return new Promise(resolve => setTimeout(resolve, ms));
    }

    static formatTimestampToDate(timestamp: number): string {
        // Step 1: Create a Date object from the timestamp
        const date = new Date(timestamp);

        // Step 2: Extract day, month, and year
        const day = date.getDate();
        const month = date.getMonth() + 1; // getMonth() returns 0-11, so add 1
        const year = date.getFullYear();

        // Step 3: Format the date as DD/MM/YYYY
        const formattedDate = `${String(month).padStart(2, '0')}/${String(day).padStart(2, '0')}/${year}`;

        return formattedDate;
    }

    static formatMinutesToMinutesAndSeconds(minutes: number): string {
        // Step 1: Extract whole minutes
        const wholeMinutes = Math.floor(minutes);

        // Step 2: Calculate remaining seconds (convert minutes fraction to seconds)
        const remainingSeconds = Math.round((minutes - wholeMinutes) * 60);

        // Step 3: Format the minutes and seconds to ensure they are always two digits
        const formattedMinutes = String(wholeMinutes).padStart(2, '0');
        const formattedSeconds = String(remainingSeconds).padStart(2, '0');

        // Step 4: Combine the minutes and seconds into a MM:SS format
        return `${formattedMinutes}:${formattedSeconds}`;
    }
}