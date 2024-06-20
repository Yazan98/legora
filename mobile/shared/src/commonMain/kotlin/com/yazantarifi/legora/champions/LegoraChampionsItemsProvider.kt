package com.yazantarifi.legora.champions

import com.yazantarifi.legora.api.requests.GetChampionsListRequestManager
import com.yazantarifi.legora.api.requests.GetTacticesChampionsListRequestManager
import com.yazantarifi.legora.champions.widgets.ChampionTitleWidget
import com.yazantarifi.legora.champions.widgets.ChampionsFreeToPlayWidget
import io.ktor.client.HttpClient
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class LegoraChampionsItemsProvider constructor(
    private val httpClient: HttpClient
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
        onSuccess: (List<ChampionItem>) -> Unit
    ) {
        launch(Dispatchers.IO) {
            championsRequest.getRequestInfo(Unit, {
                it.data?.forEach {
                    it.icon = it.icon?.replace("'", "")
                }

                onSuccess(arrayListOf(ChampionTitleWidget("Free To Play Champions")))
                onSuccess(arrayListOf(ChampionsFreeToPlayWidget(it.data?.filter { it.isFreeToPlay == true } ?: arrayListOf())))

                onSuccess(arrayListOf(ChampionTitleWidget("All Champions")))
                onSuccess(it.data ?: arrayListOf())
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
            }, {
                // Handle Error, Demo
            })
        }
    }

}
