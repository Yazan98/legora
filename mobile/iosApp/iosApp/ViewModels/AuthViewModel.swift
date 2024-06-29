//
//  AuthViewModel.swift
//  iosApp
//
//  Created by Yazan Tarifi on 28/06/2024.
//  Copyright © 2024 orgName. All rights reserved.
//

import Foundation
import SwiftUI
import shared

public class AuthViewModel : ObservableObject, AuthViewModelImplementation {
    
    public static let SERVER_NAME_KEY = "server"
    public static let SERVER_REGION_KEY = "region"
    
    @Published var isLoading: Bool = false
    private var navigationListener: AuthScreenListener? = nil
    private let loginRequestManager = ScreensDependencies.getLoginRequestManagerInstance()
    private let registerRequestManager = ScreensDependencies.getRegisterRequestManagerInstance()
    var selectionKey: String = ""

    
    public func onRegisterNavigationListener(navigationListener: AuthScreenListener) {
        self.navigationListener = navigationListener
    }
    
    public func getBottomSheetOptionsByKey(key: String) -> [AuthBottomSheetOption] {
        if key == AuthViewModel.SERVER_NAME_KEY {
            return RiotServerManager.shared.getServersList().map { server in
                AuthBottomSheetOption(name: server.name, key: server.key)
            }
        } else {
            return RiotServerManager.shared.getServerRegions().map { server in
                AuthBottomSheetOption(name: server.name, key: server.key)
            }
        }
    }
    
    public func getServerName(key: String) -> String {
        return RiotServerManager.shared.getServersList().filter { server in
            return server.key == key
        }[0].name
    }
    
    public func getServerRegion(key: String) -> String {
        return RiotServerManager.shared.getServerRegions().filter { server in
            return server.key == key
        }[0].name
    }
    
    public func onLoginAccount(email: String, password: String) {
        isLoading = true
        ApplicationDependenciesManager.shared.getDispatchQueueBackgroundInstance()?.async {
            LegoraSharedStorage.shared.requestsListener = IosLegoraRequestsManager()
            self.loginRequestManager?.getRequestInfo(
                requestBody: LoginRequestBody(email: email, password: password),
                onSuccess: { accountInfo in
                    if let accountResponse = accountInfo.data {
                        self.onAccountInfo(accountInfo: accountResponse)
                    }
                },
                onError: { error in
                    // Handle Error -> Demo
                })
        }
    }
    
    public func onRegisterAccount(
        email: String,
        password: String,
        gameUsername: String,
        serverName: String,
        serverRegion: String
    ) {
        isLoading = true
        ApplicationDependenciesManager.shared.getDispatchQueueBackgroundInstance()?.async {
            LegoraSharedStorage.shared.requestsListener = IosLegoraRequestsManager()
            self.registerRequestManager?.getRequestInfo(
                requestBody: RegisterAccountRequestBody(
                    email: email,
                    password: password,
                    summonerName: gameUsername,
                    region: serverRegion,
                    serverCode: serverName
                ), onSuccess: { accountInfo in
                    if let accountResponse = accountInfo.data {
                        self.onAccountInfo(accountInfo: accountResponse)
                    }
                }, onError: { error in
                    // Handle Error -> Demo
                })
        }
    }
    
    private func onAccountInfo(accountInfo: AuthResponse) {
        ApplicationDependenciesManager.shared.getStorageInstance()?.updateAccessToken(
            token: accountInfo.auth?.accessToken ?? ""
        )
        
        DispatchQueue.main.async {
            self.isLoading = false
            self.navigationListener?.onLoginNavigationEnabled()
        }
    }
    
}
