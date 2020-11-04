package app.alhyatt.center.ui

import android.animation.ObjectAnimator
import android.animation.PropertyValuesHolder
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.os.Handler
import android.util.Base64
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import app.alhyatt.center.R
import app.alhyatt.center.data.pojo.User
import app.alhyatt.center.ui.login.LoginActivity
import app.alhyatt.center.utilis.SavePrefs


import kotlinx.android.synthetic.main.activity_splash.*
import java.security.MessageDigest

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        printHashKey(this)
        val scaleDown: ObjectAnimator = ObjectAnimator.ofPropertyValuesHolder(
            image,
            PropertyValuesHolder.ofFloat("scaleX", 1.2f),
            PropertyValuesHolder.ofFloat("scaleY", 1.2f)
        )
        scaleDown.duration = 700
        scaleDown.repeatCount = ObjectAnimator.INFINITE
        scaleDown.repeatMode = ObjectAnimator.REVERSE
        scaleDown.start()
        val handler = Handler()
        handler.postDelayed({
            if (SavePrefs(this@SplashActivity, User::class.java).load() != null) {
                startActivity(Intent(this@SplashActivity, MainActivity::class.java))
                finish()
            } else {
                startActivity(Intent(this@SplashActivity, LoginActivity::class.java))
                finish()
            }
        }, 2800)
    }
}

fun printHashKey(pContext: Context) {
    try {
        val info = pContext.packageManager
            .getPackageInfo(pContext.packageName, PackageManager.GET_SIGNATURES)
        for (signature in info.signatures) {
            val md = MessageDigest.getInstance("SHA")
            md.update(signature.toByteArray())
            val hashKey = String(Base64.encode(md.digest(), 0))
            Log.i("KeyHash", "printHashKey() Hash Key: $hashKey")
        }
        val sha1 = byteArrayOf(
            0xA1.toByte(),
            0x1A.toByte(),
            0x44,
            0x1E,
            0x45.toByte(),
            0x66.toByte(),
            0xB4.toByte(),
            0xF2.toByte(),
            0x40.toByte(),
            0xFC.toByte(),
            0x6D.toByte(),
            0x52,
            0xA7.toByte(),
            0x8C.toByte(),
            0x9D.toByte(),
            0x83.toByte(),
            0xED.toByte(),
            0x59.toByte(),
            0x24,
            0xC4.toByte()
        )

        Log.e("keyhash111", Base64.encodeToString(sha1, Base64.NO_WRAP))

    } catch (e: java.lang.Exception) {
        Log.e("KeyHash", "printHashKey()", e)
    }
}