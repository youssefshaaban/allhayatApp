package app.alhyatt.center.ui.search

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager

import app.alhyatt.center.R
import app.alhyatt.center.base.BaseActivity
import app.alhyatt.center.base.BaseViewModel
import app.alhyatt.center.databinding.ActivitySearchBinding
import app.alhyatt.center.ui.doctor_profile.DoctorProfileActivity


class SearchActivity : BaseActivity<ActivitySearchBinding>() {
    lateinit var binding: ActivitySearchBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=getViewDataBinding()
        binding.recyclerView.layoutManager=LinearLayoutManager(this)
        binding.recyclerView.adapter=DoctorListAdapter(){
            startActivity(DoctorProfileActivity.getIntent(this))
        }
        binding.recyclerViewSpecilist.layoutManager=LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false)
        binding.recyclerViewSpecilist.adapter=SpecilistAdapter()
    }

    override fun getLayoutId(): Int {
        return R.layout.activity_search
    }

    override fun getViewModel(): BaseViewModel? {
        return null
    }


    companion object {
        fun getIntent(context: Context): Intent = Intent(context, SearchActivity::class.java)
    }
}