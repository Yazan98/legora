//
//  AuthScreen.swift
//  iosApp
//
//  Created by Yazan Tarifi on 25/06/2024.
//  Copyright © 2024 orgName. All rights reserved.
//

import SwiftUI

struct AuthScreen: View {
    
    @State private var isScreenNavigationEnabled = false
    
    var body: some View {
        ZStack {
            NavigationLink(
                destination: HomeScreen(),
                isActive: $isScreenNavigationEnabled
            ) {
                EmptyView()
            }
            
            NavigationView {
                LoginScreen(screenNavigation: $isScreenNavigationEnabled)
            }
        }
        .navigationBarBackButtonHidden(true)
        .navigationTitle("Legora")
        .navigationSplitViewStyle(.prominentDetail)
    }
}

#Preview {
    AuthScreen()
}
