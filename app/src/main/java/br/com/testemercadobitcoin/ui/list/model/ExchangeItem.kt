package br.com.testemercadobitcoin.ui.list.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize


@Parcelize
data class ExchangeItem(
    val exchangeId: String,
    val website: String,
    val name: String,
    val dataQuoteStart: String,
    val dataQuoteEnd: String,
    val dataOrderBookStart: String,
    val dataOrderBookEnd: String,
    val dataTradeStart: String,
    val dataTradeEnd: String,
    val dataSymbolsCount: Int,
    val volumeOneHrsUsd: Double,
    val volumeOneDayUsd: Double,
    val volumeOneMthUsd: Double,
    val rank: Int,
    val integrationStatus: String,
    val listMetricId: List<String>,
) : Parcelable

