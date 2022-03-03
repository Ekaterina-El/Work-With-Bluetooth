package el.ka.workwithbluetooth.screens

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import el.ka.workwithbluetooth.PairedDevicesListActivity
import el.ka.workwithbluetooth.PairedDevicesListActivity.Companion.DEVICE_KEY
import el.ka.workwithbluetooth.R
import el.ka.workwithbluetooth.model.BluetoothDevice

class ControlActivity : AppCompatActivity() {
    private lateinit var activityListLauncher: ActivityResultLauncher<Intent>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_control)
        onPairedListResult()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.control_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.connect -> {

            }
            R.id.list -> {
                activityListLauncher.launch(
                    Intent(
                        this,
                        PairedDevicesListActivity::class.java
                    )
                )

            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun onPairedListResult() {
        activityListLauncher =
            registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
                if (it.resultCode == Activity.RESULT_OK) {
                    var device: BluetoothDevice = it.data?.getSerializableExtra(DEVICE_KEY) as BluetoothDevice
                    Log.d("Active_Device", "name: ${device.name}")
                }
            }
    }
}