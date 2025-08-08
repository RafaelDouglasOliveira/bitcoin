package br.com.testemercadobitcoin.remote

import br.com.testemercadobitcoin.remote.repository.data.ExchangeResponse
import retrofit2.http.GET

interface CoinApi {

    @GET("v1/exchanges")
    suspend fun getExchanges(): List<ExchangeResponse>
}