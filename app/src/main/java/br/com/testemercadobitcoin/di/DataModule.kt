package br.com.testemercadobitcoin.di

import br.com.testemercadobitcoin.remote.CoinApi
import br.com.testemercadobitcoin.remote.RetrofitService
import br.com.testemercadobitcoin.remote.repository.CoinApiRepository
import br.com.testemercadobitcoin.ui.list.usecase.ListExchangesUseCase
import br.com.testemercadobitcoin.ui.list.viewmodel.ListExchangesViewModel
import org.koin.dsl.module
import org.koin.android.ext.koin.androidApplication
import org.koin.androidx.viewmodel.dsl.viewModel

object DataModule {

    private val data = module {
        factory {
            RetrofitService(androidApplication())
        }
        factory { get<RetrofitService>().create(CoinApi::class.java) }
        factory { CoinApiRepository(get()) }
        factory { ListExchangesUseCase(get()) }
        viewModel{ ListExchangesViewModel(get()) }
    }

    fun loadDataModule() = data
}