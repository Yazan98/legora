//
//  TitleWidget.swift
//  iosApp
//
//  Created by Yazan Tarifi on 02/07/2024.
//  Copyright © 2024 orgName. All rights reserved.
//

import SwiftUI

struct TitleWidget: View {
    
    let title: String
    
    var body: some View {
        HStack {
            Text(title)
                .font(.headline)
            
            Spacer()
        }
        .padding()
    }
}

