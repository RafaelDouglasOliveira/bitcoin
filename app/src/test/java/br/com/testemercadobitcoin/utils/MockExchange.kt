package br.com.testemercadobitcoin.utils

import br.com.testemercadobitcoin.remote.repository.data.ExchangeResponse

object MockExchange {
    val listExchangeMock = listOf(
        ExchangeResponse(
            exchangeId = "BINANCE",
            website = "https://www.binance.com/",
            name = "Binance",
            dataQuoteStart = "2023-01-01T00:00:00.0000000Z",
            dataQuoteEnd = "2023-10-26T00:00:00.0000000Z",
            dataOrderbookStart = "2023-01-01T00:00:00.0000000Z",
            dataOrderbookEnd = "2023-10-26T00:00:00.0000000Z",
            dataTradeStart = "2023-01-01T00:00:00.0000000Z",
            dataTradeEnd = "2023-10-26T00:00:00.0000000Z",
            dataSymbolsCount = 1500,
            volume1HrsUsd = 1234567.89,
            volume1DayUsd = 98765432.10,
            volume1MthUsd = 1234567890.12,
            rank = 1,
            integrationStatus = "COMPLETE",
            listMetricId = listOf("BTC", "ETH", "BNB")
        ),
        ExchangeResponse(
            exchangeId = "COINBASE",
            website = "https://www.coinbase.com/",
            name = "Coinbase Pro",
            dataQuoteStart = "2023-01-01T00:00:00.0000000Z",
            dataQuoteEnd = "2023-10-26T00:00:00.0000000Z",
            dataOrderbookStart = "2023-01-01T00:00:00.0000000Z",
            dataOrderbookEnd = "2023-10-26T00:00:00.0000000Z",
            dataTradeStart = "2023-01-01T00:00:00.0000000Z",
            dataTradeEnd = "2023-10-26T00:00:00.0000000Z",
            dataSymbolsCount = 500,
            volume1HrsUsd = 987654.32,
            volume1DayUsd = 65432109.87,
            volume1MthUsd = 987654321.09,
            rank = 2,
            integrationStatus = "COMPLETE",
            listMetricId = listOf("BTC", "ETH", "SOL")
        ),
        ExchangeResponse(
            exchangeId = "KRAKEN",
            website = "https://www.kraken.com/",
            name = "Kraken",
            dataQuoteStart = "2023-01-01T00:00:00.0000000Z",
            dataQuoteEnd = "2023-10-26T00:00:00.0000000Z",
            dataOrderbookStart = "2023-01-01T00:00:00.0000000Z",
            dataOrderbookEnd = "2023-10-26T00:00:00.0000000Z",
            dataTradeStart = "2023-01-01T00:00:00.0000000Z",
            dataTradeEnd = "2023-10-26T00:00:00.0000000Z",
            dataSymbolsCount = 300,
            volume1HrsUsd = 543210.98,
            volume1DayUsd = 32109876.54,
            volume1MthUsd = 543210987.65,
            rank = 3,
            integrationStatus = "IN_PROGRESS",
            listMetricId = listOf("XRP", "LTC")
        )
    )
}