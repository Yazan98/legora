package com.yazantarifi.legora.android.viewModels

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yazantarifi.legora.home.items.HomeScreenItem
import com.yazantarifi.legora.home.HomeScreenItemsProvider
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val homeWidgetsProvider: HomeScreenItemsProvider
): ViewModel() {

    val isLoading by lazy { mutableStateOf(false) }
    val homeScreenWidgetsState by lazy { mutableStateListOf<HomeScreenItem>() }

    fun getHomeScreenItems() {
        isLoading.value = true
        viewModelScope.launch(Dispatchers.IO) {
            homeWidgetsProvider.getHomeScreenItems(System.currentTimeMillis()) {
                viewModelScope.launch(Dispatchers.Main) {
                    println("UIUI :: Items : $it")
                    homeScreenWidgetsState.clear()
                    isLoading.value = false
                    homeScreenWidgetsState.addAll(it)
                }
            }
        }
    }

}
