package app.alhyatt.center.data.repositery


import app.alhyatt.center.data.APIHelper
import app.alhyatt.center.data.reponse.ResponseUpload
import app.alhyatt.center.data.reponse.ResponseUser
import io.reactivex.Single
import okhttp3.MultipartBody
import java.util.HashMap

class AccountRepo (private val apiHelper: APIHelper){

    fun loginSocial(email:String):Single<ResponseUser>{
        return apiHelper.login(email)
    }
    fun login(mobile:String,password:String):Single<ResponseUser>{
        return apiHelper.login(mobile,password)
    }
    fun signUp(map: HashMap<String?, String?>?):Single<ResponseUser>{
        return apiHelper.signUp(map)
    }
    fun uploadImage(multiPart: MultipartBody.Part):Single<ResponseUpload>{
        return apiHelper.UpdateImage(multiPart)
    }
    fun updateProfile(map: HashMap<String?, String?>):Single<ResponseUser>{
        return apiHelper.editProfile(map)
    }
}