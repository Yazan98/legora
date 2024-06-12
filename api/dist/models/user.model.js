var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
var __metadata = (this && this.__metadata) || function (k, v) {
    if (typeof Reflect === "object" && typeof Reflect.metadata === "function") return Reflect.metadata(k, v);
};
import { Entity, PrimaryGeneratedColumn, Column, Index, BeforeInsert, BeforeUpdate } from "typeorm";
import * as bcrypt from 'bcrypt';
let UserModel = class UserModel {
    async hashPassword() {
        if (this.password) {
            const salt = await bcrypt.genSalt(10);
            this.password = await bcrypt.hash(this.password, salt);
        }
    }
    async validatePassword(password) {
        return bcrypt.compare(password, this.password);
    }
};
__decorate([
    Index(),
    PrimaryGeneratedColumn({ type: 'bigint', unsigned: true, name: 'id' }),
    __metadata("design:type", Number)
], UserModel.prototype, "id", void 0);
__decorate([
    Column({ nullable: false }),
    __metadata("design:type", String)
], UserModel.prototype, "name", void 0);
__decorate([
    Index(),
    Column({
        default: '',
        nullable: false,
        name: 'email',
        unique: true,
        update: true,
    }),
    __metadata("design:type", String)
], UserModel.prototype, "email", void 0);
__decorate([
    Column({
        default: "",
        length: 200,
        name: "password",
        update: false
    }),
    __metadata("design:type", String)
], UserModel.prototype, "password", void 0);
__decorate([
    Column({
        default: 'UNKNOWN',
        nullable: false,
        name: 'reg_useragent',
        update: false,
        length: 50,
    }),
    __metadata("design:type", String)
], UserModel.prototype, "regUserAgent", void 0);
__decorate([
    Column({
        default: '',
        nullable: false,
        name: 'summoner_name',
        update: false,
        length: 100,
    }),
    __metadata("design:type", String)
], UserModel.prototype, "summonerName", void 0);
__decorate([
    Column({
        default: '',
        nullable: false,
        name: 'summoner_region',
        update: false,
        length: 50,
    }),
    __metadata("design:type", String)
], UserModel.prototype, "summonerRegion", void 0);
__decorate([
    Column({
        default: '',
        nullable: false,
        name: 'summoner_server_code',
        update: false,
        length: 50,
    }),
    __metadata("design:type", String)
], UserModel.prototype, "summonerServerCode", void 0);
__decorate([
    Column({
        nullable: false,
        name: 'created_at',
        type: 'bigint',
    }),
    __metadata("design:type", Number)
], UserModel.prototype, "createdAt", void 0);
__decorate([
    BeforeInsert(),
    BeforeUpdate(),
    __metadata("design:type", Function),
    __metadata("design:paramtypes", []),
    __metadata("design:returntype", Promise)
], UserModel.prototype, "hashPassword", null);
UserModel = __decorate([
    Entity({ name: "accounts" })
], UserModel);
export { UserModel };
//# sourceMappingURL=user.model.js.map