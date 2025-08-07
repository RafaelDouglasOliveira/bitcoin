package br.com.testemercadobitcoin.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
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
import br.com.testemercadobitcoin.utils.formatDate

@Composable
fun ExchangeDetail(item: ExchangeItem) {
    Card(
        modifier = Modifier.padding(16.dp),
        border = BorderStroke(1.dp, UtilsColor.orange_500),
        shape = RoundedCornerShape(4.dp),
        colors = CardDefaults.cardColors(
            containerColor = UtilsColor.gray_50
        )
    ) {
        Column {
            LabelItemDetail(label = stringResource(id = R.string.item_name), value = item.name)
            LabelItemDetail(
                label = stringResource(id = R.string.item_exchange_id),
                value = item.exchangeId
            )
            LabelItemDetail(
                label = stringResource(id = R.string.label_detail_website),
                value = item.website
            )
            LabelItemDetail(
                label = stringResource(id = R.string.label_data_quote_start),
                value = item.dataQuoteStart.formatDate()
            )
            LabelItemDetail(
                label = stringResource(id = R.string.label_data_quote_end),
                value = item.dataQuoteEnd.formatDate()
            )
            LabelItemDetail(
                label = stringResource(id = R.string.label_data_orderbook_start),
                value = item.dataOrderBookStart.formatDate()
            )
            LabelItemDetail(
                label = stringResource(id = R.string.label_data_orderbook_end),
                value = item.dataOrderBookEnd.formatDate()
            )
            LabelItemDetail(
                label = stringResource(id = R.string.label_data_trade_start),
                value = item.dataTradeStart.formatDate()
            )
            LabelItemDetail(
                label = stringResource(id = R.string.label_data_trade_end),
                value = item.dataTradeEnd.formatDate()
            )
            LabelItemDetail(
                label = stringResource(id = R.string.label_data_symbols_count),
                value = item.dataSymbolsCount.toString()
            )
            LabelItemDetail(
                label = stringResource(id = R.string.label_volume_one_hour_usd),
                value = item.volumeOneHrsUsd.toString()
            )
            LabelItemDetail(
                label = stringResource(id = R.string.label_volume_one_day_usd),
                value = item.volumeOneDayUsd.toString()
            )
            LabelItemDetail(
                label = stringResource(id = R.string.label_volume_one_month_usd),
                value = item.volumeOneMthUsd.toString()
            )
            LabelItemDetail(
                label = stringResource(id = R.string.label_rank),
                value = item.rank.toString()
            )
            LabelItemDetail(
                label = stringResource(id = R.string.integration_status),
                value = item.integrationStatus
            )
            if (item.listMetricId.isNotEmpty()) {
                ScreenMetrics(item.listMetricId)
            }
        }
        Spacer(modifier = Modifier.width(8.dp))
    }
}

@Composable
fun LabelItemDetail(label: String, value: String) {
    Row(
        modifier = Modifier.fillMaxWidth()
            .padding(start = 16.dp, top = 8.dp , bottom = 8.dp)){
        Text(
            text = label,
            fontSize = 14.sp,
            fontWeight = FontWeight.Bold,
            color = UtilsColor.orange_700
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

@Composable
fun ScreenMetrics(listMetricId: List<String>) {
    Text(
        modifier = Modifier.padding(16.dp),
        text = stringResource(R.string.label_metric_id),
        fontSize = 14.sp,
        fontWeight = FontWeight.Bold,
        color = UtilsColor.orange_700
    )
    listMetricId.forEach {
        Text(
            modifier = Modifier.padding(start = 16.dp, bottom = 4.dp),
            text = it,
            fontSize = 14.sp,
            fontWeight = FontWeight.Normal,
            color = Color.Gray
        )
    }
}