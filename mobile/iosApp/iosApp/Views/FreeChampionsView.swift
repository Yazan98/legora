//
//  FreeChampionsView.swift
//  iosApp
//
//  Created by Yazan Tarifi on 05/07/2024.
//  Copyright © 2024 orgName. All rights reserved.
//

import SwiftUI
import shared

struct FreeChampionsView: View {
    
    let champions: [LegoraChampion]
    
    var body: some View {
        ScrollView(.horizontal, showsIndicators: false) {
            LazyHStack {
                ForEach(0...champions.count - 1, id: \.self) { item in
                    VStack {
                        ChampionItemView(champion: champions[item])
                    }
                    .frame(width: UIScreen.screenWidth / 4)
                }
            }
        }
        .padding()
    }
}
