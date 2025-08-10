package br.com.testemercadobitcoin.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import br.com.testemercadobitcoin.ui.detail.DetailsExchangeScreen
import br.com.testemercadobitcoin.ui.list.ListExchangesScreen
import br.com.testemercadobitcoin.ui.splash.SplashScreen
import org.koin.androidx.compose.koinViewModel

object NavRoutes {
    const val SPLASH = "splash"
    const val LIST_EXCHANGES = "list_exchanges"
    const val DETAILS_EXCHANGE = "details_exchange"
}

@Composable
fun BitcoinNavigation() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = NavRoutes.SPLASH
    ) {
        composable(NavRoutes.SPLASH) {
          SplashScreen(navController)
        }

        composable(NavRoutes.LIST_EXCHANGES) {
            ListExchangesScreen(navController, viewModel = koinViewModel())
        }

        composable(NavRoutes.DETAILS_EXCHANGE) {
            DetailsExchangeScreen(navController)
        }
    }
}