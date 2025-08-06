package br.com.testemercadobitcoin.data

import com.google.gson.annotations.SerializedName

data class ExchangeResponse(
    @SerializedName("exchange_id")
    val exchangeId: String,
    val website: String,
    val name: String,
    @SerializedName("data_quote_start")
    val dataQuoteStart: String,
    @SerializedName("data_quote_end")
    val dataQuoteEnd: String,
    @SerializedName("data_orderbook_start")
    val dataOrderbookStart: String,
    @SerializedName("data_orderbook_end")
    val dataOrderbookEnd: String,
    @SerializedName("data_trade_start")
    val dataTradeStart: String,
    @SerializedName("data_trade_end")
    val dataTradeEnd: String,
    @SerializedName("data_symbols_count")
    val dataSymbolsCount: Int,
    @SerializedName("volume_1hrs_usd")
    val volume1HrsUsd: Double,
    @SerializedName("volume_1day_usd")
    val volume1DayUsd: Double,
    @SerializedName("volume_1mth_usd")
    val volume1MthUsd: Double,
    val rank: Int,
    @SerializedName("integration_status")
    val integrationStatus: String
)
