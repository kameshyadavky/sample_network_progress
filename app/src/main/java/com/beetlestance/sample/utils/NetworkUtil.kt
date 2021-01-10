package com.beetlestance.sample.utils

import android.bluetooth.BluetoothAdapter
import android.net.wifi.WifiManager
import androidx.core.text.parseAsHtml
import com.google.android.material.textview.MaterialTextView
import java.text.SimpleDateFormat
import java.util.*

val wifiStateMap = mapOf(
    WifiManager.WIFI_STATE_ENABLED to "ON",
    WifiManager.WIFI_STATE_DISABLED to "OFF",
    WifiManager.WIFI_STATE_ENABLING to "TURNING ON",
    WifiManager.WIFI_STATE_DISABLING to "TURNING_OFF"
)

const val wifiStateUnknown = "NA"

val bluetoothStateMap = mapOf(
    BluetoothAdapter.STATE_ON to "ON",
    BluetoothAdapter.STATE_OFF to "OFF",
    BluetoothAdapter.STATE_CONNECTED to "CONNECTED",
    BluetoothAdapter.STATE_CONNECTING to "CONNECTING",
    BluetoothAdapter.STATE_DISCONNECTED to "DISCONNECTED",
    BluetoothAdapter.STATE_DISCONNECTING to "DISCONNECTING",
    BluetoothAdapter.STATE_TURNING_ON to "TURNING ON",
    BluetoothAdapter.STATE_TURNING_OFF to "TURNING OFF"
)

const val bluetoothStateUnknown = "NA"
