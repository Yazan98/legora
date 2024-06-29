//
//  LegoraBottomSheetOptionsPicker.swift
//  iosApp
//
//  Created by Yazan Tarifi on 29/06/2024.
//  Copyright © 2024 orgName. All rights reserved.
//

import SwiftUI

struct LegoraBottomSheetOptionsPicker: View {
    
    @State var options: [AuthBottomSheetOption]
    var selectionListener: AuthSelectionListener
    
    var body: some View {
        ScrollView {
            VStack() {
                HStack {
                    Text("Server Picker")
                        .font(.system(size: 18))
                    
                    Spacer()
                }
                
                HStack {
                    Text("Pick your Account Server Information")
                        .font(.system(size: 14))
                    
                    Spacer()
                }
                
                LazyVStack(spacing: 5) {
                    ForEach(options, id: \.self) { item in
                        HStack {
                            Spacer()
                            Text(item.name)
                                .font(.system(size: 18))
                                .foregroundColor(Color.black)
                                .padding()
                            Spacer()
                        }
                        .padding()
                        .background(LegoraThemeStyle.getGreyColor().opacity(0.2))
                        .cornerRadius(10)
                        .padding(.horizontal, 20)
                        .padding(.top)
                        .onTapGesture {
                            selectionListener.onSelectOption(option: item)
                        }
                    }
                }
            }
        }
        .padding()
    }
}
