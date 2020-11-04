package app.alhyatt.center.utilis
import android.app.Activity
import android.graphics.Color
import android.os.Build
import android.view.View
import android.view.WindowManager

const val SPLASH_SCREEN_DELAY = 1500L
const val REQUEST_TIMEOUT = 60
val BASE_URL:String = "https://medicure-eg.net/hayat/"
const val BASE_URL_IMAGE="http://medicure-eg.net/hayat/prod_img/"
const val prefFileName = "com.smartzone.edura"
const val ambuklance="15011"
const val ID_KEY="id"
const val CATEGORY_ID="category_id"
const val CATEGORY_NAME="category_name"
const val USER_NAME="username"
const val NAME="name"
fun Activity.makeStatusBarTransparent() {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
        window.apply {
            clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
            addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                decorView.systemUiVisibility =
                    View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or (View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR)
            } else {
                decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
            }
            statusBarColor = Color.TRANSPARENT
        }
    }
}