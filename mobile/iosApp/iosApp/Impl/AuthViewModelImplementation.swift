//
//  AuthViewModelImplementation.swift
//  iosApp
//
//  Created by Yazan Tarifi on 28/06/2024.
//  Copyright © 2024 orgName. All rights reserved.
//

import Foundation

public protocol AuthViewModelImplementation {
    
    func onRegisterNavigationListener(navigationListener: AuthScreenListener)
    
    func onLoginAccount(email: String, password: String)
    
    func onRegisterAccount(
        email: String,
        password: String,
        gameUsername: String,
        serverName: String,
        serverRegion: String
    )
    
}
