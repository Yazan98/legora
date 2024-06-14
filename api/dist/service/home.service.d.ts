import { HomeWidget } from "../home/home.widget.js";
export declare class HomeService {
    private userRepository;
    getHomeFeedTree(userId: number): Promise<Array<HomeWidget>>;
    private getSecondPageMostPopularPlayersList;
    private getFirstPageMostPopularPlayersList;
    private getHomeTipsWidget;
    private getFirstPageLatestNewsWidget;
    private getSecondPageLatestNewsWidget;
}
