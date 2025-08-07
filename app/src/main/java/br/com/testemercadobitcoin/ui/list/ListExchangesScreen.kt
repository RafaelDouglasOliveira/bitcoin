package br.com.testemercadobitcoin.ui.list

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import br.com.testemercadobitcoin.R
import br.com.testemercadobitcoin.navigation.NavRoutes
import br.com.testemercadobitcoin.ui.components.CustomTooBar
import br.com.testemercadobitcoin.ui.components.ExchangeItem
import br.com.testemercadobitcoin.ui.components.LoadingCustom
import br.com.testemercadobitcoin.ui.list.viewmodel.ListExchangesViewModel
import br.com.testemercadobitcoin.utils.UtilsColor
import br.com.testemercadobitcoin.utils.UtilsDimen
import org.koin.androidx.compose.koinViewModel
import kotlin.system.exitProcess

@Composable
fun ListExchangesScreen(navController: NavController) {

    var showExitDialog by remember { mutableStateOf(false) }

    BackHandler(enabled = true) {
        showExitDialog = true
    }

    val viewModel: ListExchangesViewModel = koinViewModel()
    val listState = viewModel.list.observeAsState(emptyList())
    val loading = viewModel.loadingState.observeAsState(false)
    val errorState = viewModel.errorState.observeAsState(null)

    LaunchedEffect(Unit) {
        viewModel.getListExchanges()
    }

    Scaffold(
        containerColor = UtilsColor.white,
        topBar = {
            CustomTooBar(
                title = stringResource(R.string.titles_exchanges),
                isBack = false
            )
        }, content = { innerPadding ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {

                if (loading.value) {
                    LoadingCustom()
                } else if (listState.value.isNotEmpty()) {
                    LazyColumn(
                        modifier = Modifier.fillMaxSize()
                    ) {
                        items(listState.value) { item ->
                            ExchangeItem(item) {
                                navController.currentBackStackEntry?.savedStateHandle?.set(
                                    "data",
                                    item
                                )
                                navController.navigate(NavRoutes.DETAILS_EXCHANGE)
                            }
                        }
                    }
                }

                if (errorState.value != null) {
                    Text(
                        text = errorState.value.toString(),
                        fontSize = UtilsDimen.Sp.fourteen_sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.DarkGray
                    )
                }
            }
        })

    if (showExitDialog) {
        AlertDialog(
            onDismissRequest = {
                showExitDialog = false
            },
            title = {
                Text(
                    fontSize = UtilsDimen.Sp.sixteen_sp,
                    text = stringResource(R.string.title_alert_exit_app)
                )
            },
            text = {
                Text(
                    fontSize = UtilsDimen.Sp.fourteen_sp,
                    text = stringResource(R.string.message_alert_exit_app)
                )
            },
            confirmButton = {
                Button(
                    onClick = {
                        exitProcess(0)
                    },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = UtilsColor.orange_500
                    )
                ) {
                    Text(
                        stringResource(R.string.button_confirm_exit_app)
                    )
                }
            },
            dismissButton = {
                Button(
                    onClick = {
                        showExitDialog = false
                    },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = UtilsColor.orange_500
                    )
                ) {
                    Text(stringResource(R.string.button_cancel_exit_app))
                }
            }
        )
    }
}