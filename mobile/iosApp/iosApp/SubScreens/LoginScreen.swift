//
//  LoginScreen.swift
//  iosApp
//
//  Created by Yazan Tarifi on 28/06/2024.
//  Copyright © 2024 orgName. All rights reserved.
//

import SwiftUI

struct LoginScreen: View, AuthScreenListener {
    
    @ObservedObject private var viewModel: AuthViewModel = AuthViewModel()
    @State private var emailState: String = ""
    @State private var passwordState: String = ""
    @Binding var screenNavigation: Bool
    
    var body: some View {
        Group {
            if viewModel.isLoading {
                LegoraLoadingView()
            } else {
                getLoginBody()
            }
        }
        .onAppear {
            self.viewModel.onRegisterNavigationListener(navigationListener: self)
        }
    }
    
    @ViewBuilder
    private func getLoginBody() -> some View {
        ZStack {
            VStack(alignment: .leading) {
                HStack(alignment: .center) {
                    Spacer()
                    
                    if let image = UIImage(named: "RiotImage") {
                        Image(uiImage: image)
                            .resizable()
                            .scaledToFill()
                            .frame(width: 300, height: 200, alignment: .center)
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
                                
                
                Spacer()
            }
            
            // Bottom Container
            VStack {
                Spacer()
                
                if let image = UIImage(named: "RightArrow") {
                    if !emailState.isEmpty && !passwordState.isEmpty {
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
                            viewModel.onLoginAccount(
                                email: emailState,
                                password: passwordState
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
        
                NavigationLink(destination: RegisterScreen(screenNavigation: $screenNavigation)) {
                    Text("Create New Account")
                        .foregroundColor(.black)
                        .padding(3)
                }
            }
        }
    }
    
    func onLoginNavigationEnabled() {
        self.screenNavigation = true
    }
    
}
