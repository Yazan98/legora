//
//  HomeScreen.swift
//  iosApp
//
//  Created by Yazan Tarifi on 25/06/2024.
//  Copyright © 2024 orgName. All rights reserved.
//

import SwiftUI

struct HomeScreen: View {
    
    @StateObject private var championsViewModel = ChampionsViewModel()
    @StateObject private var accountViewModel = AccountViewModel()
    
    var body: some View {
        TabView {
            ChampionsScreen(viewModel: championsViewModel)
                .tabItem {
                   Label("Champions", systemImage: "opticaldiscdrive.fill")
                }
            
            AccountScreen(viewModel: accountViewModel)
                .tabItem {
                   Label("Account", systemImage: "person.fill")
                }
        }
        .navigationBarBackButtonHidden(true)
    }
}

#Preview {
    HomeScreen()
}
