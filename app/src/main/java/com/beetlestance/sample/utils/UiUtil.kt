package com.beetlestance.sample.utils

import androidx.core.text.parseAsHtml
import com.google.android.material.textview.MaterialTextView
import java.text.SimpleDateFormat
import java.util.*

fun MaterialTextView.appendWifiLog(state: Int) {
    appendText("Wifi", wifiStateMap[state] ?: wifiStateUnknown)
}
fun MaterialTextView.appendBluetoothLog(state: Int) {
    appendText("Bluetooth", bluetoothStateMap[state] ?: bluetoothStateUnknown)
}

fun MaterialTextView.appendText(logType: String, appendText: String) {
    val isEmpty = text.isEmpty()
    if (isEmpty) append("<font color=#2e7d32>Bluetooth Logs:</font>".parseAsHtml())
    append("\n")
    append("<font color=#2e7d32>${currentTime()}—> </font> $logType $appendText".parseAsHtml())

    post {
        val scrollAmount = layout.getLineTop(lineCount) - height
        // if there is no need to scroll, scrollAmount will be <=0
        if (scrollAmount > 0)
            scrollTo(0, scrollAmount)
        else
            scrollTo(0, 0)
    }
}

private fun currentTime(): String {
    return inputSimpleDateFormat("hh:mm")
        .format(Date(System.currentTimeMillis()))
}

private fun inputSimpleDateFormat(pattern: String) =
    SimpleDateFormat(pattern, Locale.getDefault())
