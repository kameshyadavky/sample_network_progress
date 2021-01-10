package com.beetlestance.sample.ui.mainfragment

import android.bluetooth.BluetoothAdapter
import android.content.Context
import android.content.Intent
import android.net.wifi.WifiManager
import android.os.Bundle
import android.provider.Settings
import android.view.View
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.viewModels
import com.beetlestance.sample.R
import com.beetlestance.sample.databinding.FragmentMainBinding
import com.beetlestance.sample.di.viewmodelfactory.ViewModelFactory
import com.beetlestance.sample.utils.*
import dagger.android.support.DaggerFragment
import soup.neumorphism.ShapeType
import javax.inject.Inject


class MainFragment : DaggerFragment(R.layout.fragment_main), EventListener {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory
    private val viewModel: MainFragmentViewModel by viewModels { viewModelFactory }

    private var binding: FragmentMainBinding? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = bindWithLifecycleOwner {
            viewModel = this@MainFragment.viewModel
            eventListener = this@MainFragment
        }


        viewModel.bluetoothLog.observeEvent(viewLifecycleOwner) {
            binding?.bluetoothState?.appendBluetoothLog(it)
        }
        viewModel.wifiLogs.observeEvent(viewLifecycleOwner) {
            binding?.wifiState?.appendWifiLog(it)
        }
    }

    override fun toggleBluetooth() {
        val bluetoothAdapter = BluetoothAdapter.getDefaultAdapter()
        if (viewModel.isBluetoothOn.get()) {
            bluetoothAdapter.disable()
        } else {
            bluetoothAdapter.enable()
        }
    }

    override fun onViewStateRestored(savedInstanceState: Bundle?) {
        super.onViewStateRestored(savedInstanceState)
        val btLog = savedInstanceState?.getCharSequence("btLog") ?: ""
        val wifiLog = savedInstanceState?.getCharSequence("wifiLog") ?: ""

        binding?.wifiState?.text = wifiLog
        binding?.bluetoothState?.text = btLog
    }

    override fun onSaveInstanceState(outState: Bundle) {
        val wifiLog = binding?.wifiState?.text
        outState.putCharSequence("wifiLog", wifiLog ?: "")
        val btLog = binding?.bluetoothState?.text
        outState.putCharSequence("btLog", btLog ?: "")
        super.onSaveInstanceState(outState)
    }

    private val settingsContract =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
            val wifiManager =
                requireContext().applicationContext.getSystemService(Context.WIFI_SERVICE) as WifiManager
            if (wifiManager.isWifiEnabled) {
                viewModel.wifiToggleShape.postValue(ShapeType.BASIN)
            } else {
                viewModel.wifiToggleShape.postValue(ShapeType.FLAT)
            }
        }

    override fun toggleWifi() {
        if (viewModel.isWifiOn.get()) {
            viewModel.wifiToggleShape.postValue(ShapeType.FLAT)
        } else {
            viewModel.wifiToggleShape.postValue(ShapeType.BASIN)
        }
        val panelIntent = Intent(Settings.ACTION_WIFI_SETTINGS)
        settingsContract.launch(panelIntent)

    }

    override fun onDestroyView() {
        binding = null
        super.onDestroyView()
    }
}


interface EventListener {
    fun toggleBluetooth()
    fun toggleWifi()
}