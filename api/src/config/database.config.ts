import "reflect-metadata";
import { DataSource } from "typeorm";

import * as dotenv from "dotenv";
import {UserModel} from "../models/user.model.js";

dotenv.config({
    path: "../" + process.env.NODE_ENV + ".env",
    debug: true
});

const {
    DB_HOST,
    DB_PORT,
    DB_USERNAME,
    DB_PASSWORD,
    DB_DATABASE,
} = process.env;

export const AppDataSource = new DataSource({
    type: "postgres",
    host: DB_HOST,
    port: parseInt(DB_PORT || "5432"),
    username: DB_USERNAME,
    password: DB_PASSWORD,
    database: DB_DATABASE,
    synchronize: true,
    logging: true,
    entities: [
        UserModel
    ],
    migrations: [ "../" + "migration/*.ts"],
    subscribers: [],
});