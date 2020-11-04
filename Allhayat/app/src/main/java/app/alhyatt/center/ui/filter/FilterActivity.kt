package app.alhyatt.center.ui.filter

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager

import app.alhyatt.center.R

import app.alhyatt.center.base.BaseActivity
import app.alhyatt.center.base.BaseViewModel
import app.alhyatt.center.databinding.ActivityFilterBinding
import app.alhyatt.center.ui.doctor_profile.DoctorProfileActivity
import app.alhyatt.center.ui.search.DoctorListAdapter
import app.alhyatt.center.ui.search.SearchActivity


class FilterActivity : BaseActivity<ActivityFilterBinding>() {
    lateinit var binding: ActivityFilterBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=getViewDataBinding()
        binding.recyclerView.layoutManager=LinearLayoutManager(this)
        binding.recyclerView.adapter= DoctorListAdapter(){
            startActivity(DoctorProfileActivity.getIntent(this))
        }
        binding.myProfile.requestFocus()
        binding.search.setOnClickListener {
            startActivity(SearchActivity.getIntent(this))
        }
        binding.back.setOnClickListener { onBackPressed() }
    }

    override fun getLayoutId(): Int {
        return R.layout.activity_filter
    }

    override fun getViewModel(): BaseViewModel? {
        return null
    }

    companion object {
        fun getIntent(context: Context): Intent = Intent(context, FilterActivity::class.java)
    }
}