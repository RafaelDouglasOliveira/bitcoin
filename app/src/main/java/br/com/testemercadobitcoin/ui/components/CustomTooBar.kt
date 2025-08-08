package br.com.testemercadobitcoin.ui.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import br.com.testemercadobitcoin.R
import br.com.testemercadobitcoin.utils.UtilsColor

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomTooBar(title: String, isBack: Boolean = false , onBack: (() -> Unit)? = null) {
    if(isBack) {
        TopAppBar(
            title = {
                Text(
                    text = title,
                    fontWeight = FontWeight.Bold
                )
            },
            colors = TopAppBarDefaults.topAppBarColors(
                containerColor = UtilsColor.white,
                titleContentColor = UtilsColor.yellow_800
            ),

            navigationIcon = {
                IconButton(
                    onClick = {
                    onBack?.invoke()
                }) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = stringResource(R.string.back),
                        tint = UtilsColor.yellow_800
                    )
                }
            }
        )
    } else {
        TopAppBar(
            title = {
                Text(
                    text = title,
                    fontWeight = FontWeight.Bold
                )
            },
            colors = TopAppBarDefaults.topAppBarColors(
                containerColor = UtilsColor.white,
                titleContentColor = UtilsColor.yellow_800
            )
        )
    }
}
