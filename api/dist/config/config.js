import dotenv from "dotenv";
import path from "path";
import fs from "fs";
import { homedir } from "os";
class Configuration {
    constructor() { }
    static getInstance() {
        if (!Configuration.instace) {
            Configuration.instace = new Configuration();
            Configuration.instace.setupEnvVar();
            Configuration.instace.setup();
        }
        return Configuration.instace;
    }
    setup() {
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
        if (!fs.existsSync(this.dataDIR))
            fs.mkdirSync(this.dataDIR);
    }
    setupEnvVar() {
        const envPath = path.resolve("../" + process.env.NODE_ENV + ".env");
        console.log(envPath);
        if (fs.existsSync(envPath)) {
            dotenv.config({
                path: envPath,
            });
        }
        else {
            dotenv.config();
        }
    }
}
const { HOST, PORT, baseUrl, TOKEN } = Configuration.getInstance();
export { HOST, PORT, baseUrl, TOKEN };
//# sourceMappingURL=config.js.map