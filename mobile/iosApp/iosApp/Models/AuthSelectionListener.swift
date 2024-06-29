//
//  AuthSelectionListener.swift
//  iosApp
//
//  Created by Yazan Tarifi on 29/06/2024.
//  Copyright © 2024 orgName. All rights reserved.
//

import Foundation

public protocol AuthSelectionListener {
    func onSelectOption(option: AuthBottomSheetOption)
}
