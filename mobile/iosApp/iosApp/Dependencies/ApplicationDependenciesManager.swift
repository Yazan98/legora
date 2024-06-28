//
//  ApplicationDependenciesManager.swift
//  iosApp
//
//  Created by Yazan Tarifi on 25/06/2024.
//  Copyright © 2024 orgName. All rights reserved.
//

import Foundation
import shared

public class ApplicationDependenciesManager {
    
    private var backgroundDispatchQueue: DispatchQueue? = nil
    private var storageProvider: LegoraStorageProvider? = nil
    private var httpClient: Ktor_client_coreHttpClient = LegoraHttpClient().httpClient
    private var legoraDatabase: LegoraDatabase? = nil
    
    public static var shared = ApplicationDependenciesManager()
    
    private init() {}
    
    public func getStorageInstance() -> LegoraStorageProvider? {
        if storageProvider == nil {
            storageProvider = LegoraStorageProvider(provider: LegoraStorageKeyValue(context: UserDefaults()))
        }
        
        return storageProvider
    }
    
    public func getDatabaseInstance() -> LegoraDatabase? {
        if self.legoraDatabase == nil {
            self.legoraDatabase = LegoraIosDatabaseManager().getDatabaseBuilder()
        }
        
        return self.legoraDatabase
    }
    
    public func getDispatchQueueBackgroundInstance() -> DispatchQueue? {
        if self.backgroundDispatchQueue == nil {
            self.backgroundDispatchQueue = DispatchQueue.global(qos: .background)
        }
        
        return self.backgroundDispatchQueue
    }
    
    public func getHttpClient() -> Ktor_client_coreHttpClient {
        return self.httpClient
    }
    
    public func getChampionsDao() -> ChampionsDao? {
        return self.getDatabaseInstance()?.getChampionsDao()
    }
    
}
