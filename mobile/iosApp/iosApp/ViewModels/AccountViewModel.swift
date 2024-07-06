//
//  AccountViewModel.swift
//  iosApp
//
//  Created by Yazan Tarifi on 06/07/2024.
//  Copyright © 2024 orgName. All rights reserved.
//

import Foundation
import shared

public class AccountViewModel : ObservableObject {
    
    @Published var isLoading: Bool = false
    @Published var accountItems: [AccountItem] = []
    
    private var accountItemsProvider: LegoraAccountItemsProvider? = ScreensDependencies.getAccountItemsProviderInstance()
    
    public func getAccountInfo() {
        if accountItems.isEmpty == false {
            return
        }
        
        isLoading = true
        DispatchQueue.global(qos: .background).async {
            self.accountItemsProvider?.getProfileInfo(onSuccess: { items in
                DispatchQueue.main.async {
                    self.isLoading = false
                    self.accountItems = items
                }
            })
        }
    }
    
}
