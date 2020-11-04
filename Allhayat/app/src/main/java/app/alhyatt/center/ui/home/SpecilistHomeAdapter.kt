package app.alhyatt.center.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView

import app.alhyatt.center.R
import app.alhyatt.center.databinding.ItemSpecialityBinding

class SpecilistHomeAdapter():
    RecyclerView.Adapter<SpecilistHomeAdapter.SpecilistyViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SpecilistyViewHolder {
     return SpecilistyViewHolder(DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.item_speciality,parent,false))
    }

    override fun getItemCount(): Int {
        return  10
    }

    override fun onBindViewHolder(holder: SpecilistyViewHolder, position: Int) {

    }


    class SpecilistyViewHolder(itemView: ItemSpecialityBinding) : RecyclerView.ViewHolder(itemView.root) {
        fun bind(pos:Int){

        }
    }


}