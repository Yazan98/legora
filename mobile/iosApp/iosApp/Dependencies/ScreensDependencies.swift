//
//  ScreensDependencies.swift
//  iosApp
//
//  Created by Yazan Tarifi on 28/06/2024.
//  Copyright © 2024 orgName. All rights reserved.
//

import Foundation
import shared

public class ScreensDependencies {
        
    private static var loginAccountRequestManager: LoginAccountRequestManager? = nil
    private static var registerAccountRequestManager: RegisterAccountRequestManager? = nil
        
    public static func getLoginRequestManagerInstance() -> LoginAccountRequestManager? {
        if loginAccountRequestManager == nil {
            loginAccountRequestManager = LoginAccountRequestManager(
                httpClient: ApplicationDependenciesManager.shared.getHttpClient(),
                requestHeaders: LegoraSharedStorage.shared.getApplicationHeaders(
                    appVersion: "1.0",
                    token: ApplicationDependenciesManager.shared.getStorageInstance()?.getAccessToken() ?? ""
                )
            )
        }
        
        return loginAccountRequestManager
    }
    
    public static func getRegisterRequestManagerInstance() -> RegisterAccountRequestManager? {
        if registerAccountRequestManager == nil {
            registerAccountRequestManager = RegisterAccountRequestManager(
                httpClient: ApplicationDependenciesManager.shared.getHttpClient(),
                requestHeaders: LegoraSharedStorage.shared.getApplicationHeaders(
                    appVersion: "1.0",
                    token: ApplicationDependenciesManager.shared.getStorageInstance()?.getAccessToken() ?? ""
                )
            )
        }
        
        return registerAccountRequestManager
    }
}
