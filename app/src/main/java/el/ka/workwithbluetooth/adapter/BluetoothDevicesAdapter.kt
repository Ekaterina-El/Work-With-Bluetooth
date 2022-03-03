package el.ka.workwithbluetooth.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import el.ka.workwithbluetooth.R
import el.ka.workwithbluetooth.model.BluetoothDevice
import kotlinx.android.synthetic.main.item.view.*

class BluetoothDevicesAdapter(
    private val listener: Listener
) : RecyclerView.Adapter<BluetoothDevicesAdapter.ViewHolder>() {
    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    private val devices = mutableListOf<BluetoothDevice>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.itemView.item_device_name.text = devices[position].name
        holder.itemView.item_device_mac_address.text = devices[position].mac
    }

    override fun getItemCount() = devices.size

    fun clear() {
        val didItems = devices.size - 1
        devices.clear()
        notifyItemRangeRemoved(0, didItems)
    }

    fun addDevice(device: BluetoothDevice) {
        devices.add(device)
        notifyItemInserted(devices.size)
    }

    override fun onViewAttachedToWindow(holder: ViewHolder) {
        super.onViewAttachedToWindow(holder)
        holder.itemView.setOnClickListener { listener.OnClick(devices[holder.adapterPosition]) }
    }

    override fun onViewDetachedFromWindow(holder: ViewHolder) {
        super.onViewDetachedFromWindow(holder)
        holder.itemView.setOnClickListener(null)
    }


    interface Listener {
        fun OnClick(item: BluetoothDevice)
    }
}