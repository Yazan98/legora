//
//  LegoraApplicationUtils.swift
//  iosApp
//
//  Created by Yazan Tarifi on 25/06/2024.
//  Copyright © 2024 orgName. All rights reserved.
//

import Foundation
import shared

public class LegoraApplicationUtils {
    
    public static func isUserLoggedIn() -> Bool {
        return ApplicationDependenciesManager.shared.getStorageInstance()?.isUserLoggedIn() ?? false
    }
    
}
