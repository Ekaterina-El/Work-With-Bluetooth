package el.ka.workwithbluetooth

import android.annotation.SuppressLint
import android.app.Activity
import android.bluetooth.BluetoothAdapter
import android.bluetooth.BluetoothDevice
import android.bluetooth.BluetoothManager
import android.bluetooth.le.ScanCallback
import android.bluetooth.le.ScanFilter
import android.bluetooth.le.ScanResult
import android.bluetooth.le.ScanSettings
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.content.pm.PackageManager
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.ParcelUuid
import android.util.Log
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import kotlinx.android.synthetic.main.activity_main.*
import java.util.jar.Manifest
class MainActivity : AppCompatActivity() {
    private lateinit var btAdapter: BluetoothAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        init()
    }

    private fun init() {
        val btManager = getSystemService(Context.BLUETOOTH_SERVICE) as  BluetoothManager
        btAdapter = btManager.adapter
        getPairedDevices()
    }

    @SuppressLint("MissingPermission", "NewApi")
    private fun getPairedDevices() {
        val pairedDevices: Set<BluetoothDevice> = btAdapter.bondedDevices
        pairedDevices.forEach { btDevice ->
            Log.i("Paired_Devices", "Device: ${btDevice.name} - ${btDevice.bondState} - ${btDevice.address} - ${btDevice.alias} - ${btDevice.type}")
        }
    }
}

