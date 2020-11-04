package app.alhyatt.center.ui.doctor_profile

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager


import app.alhyatt.center.R
import app.alhyatt.center.base.BaseActivity
import app.alhyatt.center.base.BaseViewModel
import app.alhyatt.center.databinding.ActivityDoctorProfileBinding


class DoctorProfileActivity : BaseActivity<ActivityDoctorProfileBinding>() {
    lateinit var binding: ActivityDoctorProfileBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=getViewDataBinding()
        binding.recycle.layoutManager=LinearLayoutManager(this)
        binding.recycle.adapter=SlotAdapter()
        binding.myProfile.requestFocus()
        binding.back.setOnClickListener {
            onBackPressed()
        }
        binding.book.setOnClickListener {
            startActivity(BookAppointmentDataActivity.getIntent(this))
        }

    }

    override fun getLayoutId(): Int {
        return R.layout.activity_doctor_profile
    }

    override fun getViewModel(): BaseViewModel? {
        return null
    }
    companion object {
        fun getIntent(context: Context): Intent = Intent(context, DoctorProfileActivity::class.java)
    }
}