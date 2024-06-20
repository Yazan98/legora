package com.yazantarifi.legora.home

import com.yazantarifi.legora.api.requests.GetHomeFeedRequestManager
import com.yazantarifi.legora.caching.dao.HomeScreenDao
import com.yazantarifi.legora.caching.models.HomeScreenWidgetEntity
import com.yazantarifi.legora.context.LegoraStorageProvider
import com.yazantarifi.legora.home.items.HomeNewsItemWidget
import com.yazantarifi.legora.home.items.HomePlayersWidget
import com.yazantarifi.legora.home.items.HomeScreenItem
import com.yazantarifi.legora.home.items.HomeTitleWidget
import com.yazantarifi.legora.home.items.HomeUserWidget
import io.ktor.client.HttpClient
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import kotlin.coroutines.CoroutineContext

class HomeScreenItemsProvider constructor(
    private val httpClient: HttpClient,
    private val storageProvider: LegoraStorageProvider,
    private val homeScreenCachingDao: HomeScreenDao,
    private val jsonConverterListener: LegoraJsonConverterListener
): CoroutineScope {

    private val repository: GetHomeFeedRequestManager by lazy {
        GetHomeFeedRequestManager(httpClient)
    }

    override val coroutineContext: CoroutineContext
        get() = SupervisorJob() + Dispatchers.IO

    fun getHomeScreenItems(
        currentTimestamp: Long,
        onSuccess: (List<HomeScreenItem>) -> Unit
    ) {
        launch(Dispatchers.IO) {
            val lastRequestedTimestamp = storageProvider.getHomeFeedTimestamp()
            if (lastRequestedTimestamp <= 0L) {
                getHomeScreenInternetContent(currentTimestamp, onSuccess)
                return@launch
            }

            val diffCachingTime = currentTimestamp - lastRequestedTimestamp
            if (diffCachingTime > 0) {
                getHomeScreenInternetContent(currentTimestamp, onSuccess)
            } else {
                getCachedHomeScreenWidgets(currentTimestamp, onSuccess)
            }
        }
    }

    private fun getCachedHomeScreenWidgets(currentTimestamp: Long, onSuccess: (List<HomeScreenItem>) -> Unit) {
        val items = homeScreenCachingDao.getHomeWidgets()
        if (items.isEmpty()) {
            getHomeScreenInternetContent(currentTimestamp, onSuccess)
        }
        // Parse Cached Widgets
    }

    private fun getHomeScreenInternetContent(currentTimestamp: Long, onSuccess: (List<HomeScreenItem>) -> Unit) {
        getHomeScreenResponse(currentTimestamp) {
            val homeScreenWidgets = getHomeScreenWidgetsBuilder(it)
            onCacheScreenWidgets(homeScreenWidgets)
            onSuccess(homeScreenWidgets)
        }
    }

    private fun onCacheScreenWidgets(items: List<HomeScreenItem>) {
        try {
            val cachingItems = items.mapIndexed { index, value ->
                HomeScreenWidgetEntity(index, value.getType().name, jsonConverterListener.getJsonString(value))
            }

            // Delete Prev Cached Widgets
            val items = homeScreenCachingDao.getHomeWidgets()
            if (items.isNotEmpty()) {
                items.forEach {
                    homeScreenCachingDao.deleteAllHomeWidgets(it)
                }
            }

            println("Home Feed Caching Items : $cachingItems")
            homeScreenCachingDao.onInsertHomeWidgets(cachingItems)
        } catch (ex: Exception) {
            println(ex.message)
            ex.printStackTrace()
        }
    }

    private fun getHomeScreenWidgetsBuilder(response: List<HomeFeedResponse>): List<HomeScreenItem> {
        val items = ArrayList<HomeScreenItem>()
        response.forEach {
            when (it.type) {
                "welcome_widget" -> items.add(HomeUserWidget(
                    it.summonerHighlightName ?: "",
                    it.serverHighlightName ?: "",
                    it.profileImage ?: ""
                ))

                "latest_news" -> {
                    items.add(HomeTitleWidget("Latest News"))
                    it.items?.forEach {
                        items.add(HomeNewsItemWidget(it))
                    }
                }

                "match_history" -> {
                    items.add(HomeTitleWidget("Match History"))
                    it.lolMatches?.getOrNull(0)?.let {
                        items.add(it)
                    }

                    it.tftMatches?.getOrNull(0)?.let {
                        items.add(it)
                    }

                    it.lolMatches?.getOrNull(1)?.let {
                        items.add(it)
                    }

                    it.tftMatches?.getOrNull(1)?.let {
                        items.add(it)
                    }
                }

                "players" -> {
                    items.add(HomeTitleWidget("Popular Players"))
                    items.add(HomePlayersWidget(it.items ?: arrayListOf()))
                }
            }
        }
        return items
    }

    private fun getHomeScreenResponse(currentTimestamp: Long, onSuccess: (List<HomeFeedResponse>) -> Unit) {
        repository.getRequestInfo(Unit, {
            it.data?.let { it1 -> onSuccess(it1) }
            storageProvider.updateHomeFeedTimestamp(currentTimestamp)
        }, {
            // Handle Error
        })
    }

}
