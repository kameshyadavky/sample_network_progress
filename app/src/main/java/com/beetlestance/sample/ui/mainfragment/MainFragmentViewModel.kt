package com.beetlestance.sample.ui.mainfragment

import android.bluetooth.BluetoothAdapter
import android.net.wifi.WifiManager
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.work.WorkInfo
import com.beetlestance.sample.di.workerfactory.NetworkStateWorker
import com.beetlestance.sample.tasks.Tasks
import com.beetlestance.sample.utils.*
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import soup.neumorphism.ShapeType
import java.util.concurrent.atomic.AtomicBoolean
import javax.inject.Inject

class MainFragmentViewModel @Inject constructor(
    private val tasks: Tasks
) : ViewModel() {

    var isWifiOn: AtomicBoolean = AtomicBoolean(false)
    var isBluetoothOn: AtomicBoolean = AtomicBoolean(true)

    val bluetoothToggleShape: MutableLiveData<Int> = MutableLiveData()
    val wifiToggleShape: MutableLiveData<Int> = MutableLiveData()

    val bluetoothLog: MutableLiveData<Event<Int>> = MutableLiveData()
    val wifiLogs: MutableLiveData<Event<Int>> = MutableLiveData()

    init {
        viewModelScope.launch {
            tasks.observeNetworkState().collectLatest(::updateOnStateChange)
        }

        tasks.startNetworkStateWork()
    }

    private fun updateOnStateChange(workInfo: WorkInfo?) {
        // return if the status is only providing enqueue information
        if (workInfo?.progress?.keyValueMap.isNullOrEmpty()) return

        val progress = workInfo!!.progress
        val bluetoothState =
            progress.getInt(NetworkStateWorker.bluetoothStatus, BluetoothAdapter.STATE_OFF)
        val wifiState =
            progress.getInt(NetworkStateWorker.wifiStatus, WifiManager.WIFI_STATE_DISABLED)

        val bt = bluetoothState == BluetoothAdapter.STATE_TURNING_ON
                || bluetoothState == BluetoothAdapter.STATE_ON ||
                bluetoothState == BluetoothAdapter.STATE_CONNECTING ||
                bluetoothState == BluetoothAdapter.STATE_CONNECTED

        val wifi = wifiState == WifiManager.WIFI_STATE_ENABLED ||
                wifiState == WifiManager.WIFI_STATE_ENABLING


        isBluetoothOn.set(bt)
        isWifiOn.set(wifi)

        val shapeWifi = if (isWifiOn.get()) ShapeType.BASIN else ShapeType.FLAT
        val shapeBt = if (isBluetoothOn.get()) ShapeType.BASIN else ShapeType.FLAT

        bluetoothToggleShape.postValueIfNew(shapeBt)
        wifiToggleShape.postValueIfNew(shapeWifi)

        bluetoothLog.postIfNew(bluetoothState)
        wifiLogs.postIfNew(wifiState)
    }

}