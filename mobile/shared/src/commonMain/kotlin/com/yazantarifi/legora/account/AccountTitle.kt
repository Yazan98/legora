package com.yazantarifi.legora.account

data class AccountTitle(
    val title: String
): AccountItem {

    override fun getType(): AccountItemType {
        return AccountItemType.TITLE
    }

}
