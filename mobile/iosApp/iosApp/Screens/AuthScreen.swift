//
//  AuthScreen.swift
//  iosApp
//
//  Created by Yazan Tarifi on 25/06/2024.
//  Copyright © 2024 orgName. All rights reserved.
//

import SwiftUI

struct AuthScreen: View {
    var body: some View {
        NavigationView {
            LoginScreen()
        }
        .navigationBarBackButtonHidden(true)
        .navigationTitle("Legora")
        .navigationSplitViewStyle(.prominentDetail)
    }
}

#Preview {
    AuthScreen()
}
