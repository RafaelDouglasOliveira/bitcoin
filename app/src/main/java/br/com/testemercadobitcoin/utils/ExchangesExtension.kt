package br.com.testemercadobitcoin.utils

import br.com.testemercadobitcoin.utils.Constants.FORMAT_DATE_RESPONSE
import br.com.testemercadobitcoin.utils.Constants.FORMAT_DATE_UI
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.Locale

fun String.formatDate(): String {
    val inputFormat = SimpleDateFormat(FORMAT_DATE_RESPONSE, Locale.US)
    val outputFormat = SimpleDateFormat(FORMAT_DATE_UI, Locale("pt", "BR"))
    return try {
        val date = inputFormat.parse(this)
        outputFormat.format(date)
    } catch (e: ParseException) {
       "Fora do formato esperado"
    }
}