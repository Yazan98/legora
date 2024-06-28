//
//  LegoraLoadingView.swift
//  iosApp
//
//  Created by Yazan Tarifi on 28/06/2024.
//  Copyright © 2024 orgName. All rights reserved.
//

import SwiftUI

struct LegoraLoadingView: View {
    var body: some View {
        VStack {
            ProgressView()
                .progressViewStyle(.circular)
        }
    }
}

#Preview {
    LegoraLoadingView()
}
