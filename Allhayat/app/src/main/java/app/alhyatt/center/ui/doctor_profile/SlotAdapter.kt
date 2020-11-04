package app.alhyatt.center.ui.doctor_profile

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView

import app.alhyatt.center.R
import app.alhyatt.center.databinding.ItemSlotBinding

class SlotAdapter():
    RecyclerView.Adapter<SlotAdapter.SlotViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SlotViewHolder {
     return SlotViewHolder(DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.item_slot,parent,false))
    }

    override fun getItemCount(): Int {
        return  5
    }

    override fun onBindViewHolder(holder: SlotViewHolder, position: Int) {
        holder.bind(position)
    }


    class SlotViewHolder(private val binding: ItemSlotBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(pos:Int){

        }
    }


}