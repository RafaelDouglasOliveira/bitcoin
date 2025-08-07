package br.com.testemercadobitcoin.ui.detail

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import br.com.testemercadobitcoin.R
import br.com.testemercadobitcoin.ui.components.CustomTooBar
import br.com.testemercadobitcoin.ui.components.ExchangeDetail
import br.com.testemercadobitcoin.ui.list.model.ExchangeItem
import br.com.testemercadobitcoin.utils.UtilsColor

@Composable
fun DetailsExchangeScreen(navController: NavController) {
    val data = navController.previousBackStackEntry?.savedStateHandle?.get<ExchangeItem>("data")

    Scaffold(
        containerColor = UtilsColor.white,
        topBar = {
            CustomTooBar(
                title = stringResource(R.string.titles_details_exchange),
                isBack = true
            ) {
                navController.popBackStack()
            }
        },
        content = { innerPadding ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
                    .verticalScroll(rememberScrollState()),
            ) {
                data?.let {
                    ExchangeDetail(it)
                }
            }
        })
}