package br.com.testemercadobitcoin.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.com.testemercadobitcoin.R
import br.com.testemercadobitcoin.ui.list.model.ExchangeItem
import br.com.testemercadobitcoin.utils.UtilsColor

@Composable
fun ExchangeItem(item: ExchangeItem,
                 onClick: (ExchangeItem) -> Unit ) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp)
            .clickable { onClick(item) },
        shape = MaterialTheme.shapes.medium,
        border = BorderStroke(4.dp, UtilsColor.red_600),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        )
    ) {
        LabelItem(label = stringResource(id = R.string.item_name), value = item.name)
        LabelItem(label = stringResource(id = R.string.item_exchange_id), value = item.exchangeId)
        LabelItem(label = stringResource(id = R.string.volume_day), value = item.volumeOneDayUsd.toString())
    }
}

@Composable
fun LabelItem(label: String, value: String) {
    Row(
        modifier = Modifier.fillMaxWidth()
            .padding(start = 16.dp, top = 8.dp, end = 16.dp, bottom = 8.dp)){
        Text(
            text = label,
            fontSize = 14.sp,
            fontWeight = FontWeight.Bold,
            color = UtilsColor.orange
        )
        Spacer(modifier = Modifier.width(8.dp))
        Text(
            text = value,
            fontSize = 14.sp,
            fontWeight = FontWeight.Normal,
            color = Color.Gray
        )
    }
}