import { AppDataSource } from "../config/database.config.js";
import { UserModel } from "../models/user.model.js";
import { SummonerAccountsManager } from "../riot/summoner.accounts.manager.js";
import { imagesVersion } from "../app.js";
import { MatchManager } from "../riot/match.manager.js";
export class HomeService {
    constructor() {
        this.userRepository = AppDataSource.getRepository(UserModel);
    }
    async getHomeFeedTree(userId) {
        const widgets = new Array();
        const user = await this.userRepository.findOne({ where: { id: userId } });
        const summonerInfo = await SummonerAccountsManager.getSummonerProfileByInfo(user.summonerName, user.summonerRegion, user.summonerServerCode);
        const lastLolMatch = await MatchManager.getLolLastMatchHistoryId(user.summonerRegion, summonerInfo.puuid);
        const lastTftMatch = await MatchManager.getTftLastMatchHistoryId(user.summonerRegion, summonerInfo.puuid);
        const lolMatchInfo = await MatchManager.getMatchesByIds(lastLolMatch, user.summonerRegion, summonerInfo.puuid);
        const tftMatchInfo = await MatchManager.getTftMatchesByIds(lastTftMatch, user.summonerRegion, summonerInfo.puuid);
        const welcomeWidget = {
            type: "welcome_widget",
            accountHash: summonerInfo.puuid,
            summonerHighlightName: user.summonerName.split('#')[0],
            serverHighlightName: user.summonerName.split('#')[1],
            profileImage: `https://ddragon.leagueoflegends.com/cdn/${imagesVersion}/img/profileicon/${summonerInfo.profileIconId}.png`
        };
        widgets.push(welcomeWidget);
        const latestNewsWidget = {
            type: "latest_news",
            items: this.getFirstPageLatestNewsWidget()
        };
        widgets.push(latestNewsWidget);
        const popularFirstPage = {
            type: "players",
            items: this.getFirstPageMostPopularPlayersList()
        };
        widgets.push(popularFirstPage);
        const matchHistoryWidget = {
            lolMatches: lolMatchInfo,
            tftMatches: tftMatchInfo,
            type: "match_history"
        };
        widgets.push(matchHistoryWidget);
        const secondLatestNewsWidget = {
            type: "latest_news",
            items: this.getSecondPageLatestNewsWidget()
        };
        widgets.push(secondLatestNewsWidget);
        const popularSecondPage = {
            type: "players",
            items: this.getSecondPageMostPopularPlayersList()
        };
        widgets.push(popularSecondPage);
        const tips = {
            type: "tips",
            tips: this.getHomeTipsWidget()
        };
        widgets.push(tips);
        return Promise.resolve(widgets);
    }
    getSecondPageMostPopularPlayersList() {
        return [
            {
                "game_key": "lol",
                "game_name": "League of Legends",
                "image": "https://example.com/lol_streamer_7.jpg",
                "name": "Uzi",
                "youtube_link": "https://youtube.com/channel/uzilol",
                "twitch_link": "https://twitch.tv/uzi",
                "twitter_link": "https://twitter.com/uzilol",
                "rank_name": "Challenger"
            },
            {
                "game_key": "lol",
                "game_name": "League of Legends",
                "image": "https://example.com/lol_streamer_8.jpg",
                "name": "Ruler",
                "youtube_link": "https://youtube.com/channel/rulerlol",
                "twitch_link": "https://twitch.tv/ruler",
                "twitter_link": "https://twitter.com/ruler",
                "rank_name": "Challenger"
            },
            {
                "game_key": "tft",
                "game_name": "Teamfight Tactics",
                "image": "https://example.com/tft_streamer_5.jpg",
                "name": "Kiyoon",
                "youtube_link": "https://youtube.com/channel/kiyoon",
                "twitch_link": "https://twitch.tv/kiyoon",
                "twitter_link": "https://twitter.com/kiyoontft",
                "rank_name": "Challenger"
            },
            {
                "game_key": "lol",
                "game_name": "League of Legends",
                "image": "https://example.com/lol_streamer_9.jpg",
                "name": "CoreJJ",
                "youtube_link": "https://youtube.com/channel/corejj",
                "twitch_link": "https://twitch.tv/corejj",
                "twitter_link": "https://twitter.com/corejj",
                "rank_name": "Grandmaster"
            },
            {
                "game_key": "tft",
                "game_name": "Teamfight Tactics",
                "image": "https://example.com/tft_streamer_6.jpg",
                "name": "Delight",
                "youtube_link": "https://youtube.com/channel/delighttft",
                "twitch_link": "https://twitch.tv/delighttft",
                "twitter_link": "https://twitter.com/delighttft",
                "rank_name": "Grandmaster"
            },
            {
                "game_key": "lol",
                "game_name": "League of Legends",
                "image": "https://example.com/lol_streamer_10.jpg",
                "name": "Yassuo",
                "youtube_link": "https://youtube.com/channel/yassuo",
                "twitch_link": "https://twitch.tv/yassuo",
                "twitter_link": "https://twitter.com/yassuo",
                "rank_name": "Challenger"
            },
            {
                "game_key": "tft",
                "game_name": "Teamfight Tactics",
                "image": "https://example.com/tft_streamer_7.jpg",
                "name": "Mismatched Socks",
                "youtube_link": "https://youtube.com/channel/mismatchedsocks",
                "twitch_link": "https://twitch.tv/mismatchedsocks",
                "twitter_link": "https://twitter.com/mismatchedsocks",
                "rank_name": "Challenger"
            },
            {
                "game_key": "lol",
                "game_name": "League of Legends",
                "image": "https://example.com/lol_streamer_11.jpg",
                "name": "Bwipo",
                "youtube_link": "https://youtube.com/channel/bwipo",
                "twitch_link": "https://twitch.tv/bwipo",
                "twitter_link": "https://twitter.com/bwipo",
                "rank_name": "Grandmaster"
            },
            {
                "game_key": "tft",
                "game_name": "Teamfight Tactics",
                "image": "https://example.com/tft_streamer_8.jpg",
                "name": "Jschritte",
                "youtube_link": "https://youtube.com/channel/jschritte",
                "twitch_link": "https://twitch.tv/jschritte",
                "twitter_link": "https://twitter.com/jschritte",
                "rank_name": "Grandmaster"
            },
            {
                "game_key": "lol",
                "game_name": "League of Legends",
                "image": "https://example.com/lol_streamer_12.jpg",
                "name": "TheShy",
                "youtube_link": "https://youtube.com/channel/theshy",
                "twitch_link": "https://twitch.tv/theshy",
                "twitter_link": "https://twitter.com/theshy",
                "rank_name": "Challenger"
            }
        ];
    }
    getFirstPageMostPopularPlayersList() {
        return [
            {
                "game_key": "lol",
                "game_name": "League of Legends",
                "image": "https://example.com/lol_streamer_1.jpg",
                "name": "Faker",
                "youtube_link": "https://youtube.com/channel/faker",
                "twitch_link": "https://twitch.tv/faker",
                "twitter_link": "https://twitter.com/sktt1faker",
                "rank_name": "Challenger"
            },
            {
                "game_key": "lol",
                "game_name": "League of Legends",
                "image": "https://example.com/lol_streamer_2.jpg",
                "name": "Doublelift",
                "youtube_link": "https://youtube.com/channel/doublelift",
                "twitch_link": "https://twitch.tv/doublelift",
                "twitter_link": "https://twitter.com/tl_doublelift",
                "rank_name": "Grandmaster"
            },
            {
                "game_key": "lol",
                "game_name": "League of Legends",
                "image": "https://example.com/lol_streamer_3.jpg",
                "name": "Tyler1",
                "youtube_link": "https://youtube.com/channel/tyler1",
                "twitch_link": "https://twitch.tv/loltyler1",
                "twitter_link": "https://twitter.com/loltyler1",
                "rank_name": "Challenger"
            },
            {
                "game_key": "tft",
                "game_name": "Teamfight Tactics",
                "image": "https://example.com/tft_streamer_1.jpg",
                "name": "Scarra",
                "youtube_link": "https://youtube.com/channel/scarra",
                "twitch_link": "https://twitch.tv/scarra",
                "twitter_link": "https://twitter.com/scarra",
                "rank_name": "Master"
            },
            {
                "game_key": "tft",
                "game_name": "Teamfight Tactics",
                "image": "https://example.com/tft_streamer_2.jpg",
                "name": "Soju",
                "youtube_link": "https://youtube.com/channel/soju",
                "twitch_link": "https://twitch.tv/soju",
                "twitter_link": "https://twitter.com/soju_tft",
                "rank_name": "Challenger"
            },
            {
                "game_key": "lol",
                "game_name": "League of Legends",
                "image": "https://example.com/lol_streamer_4.jpg",
                "name": "Sneaky",
                "youtube_link": "https://youtube.com/channel/sneaky",
                "twitch_link": "https://twitch.tv/sneaky",
                "twitter_link": "https://twitter.com/sneakylol",
                "rank_name": "Grandmaster"
            },
            {
                "game_key": "tft",
                "game_name": "Teamfight Tactics",
                "image": "https://example.com/tft_streamer_3.jpg",
                "name": "Becca",
                "youtube_link": "https://youtube.com/channel/becca",
                "twitch_link": "https://twitch.tv/beccastreams",
                "twitter_link": "https://twitter.com/beccachu",
                "rank_name": "Master"
            },
            {
                "game_key": "lol",
                "game_name": "League of Legends",
                "image": "https://example.com/lol_streamer_5.jpg",
                "name": "Bjergsen",
                "youtube_link": "https://youtube.com/channel/bjergsen",
                "twitch_link": "https://twitch.tv/bjergsen",
                "twitter_link": "https://twitter.com/bjergsen",
                "rank_name": "Challenger"
            },
            {
                "game_key": "tft",
                "game_name": "Teamfight Tactics",
                "image": "https://example.com/tft_streamer_4.jpg",
                "name": "Hafu",
                "youtube_link": "https://youtube.com/channel/hafu",
                "twitch_link": "https://twitch.tv/itshafu",
                "twitter_link": "https://twitter.com/itshafu",
                "rank_name": "Grandmaster"
            },
            {
                "game_key": "lol",
                "game_name": "League of Legends",
                "image": "https://example.com/lol_streamer_6.jpg",
                "name": "Caps",
                "youtube_link": "https://youtube.com/channel/caps",
                "twitch_link": "https://twitch.tv/caps",
                "twitter_link": "https://twitter.com/capslols",
                "rank_name": "Challenger"
            }
        ];
    }
    getHomeTipsWidget() {
        return [
            {
                "game_key": "lol",
                "game_name": "League of Legends",
                "tip_title": "Mastering Last Hitting",
                "tip_description": "Learn how to efficiently last hit minions to maximize your gold income. Focus on timing your attacks and positioning to ensure you get the kill.",
                "tip_image": "https://example.com/lol_last_hitting.jpg",
                "video_link": "https://youtube.com/watch?v=lol_last_hitting_guide",
                "difficulty": "Beginner",
                "likes_count": 789,
                "comments_count": 56,
                "created_timestamp": 1718368800
            },
            {
                "game_key": "valorant",
                "game_name": "Valorant",
                "tip_title": "Perfecting Your Aim",
                "tip_description": "Practice your aim daily with aim training tools and focus on crosshair placement. Aim for headshots and learn the recoil patterns of different weapons.",
                "tip_image": "https://example.com/valorant_aiming.jpg",
                "video_link": "https://youtube.com/watch?v=valorant_aiming_tips",
                "difficulty": "Intermediate",
                "likes_count": 1023,
                "comments_count": 89,
                "created_timestamp": 1718281800
            },
            {
                "game_key": "tft",
                "game_name": "Teamfight Tactics",
                "tip_title": "Building Strong Compositions",
                "tip_description": "Understand the synergies between champions to create strong compositions. Keep an eye on your opponent's setups and adapt your strategy accordingly.",
                "tip_image": "https://example.com/tft_compositions.jpg",
                "video_link": "https://youtube.com/watch?v=tft_comps_guide",
                "difficulty": "Advanced",
                "likes_count": 453,
                "comments_count": 34,
                "created_timestamp": 1718193900
            },
            {
                "game_key": "lor",
                "game_name": "Legends of Runeterra",
                "tip_title": "Managing Your Mana",
                "tip_description": "Learn to manage your mana efficiently to ensure you can play your cards when needed. Save spell mana for crucial moments and avoid overcommitting early on.",
                "tip_image": "https://example.com/lor_mana_management.jpg",
                "video_link": "https://youtube.com/watch?v=lor_mana_tips",
                "difficulty": "Beginner",
                "likes_count": 612,
                "comments_count": 48,
                "created_timestamp": 1718107200
            },
            {
                "game_key": "wildrift",
                "game_name": "League of Legends: Wild Rift",
                "tip_title": "Positioning in Team Fights",
                "tip_description": "Positioning is key in team fights. Stay behind your tanky teammates if you are a damage dealer and focus on avoiding enemy crowd control abilities.",
                "tip_image": "https://example.com/wildrift_teamfights.jpg",
                "video_link": "https://youtube.com/watch?v=wildrift_teamfight_positioning",
                "difficulty": "Intermediate",
                "likes_count": 875,
                "comments_count": 61,
                "created_timestamp": 1718020200
            },
            {
                "game_key": "lol",
                "game_name": "League of Legends",
                "tip_title": "Warding Effectively",
                "tip_description": "Place wards in key locations to gain vision and prevent enemy ambushes. Learn common warding spots and rotate your wards as the game progresses.",
                "tip_image": "https://example.com/lol_warding.jpg",
                "video_link": "https://youtube.com/watch?v=lol_warding_guide",
                "difficulty": "Advanced",
                "likes_count": 928,
                "comments_count": 83,
                "created_timestamp": 1717939200
            },
            {
                "game_key": "valorant",
                "game_name": "Valorant",
                "tip_title": "Effective Communication",
                "tip_description": "Use voice or text chat to share information with your team. Call out enemy positions, plan strategies, and coordinate your actions to improve team cohesion.",
                "tip_image": "https://example.com/valorant_communication.jpg",
                "video_link": "https://youtube.com/watch?v=valorant_comm_guide",
                "difficulty": "Beginner",
                "likes_count": 1042,
                "comments_count": 97,
                "created_timestamp": 1717853700
            },
            {
                "game_key": "tft",
                "game_name": "Teamfight Tactics",
                "tip_title": "Economy Management",
                "tip_description": "Managing your gold is crucial. Understand when to spend and when to save. Use the interest mechanics to maximize your income and outscale your opponents.",
                "tip_image": "https://example.com/tft_economy.jpg",
                "video_link": "https://youtube.com/watch?v=tft_economy_tips",
                "difficulty": "Intermediate",
                "likes_count": 712,
                "comments_count": 55,
                "created_timestamp": 1717761600
            },
            {
                "game_key": "lor",
                "game_name": "Legends of Runeterra",
                "tip_title": "Deck Building Strategies",
                "tip_description": "Focus on creating a balanced deck with a mix of spells and units. Prioritize synergies and think about how your deck will perform against common strategies.",
                "tip_image": "https://example.com/lor_deck_building.jpg",
                "video_link": "https://youtube.com/watch?v=lor_deck_building_guide",
                "difficulty": "Advanced",
                "likes_count": 549,
                "comments_count": 39,
                "created_timestamp": 1717675200
            },
            {
                "game_key": "wildrift",
                "game_name": "League of Legends: Wild Rift",
                "tip_title": "Maximizing Map Awareness",
                "tip_description": "Always keep an eye on the minimap. Knowing the positions of enemies and allies can help you make better decisions, avoid ganks, and set up plays.",
                "tip_image": "https://example.com/wildrift_map_awareness.jpg",
                "video_link": "https://youtube.com/watch?v=wildrift_map_awareness_tips",
                "difficulty": "Advanced",
                "likes_count": 698,
                "comments_count": 47,
                "created_timestamp": 1717587900
            }
        ];
    }
    getFirstPageLatestNewsWidget() {
        return [
            {
                "game_key": "lol",
                "game_name": "League of Legends",
                "event_image": "https://example.com/lol_event_1.jpg",
                "event_description": "Patch 13.14 brings major updates to champions and items.",
                "event_link": "https://riotgames.com/lol_patch_13_14",
                "event_created_timestamp": 1718368800,
                "is_video": false,
                "comments_count": 124,
                "likes_count": 567
            },
            {
                "game_key": "valorant",
                "game_name": "Valorant",
                "event_image": "https://example.com/valorant_event_1.jpg",
                "event_description": "Valorant introduces a new agent in Episode 6 Act 2.",
                "event_link": "https://riotgames.com/valorant_new_agent",
                "event_created_timestamp": 1718281800,
                "is_video": true,
                "video_link": "https://youtube.com/watch?v=agent_reveal",
                "video_duration": 210,
                "comments_count": 89,
                "likes_count": 723
            },
            {
                "game_key": "tft",
                "game_name": "Teamfight Tactics",
                "event_image": "https://example.com/tft_event_1.jpg",
                "event_description": "Mid-Set update introduces new traits and champions.",
                "event_link": "https://riotgames.com/tft_midset_update",
                "event_created_timestamp": 1718193900,
                "is_video": false,
                "comments_count": 76,
                "likes_count": 302
            },
            {
                "game_key": "lor",
                "game_name": "Legends of Runeterra",
                "event_image": "https://example.com/lor_event_1.jpg",
                "event_description": "New expansion 'Guardians of the Ancient' is now live!",
                "event_link": "https://riotgames.com/lor_guardians_expansion",
                "event_created_timestamp": 1718107200,
                "is_video": true,
                "video_link": "https://youtube.com/watch?v=expansion_trailer",
                "video_duration": 180,
                "comments_count": 98,
                "likes_count": 435
            },
            {
                "game_key": "wildrift",
                "game_name": "League of Legends: Wild Rift",
                "event_image": "https://example.com/wildrift_event_1.jpg",
                "event_description": "Wild Rift brings new skins and a ranked season update.",
                "event_link": "https://riotgames.com/wildrift_skins_ranked",
                "event_created_timestamp": 1718020200,
                "is_video": false,
                "comments_count": 67,
                "likes_count": 254
            },
            {
                "game_key": "valorant",
                "game_name": "Valorant",
                "event_image": "https://example.com/valorant_event_2.jpg",
                "event_description": "Valorant Champions Tour heads to Tokyo for the next stage.",
                "event_link": "https://riotgames.com/vct_tokyo_stage",
                "event_created_timestamp": 1717939200,
                "is_video": true,
                "video_link": "https://youtube.com/watch?v=vct_tokyo_highlights",
                "video_duration": 240,
                "comments_count": 155,
                "likes_count": 821
            },
            {
                "game_key": "lol",
                "game_name": "League of Legends",
                "event_image": "https://example.com/lol_event_2.jpg",
                "event_description": "The Summer Split of the LCS kicks off with thrilling matches.",
                "event_link": "https://riotgames.com/lol_lcs_summer_split",
                "event_created_timestamp": 1717853700,
                "is_video": false,
                "comments_count": 202,
                "likes_count": 634
            },
            {
                "game_key": "tft",
                "game_name": "Teamfight Tactics",
                "event_image": "https://example.com/tft_event_2.jpg",
                "event_description": "TFT hosts a global tournament with a massive prize pool.",
                "event_link": "https://riotgames.com/tft_global_tournament",
                "event_created_timestamp": 1717761600,
                "is_video": true,
                "video_link": "https://youtube.com/watch?v=tft_tournament_highlights",
                "video_duration": 300,
                "comments_count": 89,
                "likes_count": 520
            },
            {
                "game_key": "lor",
                "game_name": "Legends of Runeterra",
                "event_image": "https://example.com/lor_event_2.jpg",
                "event_description": "LoR patch 3.14 brings balance changes to the meta.",
                "event_link": "https://riotgames.com/lor_patch_3_14",
                "event_created_timestamp": 1717675200,
                "is_video": false,
                "comments_count": 56,
                "likes_count": 178
            },
            {
                "game_key": "wildrift",
                "game_name": "League of Legends: Wild Rift",
                "event_image": "https://example.com/wildrift_event_2.jpg",
                "event_description": "New champion 'Akshan' joins the Wild Rift roster.",
                "event_link": "https://riotgames.com/wildrift_akshan_release",
                "event_created_timestamp": 1717587900,
                "is_video": true,
                "video_link": "https://youtube.com/watch?v=wildrift_akshan_trailer",
                "video_duration": 150,
                "comments_count": 112,
                "likes_count": 470
            }
        ];
    }
    getSecondPageLatestNewsWidget() {
        return [
            {
                "game_key": "valorant",
                "game_name": "Valorant",
                "event_image": "https://example.com/valorant_event_3.jpg",
                "event_description": "Valorant's latest patch introduces weapon balance changes and new skins.",
                "event_link": "https://riotgames.com/valorant_patch_weapon_balance",
                "event_created_timestamp": 1717501500,
                "is_video": false,
                "comments_count": 137,
                "likes_count": 654
            },
            {
                "game_key": "lol",
                "game_name": "League of Legends",
                "event_image": "https://example.com/lol_event_3.jpg",
                "event_description": "The new champion 'Bel'Veth' is now available in the Summoner's Rift.",
                "event_link": "https://riotgames.com/lol_belveth_release",
                "event_created_timestamp": 1717412400,
                "is_video": true,
                "video_link": "https://youtube.com/watch?v=belveth_trailer",
                "video_duration": 180,
                "comments_count": 245,
                "likes_count": 789
            },
            {
                "game_key": "tft",
                "game_name": "Teamfight Tactics",
                "event_image": "https://example.com/tft_event_3.jpg",
                "event_description": "Set 8 brings new champions and synergies to Teamfight Tactics.",
                "event_link": "https://riotgames.com/tft_set_8_release",
                "event_created_timestamp": 1717326000,
                "is_video": false,
                "comments_count": 111,
                "likes_count": 411
            },
            {
                "game_key": "lor",
                "game_name": "Legends of Runeterra",
                "event_image": "https://example.com/lor_event_3.jpg",
                "event_description": "A new event, 'Spirit Blossom', begins in Legends of Runeterra.",
                "event_link": "https://riotgames.com/lor_spirit_blossom_event",
                "event_created_timestamp": 1717239600,
                "is_video": true,
                "video_link": "https://youtube.com/watch?v=spirit_blossom_event",
                "video_duration": 210,
                "comments_count": 77,
                "likes_count": 346
            },
            {
                "game_key": "wildrift",
                "game_name": "League of Legends: Wild Rift",
                "event_image": "https://example.com/wildrift_event_3.jpg",
                "event_description": "Wild Rift introduces 'The Ruination' event with exclusive rewards.",
                "event_link": "https://riotgames.com/wildrift_the_ruination",
                "event_created_timestamp": 1717153200,
                "is_video": false,
                "comments_count": 92,
                "likes_count": 402
            },
            {
                "game_key": "valorant",
                "game_name": "Valorant",
                "event_image": "https://example.com/valorant_event_4.jpg",
                "event_description": "Valorant's Night Market returns with discounts on weapon skins.",
                "event_link": "https://riotgames.com/valorant_night_market",
                "event_created_timestamp": 1717066800,
                "is_video": true,
                "video_link": "https://youtube.com/watch?v=night_market_event",
                "video_duration": 120,
                "comments_count": 158,
                "likes_count": 693
            },
            {
                "game_key": "lol",
                "game_name": "League of Legends",
                "event_image": "https://example.com/lol_event_4.jpg",
                "event_description": "World Championship 2024: Teams and schedules announced.",
                "event_link": "https://riotgames.com/lol_worlds_2024",
                "event_created_timestamp": 1716980400,
                "is_video": false,
                "comments_count": 276,
                "likes_count": 810
            },
            {
                "game_key": "tft",
                "game_name": "Teamfight Tactics",
                "event_image": "https://example.com/tft_event_4.jpg",
                "event_description": "TFT introduces new Little Legends and arenas in the latest update.",
                "event_link": "https://riotgames.com/tft_little_legends_update",
                "event_created_timestamp": 1716894000,
                "is_video": true,
                "video_link": "https://youtube.com/watch?v=little_legends_trailer",
                "video_duration": 140,
                "comments_count": 105,
                "likes_count": 390
            },
            {
                "game_key": "lor",
                "game_name": "Legends of Runeterra",
                "event_image": "https://example.com/lor_event_4.jpg",
                "event_description": "Legends of Runeterra unveils new champion cards in the latest expansion.",
                "event_link": "https://riotgames.com/lor_new_champions",
                "event_created_timestamp": 1716807600,
                "is_video": false,
                "comments_count": 69,
                "likes_count": 267
            },
            {
                "game_key": "wildrift",
                "game_name": "League of Legends: Wild Rift",
                "event_image": "https://example.com/wildrift_event_4.jpg",
                "event_description": "Wild Rift Season 4 kicks off with major changes to ranked gameplay.",
                "event_link": "https://riotgames.com/wildrift_season_4",
                "event_created_timestamp": 1716721200,
                "is_video": true,
                "video_link": "https://youtube.com/watch?v=wildrift_season_4_trailer",
                "video_duration": 180,
                "comments_count": 83,
                "likes_count": 431
            }
        ];
    }
}
//# sourceMappingURL=home.service.js.map