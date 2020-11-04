package app.alhyatt.center.ui.signup

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.os.Message
import app.alhyatt.center.R
import app.alhyatt.center.base.BaseActivity
import app.alhyatt.center.base.BaseViewModel
import app.alhyatt.center.data.pojo.User
import app.alhyatt.center.databinding.ActivitySignupBinding
import app.alhyatt.center.ui.MainActivity
import app.alhyatt.center.ui.MapsActivity
import app.alhyatt.center.utilis.AppUtils
import app.alhyatt.center.utilis.NAME
import app.alhyatt.center.utilis.SavePrefs
import app.alhyatt.center.utilis.USER_NAME
import com.google.firebase.iid.FirebaseInstanceId
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.util.*
import java.util.regex.Pattern

class SignupActivity : BaseActivity<ActivitySignupBinding>() {
    lateinit var binding: ActivitySignupBinding
    val viewModel: SignupViewModel by viewModel()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = getViewDataBinding()
        binding.back.setOnClickListener { onBackPressed() }
        binding.AddressEt.setOnClickListener {
            startActivityForResult(MapsActivity.getIntent(this), 1)
        }
        binding.continueButton.setOnClickListener {
            if (validateInput()) {
                signup()

            }
        }
        viewModel.userSocial.observe(this, androidx.lifecycle.Observer {
            SavePrefs(this, User::class.java).save(it)
            startActivity(MainActivity.getIntent(this))
            finishAffinity()
        })
        setDataInView()
    }

    private fun setDataInView() {
        intent.extras?.getString(USER_NAME)?.let {
            binding.emailEt.setText(it)
        }
        intent.extras?.getString(NAME)?.let {
            binding.nameEt.setText(it)
        }
    }

    private fun signup() {
        val body = HashMap<String?, String?>()
        body["username"] = binding.emailEt.getText().toString()
        body["name"] = binding.nameEt.getText().toString()
        body["password"] = binding.passEt.getText().toString()
        //body["gender"] = gender
        //body["blood_type"] = bloodType
        body["hayat_id"] = binding.hayatIdEt.getText().toString()
        body["mobile"] = binding.mobileEt.getText().toString()
        body["location"] = binding.AddressEt.getText().toString()
        body["address"] = binding.AddressEt.getText().toString()
        val token = FirebaseInstanceId.getInstance().token
        body["token"] = token

        viewModel.signup(body)
    }

    private fun validateInput(): Boolean {
        var isValid = true
        if (binding.nameEt.text.isBlank()) {
            binding.nameEt.error = getString(R.string.field_required)
            isValid = false
        }
        if (binding.emailEt.text.isBlank()) {
            binding.emailEt.error = getString(R.string.field_required)
            isValid = false
        }
        if (binding.emailEt.text.isNotBlank() && !AppUtils.isEmailValid(binding.emailEt.text.toString())) {
            binding.emailEt.error = getString(R.string.invalidemail)
            isValid = false
        }
        if (binding.mobileEt.text.isBlank()) {
            binding.mobileEt.error = getString(R.string.field_required)
            isValid = false
        }
        val regexEg = "^(010|011|012|015)[0-9]{8}$"
        val isvalidNumber = Pattern.matches(regexEg, binding.mobileEt.text.toString())
        if (binding.mobileEt.text.isNotBlank() && !isvalidNumber) {
            binding.mobileEt.error = getString(R.string.invalidPhone)
            isValid = false
        }
        if (binding.passEt.text.isBlank()) {
            binding.passEt.setError(getString(R.string.field_required))
            isValid = false
        }
        if (binding.passEt.text.isNotBlank() && binding.passEt.text.length < 6) {
            binding.passEt.setError(getString(R.string.password_is_short))
            isValid = false
        }
        if (binding.repassEt.text.isBlank()) {
            binding.repassEt.setError(getString(R.string.field_required))
            isValid = false
        }
        if (binding.repassEt.text.isNotBlank() && binding.repassEt.text.toString() != binding.passEt.text.toString()) {
            binding.repassEt.setError(getString(R.string.confirmPassword_not_match))
            isValid = false
        }
        if (binding.hayatIdEt.text.isBlank()) {
            binding.hayatIdEt.setError(getString(R.string.field_required))
            isValid = false
        }
        return isValid
    }

    companion object {
        fun getIntent(context: Context): Intent = Intent(context, SignupActivity::class.java)
    }

    override fun getLayoutId(): Int {
        return R.layout.activity_signup
    }

    override fun getViewModel(): BaseViewModel? {
        return viewModel
    }

    val handler = object : Handler(Looper.getMainLooper()) {
        override fun handleMessage(msg: Message) {
            when (msg.what) {
                1 -> {
                    val bundle = msg.data
                    binding.AddressEt.setText(bundle.getString("address", ""))
                }
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == 1 && resultCode == Activity.RESULT_OK && data != null) {
            val mLat = data.getDoubleExtra("lat", 0.0)
            val mLon = data.getDoubleExtra("lon", 0.0)
            AppUtils.getAddress(handler, this, mLat, mLon)
        }
        super.onActivityResult(requestCode, resultCode, data)
    }
}
