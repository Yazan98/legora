//
//  AccountHeaderWidget.swift
//  iosApp
//
//  Created by Yazan Tarifi on 06/07/2024.
//  Copyright © 2024 orgName. All rights reserved.
//

import SwiftUI
import shared

struct AccountHeaderWidget: View {
    
    let header: AccountInfoResponse
    
    var body: some View {
        VStack {
            AsyncImage(url: URL(string: header.summonerInfo?.coverImage ?? "")) { image in
                image.resizable()
            } placeholder: {
                Color.red
            }
            .frame(
                width: UIScreen.screenWidth,
                height: 250
            )
            .clipShape(.rect(cornerRadius: 0))
            .ignoresSafeArea()
            
            HStack {
                AsyncImage(url: URL(string: header.summonerInfo?.profileImage ?? "")) { image in
                    image.resizable()
                } placeholder: {
                    Color.red
                }
                .frame(
                    width: 85,
                    height: 85
                )
                .clipShape(.rect(cornerRadius: 100))
                .ignoresSafeArea()
                
                VStack {
                    HStack {
                        Text("\(header.summonerInfo?.summonerHighlightName ?? "")#\(header.summonerInfo?.serverHighlightName ?? "")")
                        Spacer()
                    }
                    
                    HStack {
                        Text("Level : \(header.summonerInfo?.level ?? 0) - Mastery Points : \(header.summonerInfo?.masteryPoints ?? 0)")
                        Spacer()
                    }
                }
                
                Spacer()
            }
            .padding()
            
            HStack {
                Text("Top Champions")
                    .padding()
                
                Spacer()
            }
            
            HStack {
                let masteryChampions = header.summonerInfo?.topChampionsMastery ?? []
                MasteryChampionView(champion: masteryChampions[1] as! AccountMasteryChampion, isCenter: false)
                MasteryChampionView(champion: masteryChampions[0] as! AccountMasteryChampion, isCenter: true)
                MasteryChampionView(champion: masteryChampions[2] as! AccountMasteryChampion, isCenter: false)
            }
        }
    }
    
}
