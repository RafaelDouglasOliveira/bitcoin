package br.com.testemercadobitcoin

import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.navigation.compose.ComposeNavigator
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.testing.TestNavHostController
import androidx.test.ext.junit.runners.AndroidJUnit4
import br.com.testemercadobitcoin.navigation.NavRoutes
import br.com.testemercadobitcoin.ui.list.ListExchangesScreen
import br.com.testemercadobitcoin.ui.list.viewmodel.ListExchangesViewModel
import br.com.testemercadobitcoin.ui.splash.SplashScreen
import br.com.testemercadobitcoin.utils.Constants.ANIMATION
import br.com.testemercadobitcoin.utils.Constants.DESCRIPTION_ANIMATION
import io.mockk.mockk
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.koin.test.KoinTest

@RunWith(AndroidJUnit4::class)
class SplashScreenTest: KoinTest {
    @get:Rule
    val composeTestRule = createComposeRule()
    private lateinit var navController: TestNavHostController

    @Test
    fun testaAberturaSplashScreenNavegacaoTelaListagem() {
        val mockViewModel = mockk<ListExchangesViewModel>(relaxed = true)

        composeTestRule.setContent {
            navController = TestNavHostController(LocalContext.current)
            navController.navigatorProvider.addNavigator(ComposeNavigator())

            NavHost(navController = navController, startDestination = NavRoutes.SPLASH) {
                composable(NavRoutes.SPLASH) {
                    SplashScreen(navController)
                }
                composable(NavRoutes.LIST_EXCHANGES) {
                    ListExchangesScreen(navController, mockViewModel)
                }
            }
        }

        composeTestRule.onNodeWithContentDescription(DESCRIPTION_ANIMATION).assertIsDisplayed()

        composeTestRule.waitUntil(timeoutMillis = (ANIMATION + 1000L)) {
            navController.currentDestination?.route == NavRoutes.LIST_EXCHANGES
        }

        composeTestRule.runOnIdle {
            assert(navController.currentDestination?.route == NavRoutes.LIST_EXCHANGES)
        }
    }
}