package com.yazantarifi.legora.android.screens

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.material.ScaffoldState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.bumptech.glide.integration.compose.GlideImage
import com.yazantarifi.legora.android.fragments.HomeChampionsScreen
import com.yazantarifi.legora.android.fragments.HomeFeedScreen
import com.yazantarifi.legora.android.fragments.ProfileScreenComposable
import com.yazantarifi.legora.android.models.HomeTabItem
import com.yazantarifi.legora.android.screens.base.LegoraBaseScreen
import com.yazantarifi.legora.android.viewModels.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeScreen: LegoraBaseScreen() {

    private val selectedIndex by lazy { mutableIntStateOf(0) }
    private var screenController: NavHostController? = null

    @Composable
    override fun OnStartScreenContent(scaffoldState: ScaffoldState, paddingValues: PaddingValues) {
        val screenViewModel: HomeViewModel = hiltViewModel()
        screenController?.let {
            NavHost(
                navController = it,
                startDestination = HomeTabItem.HomeScreenRoute.tabRoute,
                modifier = Modifier.padding(paddingValues = paddingValues)
            ) {
                composable(HomeTabItem.HomeScreenRoute.tabRoute) {
                    HomeFeedScreen(screenViewModel)
                }

                composable(HomeTabItem.ExploreScreenRoute.tabRoute) {
                    HomeChampionsScreen(screenViewModel)
                }

                composable(HomeTabItem.ProfileScreenRoute.tabRoute) {
                    ProfileScreenComposable(screenViewModel)
                }
            }
        }
    }

    @Composable
    override fun OnBottomBarEnabled() {
        var navigationSelectedItem by remember {
            selectedIndex
        }

        NavigationBar(
            containerColor = MaterialTheme.colorScheme.onSurfaceVariant,
            tonalElevation = 5.dp
        ) {
            HomeTabItem.getItems().forEachIndexed { index, navigationItem ->
                NavigationBarItem(
                    selected = index == navigationSelectedItem,
                    label = {
                        Text(stringResource(id = navigationItem.tabLabel))
                    },
                    icon = {
                        GlideImage(
                            model = navigationItem.tabIcon,
                            contentDescription = stringResource(id = navigationItem.tabLabel),
                            colorFilter = ColorFilter.tint(
                                if (navigationSelectedItem == index) {
                                    MaterialTheme.colorScheme.onPrimaryContainer
                                } else {
                                    MaterialTheme.colorScheme.surfaceVariant
                                }
                            )
                        )
                    },
                    onClick = {
                        if (index == navigationSelectedItem) {
                            return@NavigationBarItem
                        }

                        navigationSelectedItem = index
                        screenController?.navigate(navigationItem.tabRoute) {
                            popUpTo(screenController?.graph?.findStartDestination()?.id ?: 0) {
                                saveState = true
                            }

                            launchSingleTop = true
                            restoreState = true
                        }
                    },
                    colors = NavigationBarItemDefaults.colors(
                        selectedIconColor = MaterialTheme.colorScheme.onPrimaryContainer,
                        selectedTextColor = MaterialTheme.colorScheme.onPrimaryContainer,
                        indicatorColor = MaterialTheme.colorScheme.outline,
                        unselectedIconColor = MaterialTheme.colorScheme.surfaceVariant,
                        unselectedTextColor = MaterialTheme.colorScheme.surfaceVariant,
                    )
                )
            }
        }
    }

    override fun isBottomBarEnabled(): Boolean {
        return true
    }

    @Composable
    override fun OnPreScreenStarted() {
        super.OnPreScreenStarted()
        screenController = rememberNavController()
    }

}
