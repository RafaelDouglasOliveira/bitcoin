package br.com.testemercadobitcoin

import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.navigation.compose.ComposeNavigator
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.testing.TestNavHostController
import androidx.test.ext.junit.runners.AndroidJUnit4
import br.com.testemercadobitcoin.navigation.NavRoutes
import br.com.testemercadobitcoin.ui.detail.DetailsExchangeScreen
import br.com.testemercadobitcoin.ui.list.ListExchangesScreen
import br.com.testemercadobitcoin.ui.list.viewmodel.ListExchangesViewModel
import br.com.testemercadobitcoin.utils.MockUtils.mockExchangeItem
import io.mockk.mockk
import org.junit.Rule
import org.junit.runner.RunWith
import org.koin.test.KoinTest
import kotlin.test.Test

@RunWith(AndroidJUnit4::class)
class DetailsExchangeScreenTest: KoinTest {
    @get:Rule
    val composeTestRule = createComposeRule()
    private lateinit var navController: TestNavHostController

    @Test
    fun testaAberturaDetailsExchange() {
        val mockViewModel = mockk<ListExchangesViewModel>(relaxed = true)

        composeTestRule.setContent {
            navController = TestNavHostController(LocalContext.current)
            navController.navigatorProvider.addNavigator(ComposeNavigator())

            NavHost(navController = navController, startDestination = NavRoutes.LIST_EXCHANGES) {
                composable(NavRoutes.LIST_EXCHANGES) {
                    ListExchangesScreen(navController, mockViewModel)
                }
                composable(NavRoutes.DETAILS_EXCHANGE) {
                    DetailsExchangeScreen(navController)
                }
            }
        }

        composeTestRule.runOnIdle {
            navController.currentBackStackEntry?.savedStateHandle?.set("data", mockExchangeItem)
            navController.navigate(NavRoutes.DETAILS_EXCHANGE)
        }
        composeTestRule.waitUntil(timeoutMillis = 2000L) {
            navController.currentDestination?.route == NavRoutes.DETAILS_EXCHANGE
        }
        composeTestRule.onNodeWithText("Kraken").assertIsDisplayed()
        composeTestRule.onNodeWithContentDescription("Voltar").performClick()

        composeTestRule.runOnIdle {
            assert(navController.currentDestination?.route == NavRoutes.LIST_EXCHANGES)
        }

    }
}