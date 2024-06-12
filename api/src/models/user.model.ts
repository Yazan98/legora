import {
    Entity,
    PrimaryGeneratedColumn,
    Column,
    Index,
    BeforeInsert,
    BeforeUpdate
} from "typeorm";
import * as bcrypt from 'bcrypt';

@Entity({ name: "accounts" })
export class UserModel {

    @Index()
    @PrimaryGeneratedColumn({ type: 'bigint', unsigned: true, name: 'id' })
    id: number;

    @Column({ nullable: false })
    name: string;

    @Index()
    @Column({
        default: '',
        nullable: false,
        name: 'email',
        unique: true,
        update: true,
    })
    email: string;

    @Column({
        default: "",
        length: 200,
        name: "password",
        update: false
    })
    password: string;

    @Column({
        default: 'UNKNOWN',
        nullable: false,
        name: 'reg_useragent',
        update: false,
        length: 50,
    })
    regUserAgent: string;

    @Column({
        default: '',
        nullable: false,
        name: 'summoner_name',
        update: false,
        length: 100,
    })
    summonerName: string;

    @Column({
        default: '',
        nullable: false,
        name: 'summoner_region',
        update: false,
        length: 50,
    })
    summonerRegion: string;

    @Column({
        default: '',
        nullable: false,
        name: 'summoner_server_code',
        update: false,
        length: 50,
    })
    summonerServerCode: string;

    @Column({
        nullable: false,
        name: 'created_at',
        type: 'bigint',
    })
    createdAt: number;

    @BeforeInsert()
    @BeforeUpdate()
    async hashPassword() {
        if (this.password) {
            const salt = await bcrypt.genSalt(10);
            this.password = await bcrypt.hash(this.password, salt);
        }
    }

    async validatePassword(password: string): Promise<boolean> {
        return bcrypt.compare(password, this.password);
    }

}
