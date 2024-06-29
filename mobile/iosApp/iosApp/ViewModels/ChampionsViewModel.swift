//
//  ChampionsViewModel.swift
//  iosApp
//
//  Created by Yazan Tarifi on 29/06/2024.
//  Copyright © 2024 orgName. All rights reserved.
//

import Foundation
import shared

public class ChampionsViewModel : ObservableObject {
    
    @Published var isLoading: Bool = false
    @Published var championsList: [ChampionItem] = []
    private let championsItemsProvider = ScreensDependencies.getChampionsItemsProviderInstance()
    
    public func getChampionsList() {
        if !championsList.isEmpty {
            return
        }
        
        isLoading = true
        DispatchQueue.global(qos: .background).async {
            self.championsItemsProvider?.getScreenItems(
                currentTimestamp: Date().toMillis(),
                onSuccess: { champions in
                    DispatchQueue.main.async {
                        self.isLoading = false
                        self.championsList.append(contentsOf: champions)
                    }
                }
            )
        }
    }
    
}
