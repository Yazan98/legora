//
//  AccountScreen.swift
//  iosApp
//
//  Created by Yazan Tarifi on 29/06/2024.
//  Copyright © 2024 orgName. All rights reserved.
//

import SwiftUI
import shared

struct AccountScreen: View {
    
    @ObservedObject var viewModel: AccountViewModel

    var body: some View {
        VStack {
            getScreenContent()
        }
        .onAppear {
            viewModel.getAccountInfo()
        }
    }
    
    @ViewBuilder
    private func getScreenContent() -> some View {
        if viewModel.isLoading {
            LegoraLoadingView()
        } else {
            if viewModel.accountItems.isEmpty == false {
                ScrollView(showsIndicators: false) {
                    LazyVStack {
                        ForEach(0...viewModel.accountItems.count - 1, id: \.self) { item in
                            getItemByIndex(index: item)
                        }
                    }
                }
                .ignoresSafeArea()
            }
        }
    }
    
    @ViewBuilder
    private func getItemByIndex(index: Int) -> some View {
        switch (viewModel.accountItems[index].getType()) {
        case .title:
            TitleWidget(title: (viewModel.accountItems[index] as! AccountTitle).title)
        case .header:
            AccountHeaderWidget(header: (viewModel.accountItems[index] as! AccountInfoResponse))
        case .lolMatchHistory:
            LeagueMatchHistoryView(match: (viewModel.accountItems[index] as! LegoraMatch))
        default:
            Text("Another WIdget")
//            TitleWidget(title: (viewModel.accountItems[index] as! AccountTitle).title)
        }
    }
    
}
