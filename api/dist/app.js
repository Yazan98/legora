#!/usr/bin/env node
import express from 'express';
import rateLimit from 'express-rate-limit';
import xss from 'xss-clean';
import { AppsHeadersMiddleware } from "./config/apps.headers.middleware.js";
import { AuthMiddleware } from "./config/auth.middleware.js";
import { AppDataSource } from "./config/database.config.js";
import { baseUrl, HOST, PORT } from "./config/config.js";
import { AccountsController } from "./controller/accounts.controller.js";
import { RiotRequestsManager } from "./summoners/riot.requests.manager.js";
import { ChampionsController } from "./controller/champions.controller.js";
export let imagesVersion = '14.11.1';
export let championsList = new Array();
const app = express();
const limiter = rateLimit({
    windowMs: 15 * 60 * 1000,
    max: 100,
    message: 'You are not Allowed To Access This Api, Too many requests'
});
app.use(limiter);
app.use(xss());
app.use(express.json());
app.use((req, res, next) => {
    AppsHeadersMiddleware.onValidateAppsHeaders(req, res, next);
});
app.use((req, res, next) => {
    AuthMiddleware.onValidateAuthHeaders(req, res, next);
});
app.get('/ping', (req, res) => res.send('Working :P'));
new AccountsController().initControllerRoutes(app);
new ChampionsController().initControllerRoutes(app);
RiotRequestsManager.getCurrentAppVersion()
    .then((version) => {
    imagesVersion = version;
    getApplicationChampions(imagesVersion);
})
    .catch((ex) => {
    console.error(ex);
    imagesVersion = '14.11.1';
});
function getApplicationChampions(version) {
    RiotRequestsManager.getApplicationChampions(version)
        .then((result) => {
        result.forEach((item) => {
            championsList.push(item);
        });
    })
        .catch((ex) => {
        console.error(ex);
    });
}
AppDataSource.initialize()
    .then(async () => {
    app.listen(Number(PORT), HOST, () => console.log('Server is up and running in ' + baseUrl));
    console.log("Data Source has been initialized!");
})
    .catch((error) => console.log(error));
//# sourceMappingURL=app.js.map