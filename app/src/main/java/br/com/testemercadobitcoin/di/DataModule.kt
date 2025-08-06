package br.com.testemercadobitcoin.di

import br.com.testemercadobitcoin.remote.CoinApi
import br.com.testemercadobitcoin.remote.RetrofitService
import org.koin.dsl.module
import org.koin.android.ext.koin.androidApplication

object DataModule {

    private val data = module {
        factory {
            RetrofitService(androidApplication())
        }
        factory { get<RetrofitService>().create(CoinApi::class.java) }
    }

    fun loadDataModule() = data
}