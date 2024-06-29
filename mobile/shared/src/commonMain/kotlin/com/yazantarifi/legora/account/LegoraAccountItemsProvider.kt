package com.yazantarifi.legora.account

import com.yazantarifi.legora.LegoraSharedStorage
import com.yazantarifi.legora.api.requests.GetLeagueMatchHistoryRequestManager
import com.yazantarifi.legora.api.requests.GetProfileInfoRequestManager
import com.yazantarifi.legora.api.requests.GetTacticesMatchHistoryRequestManager
import com.yazantarifi.legora.context.LegoraStorageProvider
import com.yazantarifi.legora.response.AccountInfoResponse
import com.yazantarifi.legora.response.LegoraMatch
import com.yazantarifi.legora.response.LegoraTftMatch
import io.ktor.client.HttpClient
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class LegoraAccountItemsProvider constructor(
    private val httpClient: HttpClient,
    private val storageProvider: LegoraStorageProvider
): CoroutineScope {

    override val coroutineContext: CoroutineContext
        get() = SupervisorJob() + Dispatchers.IO

    private val accountInfoRequest: GetProfileInfoRequestManager by lazy {
        GetProfileInfoRequestManager(httpClient, LegoraSharedStorage.getApplicationHeaders("1.0", storageProvider.getAccessToken()))
    }

    private val lolMatchHistoryRequest: GetLeagueMatchHistoryRequestManager by lazy {
        GetLeagueMatchHistoryRequestManager(httpClient,  LegoraSharedStorage.getApplicationHeaders("1.0", storageProvider.getAccessToken()))
    }

    private val tftMatchHistoryRequest: GetTacticesMatchHistoryRequestManager by lazy {
        GetTacticesMatchHistoryRequestManager(httpClient,  LegoraSharedStorage.getApplicationHeaders("1.0", storageProvider.getAccessToken()))
    }

    fun getProfileInfo(onSuccess: (List<AccountItem>) -> Unit) {
        var accountInfo: AccountInfoResponse? = null
        var lolMatchHistory: List<LegoraMatch>? = null
        var tftMatchHistory: List<LegoraTftMatch>? = null

        launch(Dispatchers.IO) {
            accountInfoRequest.getSuspendedRequestInfo(Unit, {
                it.data?.let { it1 -> accountInfo = it1 }
            }, {
                // Handle Error -> Demo
            })

            lolMatchHistoryRequest.getSuspendedRequestInfo(Unit, {
                it.data?.let { it1 -> lolMatchHistory = it1 }
            }, {
                // Handle Error -> Demo
            })

            tftMatchHistoryRequest.getSuspendedRequestInfo(Unit, {
                it.data?.let { it1 -> tftMatchHistory = it1 }
            }, {
                // Handle Error -> Demo
            })

            accountInfo?.let {
                val screenWidgets = ArrayList<AccountItem>()
                // Header
                screenWidgets.add(it)

                // League
                screenWidgets.add(AccountTitle("League Match History"))
                lolMatchHistory?.forEach {
                    screenWidgets.add(it)
                }

                // TFT
                screenWidgets.add(AccountTitle("TFT Match History"))
                tftMatchHistory?.forEach {
                    screenWidgets.add(it)
                }

                onSuccess(screenWidgets)
            }
        }
    }

}
