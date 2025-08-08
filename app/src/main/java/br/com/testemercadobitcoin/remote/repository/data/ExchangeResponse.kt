package br.com.testemercadobitcoin.remote.repository.data

import com.google.gson.annotations.SerializedName

data class ExchangeResponse(
    @SerializedName("exchange_id")
    val exchangeId: String? = null,
    val website: String? = null,
    val name: String? = null,
    @SerializedName("data_quote_start")
    val dataQuoteStart: String? = null,
    @SerializedName("data_quote_end")
    val dataQuoteEnd: String? = null,
    @SerializedName("data_orderbook_start")
    val dataOrderbookStart: String? = null,
    @SerializedName("data_orderbook_end")
    val dataOrderbookEnd: String? = null,
    @SerializedName("data_trade_start")
    val dataTradeStart: String? = null,
    @SerializedName("data_trade_end")
    val dataTradeEnd: String? = null,
    @SerializedName("data_symbols_count")
    val dataSymbolsCount: Int? = null,
    @SerializedName("volume_1hrs_usd")
    val volume1HrsUsd: Double? = null,
    @SerializedName("volume_1day_usd")
    val volume1DayUsd: Double? = null,
    @SerializedName("volume_1mth_usd")
    val volume1MthUsd: Double? = null,
    val rank: Int? = null,
    @SerializedName("integration_status")
    val integrationStatus: String? = null,
    @SerializedName("metric_id")
    val listMetricId: List<String>? = null,
)
