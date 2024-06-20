# api
ExpressJs Application to Build an Api for Mobile Applications Powered By Riot Api

Commands
```
Run Locally
npm run dev

Build Docker Image
docker-compose up --build
```

Docker Installation
```
sudo apt update
sudo apt install apt-transport-https ca-certificates curl software-properties-common
curl -fsSL https://download.docker.com/linux/ubuntu/gpg | sudo apt-key add -
sudo add-apt-repository "deb [arch=amd64] https://download.docker.com/linux/ubuntu focal stable"
apt-cache policy docker-ce
sudo apt install docker-ce
```

Check Docker Service Running
```
sudo systemctl status docker
```

Push Build to Docker
```
docker build -t legora-api . --platform=linux/amd64
docker tag legora-api yt98/legora-api:latest
docker push yt98/legora-api:latest
```

Pull Docker Image on Server
```
docker pull yt98/legora-api:latest
```

Docker Image Start Command
```
docker compose up --build -d
```
