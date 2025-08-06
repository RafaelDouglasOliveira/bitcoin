package br.com.testemercadobitcoin.remote.repository

import br.com.testemercadobitcoin.data.ExchangeResponse
import br.com.testemercadobitcoin.remote.CoinApi
import br.com.testemercadobitcoin.remote.SafeResponse
import br.com.testemercadobitcoin.remote.safeRequest

class CoinApiRepository(private val coinApi: CoinApi) {
    suspend fun getListExchanges(): SafeResponse<List<ExchangeResponse>> {
        return safeRequest {
            coinApi.getExchanges()
        }
    }
}