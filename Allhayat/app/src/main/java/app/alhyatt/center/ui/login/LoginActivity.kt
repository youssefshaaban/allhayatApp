package app.alhyatt.center.ui.login

import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Base64
import android.util.Log

import app.alhyatt.center.R

import app.alhyatt.center.base.BaseActivity
import app.alhyatt.center.base.BaseViewModel
import app.alhyatt.center.data.pojo.User
import app.alhyatt.center.databinding.ActivityLoginBinding
import app.alhyatt.center.ui.MainActivity
import app.alhyatt.center.ui.signup.SignupActivity
import app.alhyatt.center.utilis.NAME
import app.alhyatt.center.utilis.SavePrefs
import app.alhyatt.center.utilis.USER_NAME
import app.alhyatt.center.utilis.makeStatusBarTransparent

import com.facebook.*
import com.facebook.login.LoginManager
import com.facebook.login.LoginResult
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.tasks.Task

import org.json.JSONObject
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.security.MessageDigest
import java.util.*

class LoginActivity : BaseActivity<ActivityLoginBinding>() {
    lateinit var binding: ActivityLoginBinding
    val TAG = "LoginActivity"
    var mGoogleSignInClient: GoogleSignInClient? = null
    var RC_SIGN_IN = 0
    var callbackManager: CallbackManager? = null
    var mEmail: String? = null
    var mName: String? = null
    val viewModel: AuthenticationViewModel by viewModel()
    override fun onCreate(savedInstanceState: Bundle?) {
        makeStatusBarTransparent()
        super.onCreate(savedInstanceState)
        binding = getViewDataBinding()
        initFacebookLogin()
        observeViewModel()
        val gso =
            GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build()
        mGoogleSignInClient = GoogleSignIn.getClient(this, gso)
        binding.google.setOnClickListener {
            googleSignIn()
        }
        binding.face.setOnClickListener {
            faceSignIn()
        }
        binding.login.setOnClickListener {
            if (validateInput()) {
                viewModel.login(
                    binding.etMobile.text.toString(),
                    binding.etPassword.text.toString()
                )
            }
        }
        binding.signUp.setOnClickListener {
            startActivity(SignupActivity.getIntent(this))
        }
    }

    private fun validateInput(): Boolean {
        var isValide = true
        if (binding.etMobile.text.isBlank()) {
            binding.etMobile.error = getString(R.string.field_required)
            isValide = false
        }
        if (binding.etPassword.text.isBlank()) {
            binding.etPassword.error = getString(R.string.field_required)
            isValide = false
        }


        return isValide
    }


    private fun googleSignIn() {
        val signInIntent = mGoogleSignInClient!!.signInIntent
        startActivityForResult(signInIntent, RC_SIGN_IN)
    }

    private fun faceSignIn() {
        LoginManager.getInstance().logInWithReadPermissions(
            this@LoginActivity,
            Arrays.asList("public_profile", "email")
        )
    }

    private fun initFacebookLogin() {
        callbackManager = CallbackManager.Factory.create()
        LoginManager.getInstance().registerCallback(callbackManager,
            object : FacebookCallback<LoginResult> {
                override fun onSuccess(loginResult: LoginResult) {
                    //  hideLoading();
                    Log.d(TAG, "FACEBOOK_SUCCESS")
                    val accessToken = loginResult.accessToken
                    accessToken?.let { useLoginInformation(it) }
                }

                override fun onCancel() {
                    Log.e(TAG, "FACEBOOK_CANCELLED")
                    //hideLoading();
                }

                override fun onError(exception: FacebookException) {
                    //hideLoading();
                    Log.e(TAG, "FacebookException: $exception")
                    if (exception is FacebookAuthorizationException) {
                        if (AccessToken.getCurrentAccessToken() != null) {
                            LoginManager.getInstance().logOut()
                        }
                    }
                }
            })
    }

    private fun useLoginInformation(accessToken: AccessToken) {
        val request = GraphRequest.newMeRequest(
            accessToken
        ) { `object`: JSONObject, response: GraphResponse? ->
            try {
                mEmail = `object`.optString("email")
                mName = `object`.optString("name")
                val mImage =
                    "https://graph.facebook.com/" + `object`.optString("id") + "/picture?type=large"
                if (mEmail != null)
                    viewModel.loginSocial(mEmail!!)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
        val parameters = Bundle()
        parameters.putString("fields", "id,name,email,picture.width(200)")
        request.parameters = parameters
        // Initiate the GraphRequest
        request.executeAsync()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        // Result returned from launching the Intent from GoogleSignInClient.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            // The Task returned from this call is always completed, no need to attach
            // a listener.
            val task =
                GoogleSignIn.getSignedInAccountFromIntent(data)
            handleGoogleSignInResult(task)
        } else {
            callbackManager!!.onActivityResult(requestCode, resultCode, data)
        }
    }

    private fun handleGoogleSignInResult(completedTask: Task<GoogleSignInAccount>) {
        try {
            val account =
                completedTask.getResult(ApiException::class.java)
            Log.d(
                TAG,
                "handleSignInResult: " + account!!.givenName + account.familyName
            )
            if (account.email != null)
                viewModel.loginSocial(account.email!!)
            // Signed in successfully, show authenticated UI.
            //updateUI(account);
        } catch (e: ApiException) {
            // The ApiException status code indicates the detailed failure reason.
            // Please refer to the GoogleSignInStatusCodes class reference for more information.
            Log.w(
                TAG,
                "signInResult:failed code=" + e.statusCode
            )
            Log.w(
                TAG,
                "signInResult:failed code=" + e.message
            )
            Log.w(
                TAG,
                "signInResult:failed code=" + e.localizedMessage
            )
        }
    }


    override fun getLayoutId(): Int {
        return R.layout.activity_login
    }

    fun observeViewModel() {
        viewModel.gotToSignUp.observe(this, androidx.lifecycle.Observer {
            startActivity(SignupActivity.getIntent(this).apply {
                putExtra(USER_NAME,mEmail)
                putExtra(NAME,mName)
            })
        })
        viewModel.userSocial.observe(this, androidx.lifecycle.Observer {
            SavePrefs(this, User::class.java).save(it)
            startActivity(MainActivity.getIntent(this))
            finishAffinity()
        })
    }

    override fun getViewModel(): BaseViewModel? {
        return viewModel
    }

}