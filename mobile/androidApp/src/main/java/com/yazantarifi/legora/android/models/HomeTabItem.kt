package com.yazantarifi.legora.android.models

import com.yazantarifi.legora.android.R

sealed class HomeTabItem constructor(
    val tabRoute: String,
    val tabIcon: Int,
    val tabLabel: Int,
) {
    object HomeScreenRoute: HomeTabItem(
        "home",
        R.drawable.home,
        R.string.home
    )

    object ExploreScreenRoute: HomeTabItem(
        "explore",
        R.drawable.discovery,
        R.string.explore
    )

    object ProfileScreenRoute: HomeTabItem(
        "profile",
        R.drawable.profile_circle,
        R.string.profile
    )

    companion object {
        fun getItems(): List<HomeTabItem> {
            return arrayListOf(
                HomeScreenRoute,
                ExploreScreenRoute,
                ProfileScreenRoute,
            )
        }
    }

}