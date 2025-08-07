package br.com.testemercadobitcoin.ui.list.usecase.mapper

import br.com.testemercadobitcoin.data.ExchangeResponse
import br.com.testemercadobitcoin.ui.list.model.ExchangeItem
import br.com.testemercadobitcoin.utils.Constants.DEFAULT_VALUE

object ExchangeMapper {
    fun List<ExchangeResponse>.toExchange(): List<ExchangeItem> {
        return this.map { exchangeResponse ->
            ExchangeItem(
                exchangeId = exchangeResponse.exchangeId ?: DEFAULT_VALUE,
                website = exchangeResponse.website ?: DEFAULT_VALUE,
                name = exchangeResponse.name ?: DEFAULT_VALUE,
                dataQuoteStart = exchangeResponse.dataQuoteStart ?: DEFAULT_VALUE,
                dataQuoteEnd = exchangeResponse.dataQuoteEnd ?: DEFAULT_VALUE,
                dataOrderBookStart = exchangeResponse.dataOrderbookStart ?: DEFAULT_VALUE,
                dataOrderBookEnd = exchangeResponse.dataOrderbookEnd ?: DEFAULT_VALUE,
                dataTradeStart = exchangeResponse.dataTradeStart ?: DEFAULT_VALUE,
                dataTradeEnd = exchangeResponse.dataTradeEnd ?: DEFAULT_VALUE,
                dataSymbolsCount = exchangeResponse.dataSymbolsCount ?: 0,
                volumeOneHrsUsd = exchangeResponse.volume1HrsUsd ?: 0.0,
                volumeOneDayUsd = exchangeResponse.volume1DayUsd ?: 0.0,
                volumeOneMthUsd = exchangeResponse.volume1MthUsd ?: 0.0,
                rank = exchangeResponse.rank ?: 0,
                integrationStatus = exchangeResponse.integrationStatus ?: DEFAULT_VALUE,
                listMetricId = exchangeResponse.listMetricId ?: emptyList()
            )
        }
    }
}