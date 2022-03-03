package el.ka.workwithbluetooth

import android.annotation.SuppressLint
import android.bluetooth.BluetoothAdapter
import android.bluetooth.BluetoothDevice
import android.bluetooth.BluetoothManager
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private lateinit var btAdapter: BluetoothAdapter
    private lateinit var adapter: BluetoothDevicesAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initRecyclerView()
        init()
    }

    private fun initRecyclerView() {
        adapter = BluetoothDevicesAdapter()
        this.rv_devices.adapter = adapter
    }

    private fun init() {
        val btManager = getSystemService(Context.BLUETOOTH_SERVICE) as  BluetoothManager
        btAdapter = btManager.adapter
        getPairedDevices()
    }

    @SuppressLint("MissingPermission", "NewApi")
    private fun getPairedDevices() {
        adapter.clear()
        val pairedDevices: Set<BluetoothDevice> = btAdapter.bondedDevices
        pairedDevices.forEach { btDevice ->
            adapter.addDevice(el.ka.workwithbluetooth.BluetoothDevice(
                btDevice.name,
                btDevice.address
            ))
        }
    }
}

