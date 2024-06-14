import { HomeWidget } from "../home.widget.js";
export interface HomeWelcomeWidget extends HomeWidget {
    summonerHighlightName: string;
    serverHighlightName: string;
    accountHash: string;
    profileImage: string;
}
