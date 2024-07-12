# Legora

> League of Legends Mobile Companion

## Overview
Welcome to the League of Legends Mobile Companion! This project is an open-source mobile application designed to Test out the Integration between ExpressJs Application and Mobile Applications. The app, built using Kotlin Multiplatform, is available for both iOS and Android. It integrates with a backend API powered by Express.js and TypeScript, providing rich data and functionalities.

![](https://github.com/Yazan98/legora/blob/main/images/Banner%20Image.png?raw=true)

## Riot Games Api Info
We have a Completed Article about Using Riot Games Apis to get Started and see All Curls that used inside this Project
[Article Link](https://medium.com/@yazantarifi98/unlocking-the-power-of-riot-api-fetching-summoner-and-champion-data-for-your-league-of-legends-app-9f364ae8b27e)

## Features
<strong>Feed Screen</strong>: First Screen to Show News, Popular Players
<strong>Summoner Info</strong>: Show All Info for Summoners including (Lol Matches, TFT Matches, Champions Mastery, Summoner Public Info)
<strong>Champions List</strong>: Show LOL, TFT Champions List and the Public Data for Each Champion
<strong>Accounts</strong>: Create Accounts to Connect to League Of Legends Accounts Using Riot Api Communication

![](https://github.com/Yazan98/legora/blob/main/images/Second%20Banner.png?raw=true)

## Riot Summoner Data Information
This Project Depends on the Api Project for Server Communication with Riot Api to get the data and parse it for Clients
1. Summoner Info
2. Match History
3. Champions Info (Info, Name, Icons)
4. Items Icons
5. Convert Response Keys to Json Arrays for Mobile Apps Parse

## Screenshots

#### Android

| ![](https://github.com/Yazan98/legora/blob/main/images/android/Screenshot_20240706_191903.png?raw=true)  | ![](https://github.com/Yazan98/legora/blob/main/images/android/Screenshot_20240706_192011.png?raw=true)  |  ![](https://github.com/Yazan98/legora/blob/main/images/android/Screenshot_20240706_192511.png?raw=true) | 
|---|---|---|
| ![](https://github.com/Yazan98/legora/blob/main/images/android/Screenshot_20240706_192524.png?raw=true)  |  ![](https://github.com/Yazan98/legora/blob/main/images/android/Screenshot_20240706_192542.png?raw=true) | ![](https://github.com/Yazan98/legora/blob/main/images/android/Screenshot_20240706_192557.png?raw=true)  | 
| ![](https://github.com/Yazan98/legora/blob/main/images/android/Screenshot_20240706_192613.png?raw=true)  |  ![](https://github.com/Yazan98/legora/blob/main/images/android/Screenshot_20240706_192644.png?raw=true) |  ![](https://github.com/Yazan98/legora/blob/main/images/android/Screenshot_20240706_192700.png?raw=true) | 


#### IOS

| ![](https://github.com/Yazan98/legora/blob/main/images/ios/Simulator%20Screenshot%20-%20iPhone%2015%20Pro%20Max%20-%202024-07-06%20at%2019-portrait%202.png?raw=true)  | ![](https://github.com/Yazan98/legora/blob/main/images/ios/Simulator%20Screenshot%20-%20iPhone%2015%20Pro%20Max%20-%202024-07-06%20at%2019-portrait%203.png?raw=true)  |  ![](https://github.com/Yazan98/legora/blob/main/images/ios/Simulator%20Screenshot%20-%20iPhone%2015%20Pro%20Max%20-%202024-07-06%20at%2019-portrait%204.png?raw=true) | 
|---|---|---|
| ![](https://github.com/Yazan98/legora/blob/main/images/ios/Simulator%20Screenshot%20-%20iPhone%2015%20Pro%20Max%20-%202024-07-06%20at%2019-portrait%205.png?raw=true)  |  ![](https://github.com/Yazan98/legora/blob/main/images/ios/Simulator%20Screenshot%20-%20iPhone%2015%20Pro%20Max%20-%202024-07-06%20at%2019-portrait.png?raw=true) | 


## Tech Stack

#### Mobile Applications:
1. Android App (Jetpack Compose)
2. IOS App (Swift UI)

#### Backend API:
Framework: Express.js
Language: TypeScript

## Android Application Info
1. Language : Kotlin
2. Shared Code : Kotlin Multiplatform
3. Dependency Injection : Hilt
4. Local Caching : Room Kotlin Multiplatform
5. Image Loading : Glide
6. Api Client Library : Ktor Client
7. UI : Jetpack Compose
8. Loading : Lottie Animation

## IOS Application Info
1. Language: Swift
2. Shared Code : Kotlin Multiplatform
3. Local Caching : Room Kotlin Multiplatform
4. Api Client Library : Ktor Client
5. UI : SwiftUI

## Api Application Info
1. Language: Typescript
2. Deployment: Docker, Docker Compose, Digital Ocean Droplet
3. Data Mapping: Summoner Info, Match History, Champions, DDragon Images and Champions Info
4. Create Users in Postgresql
5. Auth Tokens With JWT (Public Links, Authenticated Links Only)

# Api Details 
1. Example Response
2. Postman Apis Collection
3. Deployment Steps
4. Docker Image

> Before Run the Api Project make sure to Replace : TOKEN Env Variable with Riot Token from Your Account Dashboard

All of them Available inside Readme File in API Path
> Api Documentation [Link](https://github.com/Yazan98/legora/blob/main/api/README.md)

> Postman Collection [Link](https://github.com/Yazan98/legora/blob/main/Legora%20Api%20Postman%20Collection.json)

> Docker Compose Yml File for Server Deployment [Link](https://github.com/Yazan98/legora/blob/main/api/server/docker-compose.yml)

# License
This project is licensed under the MIT License - see the LICENSE file for details.

# Acknowledgements
Riot Games for the incredible League of Legends game and API.
Kotlin Multiplatform for enabling shared code across iOS and Android.
Express.js and TypeScript communities for the powerful backend framework.

# Opensource Project Info

Legora is an unofficial project and is not endorsed or affiliated with Riot Games, Inc. This app is a demo and open-source project created for educational and experimental purposes.

#### Key Points:

1. Not an Official Riot Games Project: This app is developed independently and has no direct connection to Riot Games or any of its official partners.
2. For Educational Use: The project is intended to showcase the capabilities of Kotlin Multiplatform for mobile development and Express.js with TypeScript for building APIs.

