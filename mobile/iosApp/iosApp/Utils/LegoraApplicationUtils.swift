//
//  LegoraApplicationUtils.swift
//  iosApp
//
//  Created by Yazan Tarifi on 25/06/2024.
//  Copyright © 2024 orgName. All rights reserved.
//

import Foundation
import shared
import SwiftUI

public class LegoraApplicationUtils {
    
    public static func isUserLoggedIn() -> Bool {
        return ApplicationDependenciesManager.shared.getStorageInstance()?.isUserLoggedIn() ?? false
    }
    
}

extension Date {
    func toMillis() -> Int64! {
        return Int64(self.timeIntervalSince1970 * 1000)
    }
}

extension UIScreen{
   static let screenWidth = UIScreen.main.bounds.size.width
   static let screenHeight = UIScreen.main.bounds.size.height
   static let screenSize = UIScreen.main.bounds.size
}
