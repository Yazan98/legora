package com.yazantarifi.legora.champions

import com.yazantarifi.legora.api.requests.GetChampionsListRequestManager
import com.yazantarifi.legora.api.requests.GetTacticesChampionsListRequestManager
import com.yazantarifi.legora.caching.dao.ChampionsDao
import com.yazantarifi.legora.caching.models.ChampionCacheEntity
import com.yazantarifi.legora.champions.widgets.ChampionTitleWidget
import com.yazantarifi.legora.champions.widgets.ChampionsFreeToPlayWidget
import com.yazantarifi.legora.context.LegoraStorageProvider
import com.yazantarifi.legora.response.LegoraChampion
import io.ktor.client.HttpClient
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class LegoraChampionsItemsProvider constructor(
    private val httpClient: HttpClient,
    private val championsDao: ChampionsDao,
    private val storageProvider: LegoraStorageProvider
): CoroutineScope {

    private val championsRequest: GetChampionsListRequestManager by lazy {
        GetChampionsListRequestManager(httpClient)
    }

    private val tftChampionsRequest: GetTacticesChampionsListRequestManager by lazy {
        GetTacticesChampionsListRequestManager(httpClient)
    }

    override val coroutineContext: CoroutineContext
        get() = SupervisorJob() + Dispatchers.IO

    fun getScreenItems(
        currentTimestamp: Long,
        onSuccess: (List<ChampionItem>) -> Unit
    ) {
        launch(Dispatchers.IO) {
            val lastRequestedTimestamp = storageProvider.getHomeFeedTimestamp()
            if (lastRequestedTimestamp <= 0L) {
                getChampionsContent(currentTimestamp, onSuccess)
                return@launch
            }

            val diffCachingTime = currentTimestamp - lastRequestedTimestamp
            if (diffCachingTime > 600000) {
                getChampionsContent(currentTimestamp, onSuccess)
            } else {
                getCachedChampionsContent(currentTimestamp, onSuccess)
            }
        }
    }

    private suspend fun getCachedChampionsContent(currentTimestamp: Long, onSuccess: (List<ChampionItem>) -> Unit) {
        val championsList = championsDao.getChampions().sortedBy { it.order }
        if (championsList.isEmpty()) {
            getChampionsContent(currentTimestamp, onSuccess)
            return
        }

        println("Champions Logs : Get From Cache")
        val lolChampions = championsList.filter { it.type == "lol" }
        val tftChampions = championsList.filter { it.type == "tft" }

        onSuccess(arrayListOf(ChampionTitleWidget("Free To Play Champions")))
        onSuccess(arrayListOf(ChampionsFreeToPlayWidget(lolChampions?.filter { it.isFreeToPlay }?.map { LegoraChampion(it.id, it.name, it.icon, it.isFreeToPlay, it.type) } ?: arrayListOf())))

        onSuccess(arrayListOf(ChampionTitleWidget("All Champions")))
        onSuccess(lolChampions.map { LegoraChampion(it.id, it.name, it.icon, it.isFreeToPlay, it.type) })

        onSuccess(arrayListOf(ChampionTitleWidget("TFT Champions")))
        onSuccess(tftChampions.map { LegoraChampion(it.id, it.name, it.icon, it.isFreeToPlay, it.type) })
    }

    private suspend fun getChampionsContent(
        currentTimestamp: Long,
        onSuccess: (List<ChampionItem>) -> Unit
    ) {
        println("Champions Logs : Get From Internet")
        championsRequest.getRequestInfo(Unit, {
            it.data?.forEach {
                it.icon = it.icon?.replace("'", "")
            }

            onSuccess(arrayListOf(ChampionTitleWidget("Free To Play Champions")))
            onSuccess(arrayListOf(ChampionsFreeToPlayWidget(it.data?.filter { it.isFreeToPlay == true } ?: arrayListOf())))

            onSuccess(arrayListOf(ChampionTitleWidget("All Champions")))
            onSuccess(it.data ?: arrayListOf())

            launch {
                championsDao.onInsertChampions(it.data?.mapIndexed { index, it ->
                    ChampionCacheEntity(
                        it.name ?: "",
                        it.id ?: 0L,
                        it.icon ?: "",
                        it.type ?: "",
                        index,
                        it.isFreeToPlay ?: false
                    )
                } ?: arrayListOf())
            }
        }, {
            // Handle Error, Demo
        })

        delay(500L)

        tftChampionsRequest.getRequestInfo(Unit, {
            it.data?.forEach {
                it.icon = it.icon?.replace("'", "")
            }

            onSuccess(arrayListOf(ChampionTitleWidget("TFT Champions")))
            onSuccess(it.data ?: arrayListOf())

            storageProvider.updateHomeFeedTimestamp(currentTimestamp)
            launch {
                championsDao.onInsertChampions(it.data?.mapIndexed { index, it ->
                    ChampionCacheEntity(
                        it.name ?: "",
                        it.id ?: 0L,
                        it.icon ?: "",
                        it.type ?: "",
                        index,
                        it.isFreeToPlay ?: false
                    )
                } ?: arrayListOf())
            }
        }, {
            // Handle Error, Demo
        })
    }

}
