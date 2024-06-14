import dotenv from "dotenv";
import path from "path";
import fs from "fs";
import { homedir } from "os";


class Configuration {
    private static instace: Configuration;
    public NODE_ENV: string;
    public HOST: string;
    public PORT: string;
    public TOKEN: string;
    public baseUrl: string;
    private LOG_LEVEL: string;
    public dataDIR: string;
    private dbConnUrl: string;

    private constructor() {}

    public static getInstance(): Configuration {

        if (!Configuration.instace) {
            Configuration.instace = new Configuration();
            Configuration.instace.setupEnvVar();
            Configuration.instace.setup();
        }

        return Configuration.instace;
    }

    private setup(){
        this.HOST = process.env.HOST ? process.env.HOST : "localhost";
        this.PORT = process.env.PORT ? process.env.PORT : "3000";
        this.TOKEN = process.env.TOKEN ? process.env.TOKEN : "";
        this.LOG_LEVEL = process.env.LOG_LEVEL ? process.env.LOG_LEVEL : "info";
        this.NODE_ENV = process.env.NODE_ENV ? process.env.NODE_ENV : "development";
        this.dbConnUrl = process.env.DB_URL;
        this.baseUrl = "http://" + this.HOST + ":" + this.PORT;

        this.dataDIR = process.env.DATA_DIR
            ? process.env.DATA_DIR
            : path.join(homedir(), "boilerplate");
        if (!fs.existsSync(this.dataDIR)) fs.mkdirSync(this.dataDIR);
    }

    private setupEnvVar(){
        // Enviroment  variable
        ////////////////////////
        const envPath = path.resolve(
            "../" + process.env.NODE_ENV + ".env",

        );

        console.log(envPath);

        if (fs.existsSync(envPath)) {
            dotenv.config({
                path: envPath,
            });
        } else {
            dotenv.config();
        }
    }

}

const {
    HOST,
    PORT,
    baseUrl,
    TOKEN
} = Configuration.getInstance();
export { HOST, PORT, baseUrl, TOKEN };