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
    
    var body: some View {
        TabView {
            FeedScreen()
                .tabItem {
                   Label("Home", systemImage: "house.fill")
                }
            
            ChampionsScreen(viewModel: championsViewModel)
                .tabItem {
                   Label("Champions", systemImage: "opticaldiscdrive.fill")
                }
            
            AccountScreen()
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
