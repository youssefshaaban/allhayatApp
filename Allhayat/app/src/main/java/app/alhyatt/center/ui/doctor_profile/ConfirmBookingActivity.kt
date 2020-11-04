package app.alhyatt.center.ui.doctor_profile

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

import app.alhyatt.center.R

import app.alhyatt.center.base.BaseActivity
import app.alhyatt.center.base.BaseViewModel
import app.alhyatt.center.databinding.ActivityConfirmBookingBinding
import app.alhyatt.center.ui.MainActivity

class ConfirmBookingActivity : BaseActivity<ActivityConfirmBookingBinding>() {


    lateinit var binding: ActivityConfirmBookingBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = getViewDataBinding()
        binding.back.setOnClickListener {
            onBackPressed()
        }
        binding.done.setOnClickListener {
            startActivity(
                MainActivity.getIntent(this)
                    .addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TOP)
                    .putExtra("booking", true)
            )
        }
    }

    companion object {
        fun getIntent(context: Context): Intent =
            Intent(context, ConfirmBookingActivity::class.java)
    }

    override fun getLayoutId(): Int {
        return R.layout.activity_confirm_booking
    }

    override fun getViewModel(): BaseViewModel? {
        return null
    }
}