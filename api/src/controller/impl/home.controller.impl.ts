import {HomeWidget} from "../../home/home.widget.js";

export interface HomeControllerImpl {
    getHomeFeedTree(userId: number): Promise<Array<HomeWidget>>;
}