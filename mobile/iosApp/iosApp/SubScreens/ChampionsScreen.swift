//
//  ChampionsScreen.swift
//  iosApp
//
//  Created by Yazan Tarifi on 29/06/2024.
//  Copyright © 2024 orgName. All rights reserved.
//

import SwiftUI
import shared

struct ChampionsScreen: View {
    
    @ObservedObject var viewModel: ChampionsViewModel
    let spacing: CGFloat = 20
    let columnCount = 2
    var columns: [GridItem] {
        Array(repeatElement(GridItem(.flexible()), count: columnCount))
    }

    var body: some View {
        VStack {
            getScreenContent()
        }
        .onAppear {
            viewModel.getChampionsList()
        }
    }
    
    @ViewBuilder
    private func getScreenContent() -> some View {
        if viewModel.isLoading {
            LegoraLoadingView()
        } else {
            if !viewModel.championsList.isEmpty {
                GeometryReader { proxy in
                    ScrollView(showsIndicators: false) {
                        LazyVGrid(columns: columns, spacing: spacing) {
                            ForEach(0...viewModel.championsList.count - 1, id: \.self) { item in
                                let isItemFullWidth = viewModel.championsList[item].getType() == .freeToPlay ||
                                viewModel.championsList[item].getType() == .title
                                
                                if isItemFullWidth {
                                    Color.clear
                                        .frame(minHeight: 100)
                                        .overlay(alignment: .leading) {
                                                if (viewModel.championsList[item].getType() == .title) {
                                                    TitleWidget(title: (viewModel.championsList[item] as! ChampionTitleWidget).title)
                                                                .frame(maxHeight: .infinity)
                                                                .frame(width: proxy.size.width)
                                                } else {
                                                    FreeChampionsView(
                                                        champions: (viewModel.championsList[item] as! ChampionsFreeToPlayWidget).champions
                                                    )
                                                    .frame(width: UIScreen.screenWidth, height: 200)
                                                }
                                        }
                                    Color.clear
                                    
                                } else {
                                    ChampionItemView(champion: viewModel.championsList[item] as! LegoraChampion)
                                        .frame(width: cellWidth(for: proxy.size))
                                }
                            }
                        }
                    }
                }
                .padding()
            } else {
                LegoraLoadingView()
            }
        }
    }
    
    private func cellWidth(for size: CGSize) -> CGFloat {
        (size.width - ((CGFloat(columnCount) - 1) * spacing)) / CGFloat(columnCount)
    }
}
