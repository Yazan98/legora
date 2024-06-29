//
//  ChampionsScreen.swift
//  iosApp
//
//  Created by Yazan Tarifi on 29/06/2024.
//  Copyright © 2024 orgName. All rights reserved.
//

import SwiftUI

struct ChampionsScreen: View {
    
    let spacing: CGFloat = 20
    let columnCount = 2
    @ObservedObject var viewModel: ChampionsViewModel

    var body: some View {
        getScreenContent()
            .onAppear {
                viewModel.getChampionsList()
            }
    }
    
    @ViewBuilder
    private func getScreenContent() -> some View {
        if viewModel.isLoading {
            LegoraLoadingView()
        } else {
            var columns: [GridItem] {
                Array(repeatElement(GridItem(.flexible()), count: columnCount))
            }
            
            if !viewModel.championsList.isEmpty {
                GeometryReader { proxy in
                    ScrollView {
                        LazyVGrid(columns: columns, spacing: spacing) {
                            ForEach(0...viewModel.championsList.count - 1, id: \.self) { item in
                                let isItemFullWidth =
                                viewModel.championsList[item].getType() == .freeToPlay ||
                                viewModel.championsList[item].getType() == .title
                                
                                if isItemFullWidth {
                                    Color.clear
                                                                .frame(minHeight: 100)
                                                                .overlay(alignment: .leading) {
                                                                    Text("\(viewModel.championsList[item].getType()) \(item)")
                                                                        .frame(maxHeight: .infinity)
                                                                        .frame(width: proxy.size.width)
                                                                        .background(Color.gray)
                                                                        .foregroundColor(.white)
                                                                        .cornerRadius(8)

                                                                }
                                                            Color.clear
                                } else {
                                    Text("\(viewModel.championsList[item].getType()) \(item)")
                                                                .frame(width: cellWidth(for: proxy.size))
                                                                .frame(minHeight: 100)
                                                                .background(Color.green)
                                                                .foregroundColor(.white)
                                                                .cornerRadius(8)
                                                                .padding()
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
