#!/usr/bin/env node
import { ChampionObject } from "./response/riot/champion.model.js";
import { TftAugment } from "./response/custom/tft.augment.js";
import { TftChampionInfo, TftItemInfo } from "./response/riot/tft.match.info.js";
export declare let imagesVersion: string;
export declare let championsList: ChampionObject[];
export declare let tftAugments: TftAugment[];
export declare let tftChampions: TftChampionInfo[];
export declare let tftItems: TftItemInfo[];
