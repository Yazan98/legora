# Legora

> League of Legends Mobile Companion

## Overview
Welcome to the League of Legends Mobile Companion! This project is an open-source mobile application designed to Test out the Integration between ExpressJs Application and Mobile Applications. The app, built using Kotlin Multiplatform, is available for both iOS and Android. It integrates with a backend API powered by Express.js and TypeScript, providing rich data and functionalities.

![](https://github.com/Yazan98/legora/blob/main/images/Banner%20Image.png?raw=true)

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

> All of them Available inside Readme File in API Path

# License
This project is licensed under the MIT License - see the LICENSE file for details.

# Acknowledgements
Riot Games for the incredible League of Legends game and API.
Kotlin Multiplatform for enabling shared code across iOS and Android.
Express.js and TypeScript communities for the powerful backend framework.


