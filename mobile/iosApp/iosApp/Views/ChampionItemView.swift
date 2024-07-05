//
//  ChampionItemView.swift
//  iosApp
//
//  Created by Yazan Tarifi on 05/07/2024.
//  Copyright © 2024 orgName. All rights reserved.
//

import SwiftUI
import shared
struct ChampionItemView: View {
    
    let champion: LegoraChampion
    
    var body: some View {
        VStack {
            AsyncImage(url: URL(string: champion.icon ?? "")) { image in
                image.resizable()
            } placeholder: {
                Color.red
            }
            .frame(
                width: champion.type == "lol" ? UIScreen.screenWidth / 5 : (UIScreen.screenWidth / 2) - 30,
                height: champion.type == "lol" ? UIScreen.screenWidth / 5 : 120
            )
            .clipShape(champion.type == "lol" ? .rect(cornerRadius: 90) : .rect(cornerRadius: 15))
            
            Text(champion.name ?? "")
                .multilineTextAlignment(.center)
                .frame(width: 128)
                .padding(2)
        }
        .padding()
    }
}
