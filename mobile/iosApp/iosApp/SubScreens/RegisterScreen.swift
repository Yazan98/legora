//
//  RegisterScreen.swift
//  iosApp
//
//  Created by Yazan Tarifi on 28/06/2024.
//  Copyright © 2024 orgName. All rights reserved.
//

import SwiftUI

struct RegisterScreen: View, AuthScreenListener {
    
    @ObservedObject private var viewModel: AuthViewModel = AuthViewModel()
    @State private var emailState: String = ""
    @State private var passwordState: String = ""
    @State private var gameUsernameState: String = ""
    @State private var serverName: String = ""
    @State private var serverRegion: String = ""
    @Binding var screenNavigation: Bool
    
    var body: some View {
        Group {
            if viewModel.isLoading {
                LegoraLoadingView()
            } else {
                getRegisterScreenBody()
            }
        }
        .onAppear {
            self.viewModel.onRegisterNavigationListener(navigationListener: self)
        }
    }
    
    @ViewBuilder
    private func getRegisterScreenBody() -> some View {
        ZStack {
            VStack {
                HStack(alignment: .center) {
                    if let image = UIImage(named: "RiotImage") {
                        Image(uiImage: image)
                            .resizable()
                            .scaledToFill()
                            .frame(width: 200, height: 100, alignment: .center)
                            .clipped()
                    }
                    
                    Spacer()
                }
                
                
                TextField("Email Address", text: $emailState)
                        .padding()
                        .background(LegoraThemeStyle.getGreyColor().opacity(0.2))
                        .cornerRadius(10)
                        .padding(.horizontal, 20)
                        .foregroundColor(.black)
                        .font(.system(size: 18))
                
                SecureField("Password", text: $passwordState)
                        .padding()
                        .background(LegoraThemeStyle.getGreyColor().opacity(0.2))
                        .cornerRadius(10)
                        .padding(.horizontal, 20)
                        .font(.system(size: 18))
                        .foregroundColor(.black)
                        .padding(.top)
                
                TextField("Game Username", text: $gameUsernameState)
                        .padding()
                        .background(LegoraThemeStyle.getGreyColor().opacity(0.2))
                        .cornerRadius(10)
                        .padding(.horizontal, 20)
                        .foregroundColor(.black)
                        .font(.system(size: 18))
                        .padding(.top)

                // Pickers
                if serverName.isEmpty {
                    getTextPickerWidget(text: "Pick Server Name", isSelected: false)
                } else {
                    getTextPickerWidget(text: serverName, isSelected: true)
                }

                if serverRegion.isEmpty {
                    getTextPickerWidget(text: "Pick Server Region", isSelected: false)
                } else {
                    getTextPickerWidget(text: serverRegion, isSelected: true)
                }
                
                Spacer()
            }
            
            VStack {
                Spacer()
                if let image = UIImage(named: "RightArrow") {
                    if !emailState.isEmpty && !passwordState.isEmpty && !serverName.isEmpty && !serverRegion.isEmpty && !gameUsernameState.isEmpty {
                        Group {
                            Image(uiImage: image)
                                .resizable()
                                .renderingMode(.template)
                                .foregroundColor(LegoraThemeStyle.getPrimaryColor())
                                .scaledToFill()
                                .frame(width: 30, height: 30, alignment: .center)
                        }
                        .padding()
                        .overlay(
                            RoundedRectangle(cornerRadius: 16)
                                .stroke(LegoraThemeStyle.getPrimaryColor(), lineWidth: 2)
                        )
                        .onTapGesture {
                            viewModel.onRegisterAccount(
                                email: emailState,
                                password: passwordState,
                                gameUsername: gameUsernameState,
                                serverName: serverName,
                                serverRegion: serverRegion
                            )
                        }
                    } else {
                        Group {
                            Image(uiImage: image)
                                .resizable()
                                .renderingMode(.template)
                                .foregroundColor(LegoraThemeStyle.getGreyColor())
                                .scaledToFill()
                                .frame(width: 30, height: 30, alignment: .center)
                                .clipped()
                        }
                        .padding()
                        .overlay(
                            RoundedRectangle(cornerRadius: 16)
                                .stroke(LegoraThemeStyle.getGreyColor(), lineWidth: 2)
                        )
                    }
                }
            }
        }
    }
    
    @ViewBuilder
    private func getTextPickerWidget(text: String, isSelected: Bool) -> some View {
        HStack {
            Text(text)
                .font(.system(size: 18))
                .foregroundColor(isSelected ? .black : LegoraThemeStyle.getDarkGreyColor())
            
            Spacer()
            
            
            if let image = UIImage(named: "BottomArrow") {
                Image(uiImage: image)
                    .resizable()
                    .renderingMode(.template)
                    .foregroundColor(LegoraThemeStyle.getPrimaryColor())
                    .scaledToFill()
                    .frame(width: 20, height: 20, alignment: .center)
            }
        }
        .padding()
        .background(LegoraThemeStyle.getGreyColor().opacity(0.2))
        .cornerRadius(10)
        .padding(.horizontal, 20)
        .padding(.top)
    }
    
    func onLoginNavigationEnabled() {
        self.screenNavigation = true
    }
}
