package app.alhyatt.center.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController

import app.alhyatt.center.R

import app.alhyatt.center.base.BaseActivity
import app.alhyatt.center.base.BaseViewModel
import app.alhyatt.center.databinding.ActivityMainBinding


class MainActivity : BaseActivity<ActivityMainBinding>() {
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = getViewDataBinding()
        val navView: BottomNavigationView = findViewById(R.id.nav_view)

        val navController = findNavController(R.id.nav_host_fragment)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
//        val appBarConfiguration = AppBarConfiguration(setOf(
//            R.id.navigation_home,
//            R.id.navigation_profile,
//            R.id.navigation_notifications,
//            R.id.navigation_more,
//            R.id.navigation_booking
//        ))
//        setupActionBarWithNavController(navController, appBarConfiguration)
        val  book=intent.extras?.getBoolean("booking")
        if (book!=null&&book){
            navController.navigate(R.id.myBooking)
        }
        navView.setupWithNavController(navController)
        binding.addActivity.setOnClickListener {
            navController.navigate(R.id.home)
        }

    }

    override fun getLayoutId(): Int {
        return R.layout.activity_main
    }

    override fun getViewModel(): BaseViewModel? {
        return null
    }

    companion object {
        fun getIntent(context: Context): Intent = Intent(context, MainActivity::class.java).apply {
            addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK)
        }
    }
}