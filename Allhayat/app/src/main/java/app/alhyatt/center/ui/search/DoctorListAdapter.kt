package app.alhyatt.center.ui.search

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView

import app.alhyatt.center.R
import app.alhyatt.center.databinding.ItemDoctorBinding

class DoctorListAdapter(private val clickDoc:()->Unit):
    RecyclerView.Adapter<DoctorListAdapter.DoctorViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DoctorViewHolder {
     return DoctorViewHolder(DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.item_doctor,parent,false))
    }

    override fun getItemCount(): Int {
        return  10
    }

    override fun onBindViewHolder(holder: DoctorViewHolder, position: Int) {
        holder.bind(position)
    }


   inner class DoctorViewHolder(val binding: ItemDoctorBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(pos:Int){
            binding.root.setOnClickListener {
                clickDoc()
            }
        }
    }


}