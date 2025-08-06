package br.com.testemercadobitcoin.ui.list.usecase

import br.com.testemercadobitcoin.remote.repository.CoinApiRepository

class ListExchangesUseCase(private val coinApiRepository: CoinApiRepository)  {
    suspend fun execute() = coinApiRepository.getListExchanges()
}