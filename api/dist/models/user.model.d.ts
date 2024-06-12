export declare class UserModel {
    id: number;
    name: string;
    email: string;
    password: string;
    regUserAgent: string;
    summonerName: string;
    summonerRegion: string;
    summonerServerCode: string;
    createdAt: number;
    hashPassword(): Promise<void>;
    validatePassword(password: string): Promise<boolean>;
}
