# Legora Api
The Source Code of Legora Api to Communicate with Mobile Apps Built with ExpressJs, Typescript

# Supported Links
1. Users (Register User, Login User, Get User Info By Token)
2. Champions (Get LOL Champions, Get TFT Champions, Get LOL Champion Info)
3. Home (Get Feed Screen Widgets Based on Authenticated User)
4. Matches (Get Last 10 LOL Matches, Get Last 10 TFT Matches, Get LOL Match Info By Id)

# Response Example

1. Login Request
```
{
    "message": "Account Logged In Successfully",
    "status": 201,
    "data": {
        "account": {
            "id": 3,
            "name": "ShadowSilencer",
            "email": "test@gmail.com",
            "summonerName": "ShadowSilencer#1133",
            "summonerRegion": "europe",
            "summonerServerCode": "eun1",
            "createdAt": 1720283029662
        },
        "auth": {
            "accessToken": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJlbWFpbCI6InRlc3RAZ21haWwuY29tIiwiaWQiOjMsInR5cGUiOiJhY2Nlc3NfdG9rZW4iLCJpYXQiOjE3MjAyODMzMTUsImV4cCI6MTcyMDM2OTcxNSwiaXNzIjoiTGVnb3JhIEFwaSJ9.lDgr2urXHSobBtqhQo-PJ7sVSSuFdSbfpR1_j2k-bRQ",
            "refreshToken": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJlbWFpbCI6InRlc3RAZ21haWwuY29tIiwiaWQiOjMsInR5cGUiOiJyZWZyZXNoX3Rva2VuIiwiaWF0IjoxNzIwMjgzMzE1LCJpc3MiOiJMZWdvcmEgQXBpIn0.dagly33kefPkaYd7EbB-41Rrw5iJ-85nu96ncxibGpE"
        }
    }
}
```

2. LOL Champions List
```
{
    "message": "Champions Found Successfully!",
    "status": 200,
    "data": [
        {
            "id": 266,
            "name": "Aatrox",
            "icon": "https://ddragon.leagueoflegends.com/cdn/14.13.1/img/champion/Aatrox.png",
            "isFreeToPlay": false,
            "type": "lol"
        },
        {
            "id": 238,
            "name": "Zed",
            "icon": "https://ddragon.leagueoflegends.com/cdn/14.13.1/img/champion/Zed.png",
            "isFreeToPlay": false,
            "type": "lol"
        },
        {
            "id": 221,
            "name": "Zeri",
            "icon": "https://ddragon.leagueoflegends.com/cdn/14.13.1/img/champion/Zeri.png",
            "isFreeToPlay": false,
            "type": "lol"
        },
        {
            "id": 115,
            "name": "Ziggs",
            "icon": "https://ddragon.leagueoflegends.com/cdn/14.13.1/img/champion/Ziggs.png",
            "isFreeToPlay": false,
            "type": "lol"
        },
        {
            "id": 26,
            "name": "Zilean",
            "icon": "https://ddragon.leagueoflegends.com/cdn/14.13.1/img/champion/Zilean.png",
            "isFreeToPlay": false,
            "type": "lol"
        },
        {
            "id": 142,
            "name": "Zoe",
            "icon": "https://ddragon.leagueoflegends.com/cdn/14.13.1/img/champion/Zoe.png",
            "isFreeToPlay": false,
            "type": "lol"
        },
        {
            "id": 143,
            "name": "Zyra",
            "icon": "https://ddragon.leagueoflegends.com/cdn/14.13.1/img/champion/Zyra.png",
            "isFreeToPlay": false,
            "type": "lol"
        }
    ]
}
```

3. Account Info

```
{
    "message": "Account Info Found Successfully!",
    "status": 201,
    "data": {
        "user": {
            "id": 3,
            "name": "ShadowSilencer",
            "email": "test@gmail.com",
            "summonerName": "ShadowSilencer#1133",
            "summonerRegion": "europe",
            "summonerServerCode": "eun1",
            "createdAt": 1720283029662
        },
        "summonerInfo": {
            "level": 490,
            "coverImage": "https://ddragon.leagueoflegends.com/cdn/img/champion/splash/Zed_0.jpg",
            "name": "ShadowSilencer",
            "masteryPoints": 520,
            "accountId": "TBeC2kSLGeluYOiLh1Zgqqkbedv3ClsBaGbaV02wQ303oMk",
            "accountHash": "XPGZ4JFz4189uVWfEkkk9Ayd7tEt4oFAyn-mRRIT5FFSm_B3R2-M72MOl8aQs1kRI9Fz89YJ3Skmug",
            "isLolMatchesFound": true,
            "isTftMatchesFound": true,
            "summonerHighlightName": "ShadowSilencer",
            "serverHighlightName": "1133",
            "profileImage": "https://ddragon.leagueoflegends.com/cdn/14.13.1/img/profileicon/6570.png",
            "topChampionsMastery": [
                {
                    "icon": "https://ddragon.leagueoflegends.com/cdn/14.13.1/img/champion/Zed.png",
                    "name": "Zed",
                    "id": 238,
                    "level": 33,
                    "points": 384260
                },
                {
                    "icon": "https://ddragon.leagueoflegends.com/cdn/14.13.1/img/champion/Diana.png",
                    "name": "Diana",
                    "id": 131,
                    "level": 15,
                    "points": 183541
                },
                {
                    "icon": "https://ddragon.leagueoflegends.com/cdn/14.13.1/img/champion/Khazix.png",
                    "name": "Kha'Zix",
                    "id": 121,
                    "level": 12,
                    "points": 150187
                },
                {
                    "icon": "https://ddragon.leagueoflegends.com/cdn/14.13.1/img/champion/Katarina.png",
                    "name": "Katarina",
                    "id": 55,
                    "level": 12,
                    "points": 144337
                },
                {
                    "icon": "https://ddragon.leagueoflegends.com/cdn/14.13.1/img/champion/Lux.png",
                    "name": "Lux",
                    "id": 99,
                    "level": 10,
                    "points": 113469
                },
                {
                    "icon": "https://ddragon.leagueoflegends.com/cdn/14.13.1/img/champion/MissFortune.png",
                    "name": "Miss Fortune",
                    "id": 21,
                    "level": 10,
                    "points": 112072
                }
            ]
        }
    }
}
```

# Commands

## Run The Project Locally
1. Run the App from Terminal
2. Run the Included Docker Image

```
1. npm run dev

2. docker-compose up --build
```

## Run the Project on Server

#### Docker Installation
``` 
sudo apt update
sudo apt install apt-transport-https ca-certificates curl software-properties-common
curl -fsSL https://download.docker.com/linux/ubuntu/gpg | sudo apt-key add -
sudo add-apt-repository "deb [arch=amd64] https://download.docker.com/linux/ubuntu focal stable"
apt-cache policy docker-ce
sudo apt install docker-ce
```

#### Check Docker Service Running
```
sudo systemctl status docker
```

#### Push Build to Docker
```
docker build -t legora-api . --platform=linux/amd64
docker tag legora-api yt98/legora-api:latest
docker push yt98/legora-api:latest
```

#### Pull Docker Image on Server
```
docker pull yt98/legora-api:latest
```

#### Docker Stop Running Image
```
docker ps -a
docker stop {id}
```

#### Docker Image Start Command
```
docker compose up --build -d
```
