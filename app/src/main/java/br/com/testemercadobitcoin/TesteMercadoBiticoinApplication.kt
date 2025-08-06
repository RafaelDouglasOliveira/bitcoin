package br.com.testemercadobitcoin

import android.app.Application
import br.com.testemercadobitcoin.di.DataModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.GlobalContext.startKoin

class TesteMercadoBiticoinApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@TesteMercadoBiticoinApplication)
            modules(DataModule.loadDataModule())
        }
    }
}