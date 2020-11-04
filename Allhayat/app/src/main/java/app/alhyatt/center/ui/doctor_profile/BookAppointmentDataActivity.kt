package app.alhyatt.center.ui.doctor_profile

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

import app.alhyatt.center.R
import app.alhyatt.center.base.BaseActivity
import app.alhyatt.center.base.BaseViewModel
import app.alhyatt.center.databinding.ActivityBookAppointmentDataBinding

class BookAppointmentDataActivity : BaseActivity<ActivityBookAppointmentDataBinding>() {
    lateinit var binding: ActivityBookAppointmentDataBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=getViewDataBinding()
        binding.back.setOnClickListener {
            onBackPressed()
        }
        binding.book.setOnClickListener {
            startActivity(ConfirmBookingActivity.getIntent(this))
        }
    }


    override fun getLayoutId(): Int {
        return R.layout.activity_book_appointment_data
    }

    override fun getViewModel(): BaseViewModel? {
        return null
    }

    companion object {
        fun getIntent(context: Context): Intent = Intent(context, BookAppointmentDataActivity::class.java)
    }
}