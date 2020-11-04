package app.alhyatt.center.ui.notifications

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView


import app.alhyatt.center.R
import app.alhyatt.center.databinding.ItemNotificationBinding


class NotificationAdapter():
    RecyclerView.Adapter<NotificationAdapter.NotificationViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotificationViewHolder {
     return NotificationViewHolder(DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.item_notification,parent,false))
    }

    override fun getItemCount(): Int {
        return  10
    }

    override fun onBindViewHolder(holder: NotificationViewHolder, position: Int) {
        holder.bind(position)
    }


    class NotificationViewHolder(private val binding: ItemNotificationBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(pos:Int){
            if (pos%2==0){
                binding.contentRoot.background=ContextCompat.getDrawable(binding.root.context, R.drawable.linesiderednotification)
                binding.notify.setImageResource(R.drawable.bellred)
            }else{
                binding.contentRoot.background=ContextCompat.getDrawable(binding.root.context,R.drawable.linesidegreennotification)
                binding.notify.setImageResource(R.drawable.bellgreen)
            }
        }
    }


}