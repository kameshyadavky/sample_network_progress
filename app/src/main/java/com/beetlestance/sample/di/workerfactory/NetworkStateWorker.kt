package com.beetlestance.sample.di.workerfactory

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.bluetooth.BluetoothAdapter
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.net.wifi.WifiManager
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.work.*
import com.beetlestance.sample.ui.MainActivity
import com.beetlestance.sample.utils.bluetoothStateMap
import com.beetlestance.sample.utils.bluetoothStateUnknown
import com.beetlestance.sample.utils.wifiStateMap
import com.beetlestance.sample.utils.wifiStateUnknown
import com.squareup.inject.assisted.Assisted
import com.squareup.inject.assisted.AssistedInject
import kotlin.coroutines.suspendCoroutine


class NetworkStateWorker @AssistedInject constructor(
    @Assisted private val appContext: Context,
    @Assisted private val params: WorkerParameters
) : CoroutineWorker(appContext, params) {


    var bluetoothState: Int = BluetoothAdapter.STATE_OFF
    var wifiState: Int = WifiManager.WIFI_STATE_DISABLED

    override suspend fun doWork(): Result = suspendCoroutine {
        val networkFilter = IntentFilter(BluetoothAdapter.ACTION_STATE_CHANGED).apply {
            addAction(WifiManager.WIFI_STATE_CHANGED_ACTION)
        }
        appContext.registerReceiver(receiver, networkFilter)

        bluetoothState = BluetoothAdapter.getDefaultAdapter().state
        wifiState =
            (appContext.applicationContext.getSystemService(Context.WIFI_SERVICE) as WifiManager).wifiState

        sendProgress()
        // Set worker for foreground
        setForegroundAsync(createForegroundInfo("BT: ${readableBluetoothState()}, WiFi: ${readableWifiState()}"))
    }

    /**
     * This is working now with the new permissions
     */
    private val receiver: BroadcastReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context?, intent: Intent) {
            val action = intent.action

            if (action == BluetoothAdapter.ACTION_STATE_CHANGED) {
                val state = intent.getIntExtra(
                    BluetoothAdapter.EXTRA_STATE,
                    BluetoothAdapter.ERROR
                )
                bluetoothState = state
            }

            if (action == WifiManager.WIFI_STATE_CHANGED_ACTION) {
                wifiState = intent.getIntExtra(
                    WifiManager.EXTRA_WIFI_STATE,
                    WifiManager.WIFI_STATE_DISABLED
                )
            }
            sendProgress()
            setForegroundAsync(createForegroundInfo("BT: ${readableBluetoothState()}, WiFi: ${readableWifiState()}"))
        }
    }

    private fun sendProgress() {
        val returnData = workDataOf(bluetoothStatus to bluetoothState, wifiStatus to wifiState)
        setProgressAsync(returnData)
    }

    private fun readableBluetoothState() =
        bluetoothStateMap[bluetoothState] ?: bluetoothStateUnknown

    private fun readableWifiState() = wifiStateMap[wifiState] ?: wifiStateUnknown

    private fun createForegroundInfo(title: String): ForegroundInfo {
        val cancelIntent = WorkManager.getInstance(appContext)
            .createCancelPendingIntent(id)

        val notificationIntent = Intent(appContext, MainActivity::class.java)

        val pendingIntent = PendingIntent.getActivity(
            appContext,
            100,
            notificationIntent,
            PendingIntent.FLAG_UPDATE_CURRENT
        )

        val notificationBuilder = createNotificationBuilder(appContext, title).apply {
            addAction(android.R.drawable.ic_delete, "STOP", cancelIntent)
            setContentIntent(pendingIntent)
        }

        return ForegroundInfo(100, notificationBuilder.build())
    }

    private fun createNotificationBuilder(
        context: Context,
        title: String
    ): NotificationCompat.Builder {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            createChannel()
        }

        return NotificationCompat.Builder(context, NETWORK_CHANNEL)
            .setContentTitle(title)
            .setAutoCancel(false)
            .setTicker(title)
            .setSmallIcon(android.R.drawable.btn_default)
            .setOngoing(true)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun createChannel() {
        val channel = NotificationChannel(
            NETWORK_CHANNEL,
            "This channel provides the status of network on device",
            NotificationManager.IMPORTANCE_LOW
        )

        NotificationManagerCompat.from(appContext).createNotificationChannel(channel)
    }

    companion object {
        val TAG: String = NetworkStateWorker::class.java.simpleName
        const val NETWORK_CHANNEL = "network_channel"
        const val bluetoothStatus = "bluetoothState"
        const val wifiStatus = "wifiState"
    }

    @AssistedInject.Factory
    interface NetworkStateWorkerFactory : ChildWorkerFactory
}
