package br.com.testemercadobitcoin.remote.repository

import br.com.testemercadobitcoin.remote.CoinApi
import br.com.testemercadobitcoin.remote.SafeResponse
import br.com.testemercadobitcoin.remote.safeRequest
import br.com.testemercadobitcoin.ui.list.model.ExchangeItem
import br.com.testemercadobitcoin.ui.list.usecase.mapper.ExchangeMapper.toExchange

class CoinApiRepository(private val coinApi: CoinApi) {
    suspend fun getListExchanges(): SafeResponse<List<ExchangeItem>> {
        val result = coinApi.getExchanges().toExchange()
        return safeRequest {
           result
        }
    }
}