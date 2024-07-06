//
//  MasteryChampionView.swift
//  iosApp
//
//  Created by Yazan Tarifi on 06/07/2024.
//  Copyright © 2024 orgName. All rights reserved.
//

import SwiftUI
import shared

struct MasteryChampionView: View {
    
    let champion: AccountMasteryChampion
    let isCenter: Bool
    
    var body: some View {
        VStack {
            VStack {
                AsyncImage(url: URL(string: champion.icon ?? "")) { image in
                    image.resizable()
                } placeholder: {
                    Color.red
                }
                .frame(
                    width: 70,
                    height: 70
                )
                .clipShape(.rect(cornerRadius: 100))
                
                Text(champion.name ?? "")
            }
            .padding(10)
        }
        .padding(isCenter ? .bottom : .top)
    }
}

