package app.alhyatt.center.ui.booking


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView

import app.alhyatt.center.R
import app.alhyatt.center.databinding.ItemBookingAppointmentBinding


class AppointmentAdapter():
    RecyclerView.Adapter<AppointmentAdapter.NotificationViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotificationViewHolder {
     return NotificationViewHolder(DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.item_booking_appointment,parent,false))
    }

    override fun getItemCount(): Int {
        return  10
    }

    override fun onBindViewHolder(holder: NotificationViewHolder, position: Int) {
        holder.bind(position)
    }


    class NotificationViewHolder(private val binding: ItemBookingAppointmentBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(pos:Int){
            if (pos%2==0){
                binding.status.background=ContextCompat.getDrawable(binding.root.context,R.drawable.back_visit_appointment)
                binding.status.text=binding.root.context.getString(R.string.title_visited)
            }else{
                binding.status.background=ContextCompat.getDrawable(binding.root.context,R.drawable.back_cancelled)
                binding.status.text=binding.root.context.getString(R.string.title_cancelled)
            }
        }
    }


}