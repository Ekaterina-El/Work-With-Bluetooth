package el.ka.workwithbluetooth.model

import java.io.Serializable

data class BluetoothDevice(
    val name: String = "",
    var mac: String = ""
): Serializable