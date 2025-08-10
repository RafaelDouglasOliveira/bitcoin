package br.com.testemercadobitcoin

import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.lifecycle.MutableLiveData
import androidx.navigation.compose.ComposeNavigator
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.testing.TestNavHostController
import androidx.test.ext.junit.runners.AndroidJUnit4
import br.com.testemercadobitcoin.navigation.NavRoutes
import br.com.testemercadobitcoin.remote.SafeResponse
import br.com.testemercadobitcoin.ui.detail.DetailsExchangeScreen
import br.com.testemercadobitcoin.ui.list.ListExchangesScreen
import br.com.testemercadobitcoin.ui.list.model.ExchangeItem
import br.com.testemercadobitcoin.ui.list.viewmodel.ListExchangesViewModel
import br.com.testemercadobitcoin.utils.MockUtils.listExchangeMock
import io.mockk.every
import io.mockk.mockk
import org.junit.Before
import org.junit.Rule
import org.junit.runner.RunWith
import kotlin.test.Test

@RunWith(AndroidJUnit4::class)
class ListExchangesScreenTest {

    @get:Rule
    val composeTestRule = createComposeRule()
    private lateinit var navController: TestNavHostController
    private val liveDataLoading = MutableLiveData(true)
    private val liveDataList = MutableLiveData(emptyList<ExchangeItem>())
    private val liveDataError = MutableLiveData<String>(null)
    private lateinit var mockViewModel: ListExchangesViewModel

    @Before
    fun setup() {
        mockViewModel = mockk(relaxed = true) {
            every { loadingState } returns liveDataLoading
            every { list } returns liveDataList
            every { errorState } returns liveDataError
        }
    }

    @Test
    fun sucessoAberturaListagem() {
        liveDataLoading.postValue(true)
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

        liveDataLoading.postValue(false)
        liveDataList.postValue(listExchangeMock)
        composeTestRule.runOnIdle {}
        composeTestRule.onNodeWithText("Binance", substring = true).assertIsDisplayed()
        composeTestRule.onNodeWithText("Coinbase Pro", substring = true).assertIsDisplayed()


         composeTestRule.onNodeWithText("Binance", substring = true).performClick()

         composeTestRule.waitUntil(timeoutMillis = 4000L) {
             navController.currentDestination?.route == NavRoutes.DETAILS_EXCHANGE
         }
         composeTestRule.runOnIdle {
             assert(navController.currentDestination?.route == NavRoutes.DETAILS_EXCHANGE)
         }
    }
}