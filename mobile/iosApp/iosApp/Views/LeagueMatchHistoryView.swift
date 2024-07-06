//
//  LeagueMatchHistoryView.swift
//  iosApp
//
//  Created by Yazan Tarifi on 06/07/2024.
//  Copyright © 2024 orgName. All rights reserved.
//

import SwiftUI
import shared

struct LeagueMatchHistoryView: View {
    
    let match: LegoraMatch
    var columns: [GridItem] {
        Array(repeatElement(GridItem(.flexible()), count: 2))
    }
    
    var body: some View {
        VStack {
            
            HStack {
                VStack {
                    HStack {
                        Text("Date : \(match.creationTimestamp ?? "")")
                        Spacer()
                    }
                    
                    HStack {
                        Text("Mode : \(match.mode ?? "")")
                        Spacer()
                    }
                }
                
                Spacer()
                
                Text("\((match.isVictory != nil) ? "Victory" : "Defeat")")
            }
            .padding(2)
            
            HStack {
                VStack {
                    AsyncImage(url: URL(string: match.champion?.image ?? "")) { image in
                        image.resizable()
                    } placeholder: {
                        Color.red
                    }
                    .frame(
                        width: 70,
                        height: 70
                    )
                    .clipShape(.rect(cornerRadius: 100))
                    
                    Text(match.champion?.name ?? "")
                }
                .padding(2)
                
                
                Spacer()
                
                LazyHGrid(rows: columns, spacing: 2) {
                    ForEach(0...(match.items?.count ?? 0) - 1, id: \.self) { item in
                        AsyncImage(url: URL(string: match.items?[item] ?? "")) { image in
                            image.resizable()
                        } placeholder: {
                            Color.red
                        }
                        .frame(
                            width: 30,
                            height: 30
                        )
                        .clipShape(.rect(cornerRadius: 10))
                    }
                }
                .padding(2)
            }
            
            HStack {
                Text("\(match.kills ?? 0)/\(match.deaths ?? 0)/\(match.assists ?? 0)")
                Spacer()
                Text("Farm : \(match.farm ?? 0)")
                Spacer()
                Text("Gold : \(match.getGoldValue())")
            }
            .padding(2)
            
        }
        .padding(3)
    }
}
