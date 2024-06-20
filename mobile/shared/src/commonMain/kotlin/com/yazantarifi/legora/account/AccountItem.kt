package com.yazantarifi.legora.account

interface AccountItem {
    fun getType(): AccountItemType
}

enum class AccountItemType {
    HEADER,
    TITLE,
    LOL_MATCH_HISTORY,
    TFT_MATCH_HISTORY
}