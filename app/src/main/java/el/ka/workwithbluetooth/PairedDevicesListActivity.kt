package el.ka.workwithbluetooth

import android.annotation.SuppressLint
import android.bluetooth.BluetoothAdapter
import android.bluetooth.BluetoothDevice
import android.bluetooth.BluetoothManager
import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import el.ka.workwithbluetooth.adapter.BluetoothDevicesAdapter
import kotlinx.android.synthetic.main.activity_paired_devices_list.*

class PairedDevicesListActivity : AppCompatActivity(), BluetoothDevicesAdapter.Listener {
    private lateinit var btAdapter: BluetoothAdapter
    private lateinit var adapter: BluetoothDevicesAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_paired_devices_list)

        initRecyclerView()
        init()
    }

    private fun initRecyclerView() {
        adapter = BluetoothDevicesAdapter(this)
        this.rv_devices.adapter = adapter
    }

    private fun init() {
        val btManager = getSystemService(Context.BLUETOOTH_SERVICE) as BluetoothManager
        btAdapter = btManager.adapter
        getPairedDevices()
    }

    @SuppressLint("MissingPermission", "NewApi")
    private fun getPairedDevices() {
        adapter.clear()
        val pairedDevices: Set<BluetoothDevice> = btAdapter.bondedDevices
        pairedDevices.forEach { btDevice ->
            adapter.addDevice(
                el.ka.workwithbluetooth.model.BluetoothDevice(
                    btDevice.name,
                    btDevice.address
                )
            )
        }
    }

    companion object {
        const val DEVICE_KEY = "device_info"
    }

    override fun OnClick(item: el.ka.workwithbluetooth.model.BluetoothDevice) {
        val i = Intent().apply {
            putExtra(DEVICE_KEY, item)
        }
        setResult(RESULT_OK, i)
        finish()
    }
}

