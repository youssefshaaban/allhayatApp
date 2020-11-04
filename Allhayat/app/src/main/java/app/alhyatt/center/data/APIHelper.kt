package app.alhyatt.center.data



import app.alhyatt.center.data.reponse.ResponseUpload
import app.alhyatt.center.data.reponse.ResponseUser
import com.google.gson.JsonObject
import io.reactivex.Single
import okhttp3.MultipartBody
import retrofit2.Call
import retrofit2.http.*
import java.util.*


interface APIHelper {
    @GET("global/login")
    fun login(@Query("username") map: String?): Single<ResponseUser>

    @GET("global/login")
    fun login(@Query("mobile") mobile: String?,@Query("password") password: String?): Single<ResponseUser>

    @GET("global/signup")
    fun signUp(@QueryMap map: HashMap<String?, String?>?): Single<ResponseUser>

    @Multipart
    @POST("global/upload_image_api")
    fun UpdateImage(@Part uploadedFile: MultipartBody.Part?): Single<ResponseUpload>


    @GET("global/edit")
    fun editProfile(@QueryMap map: HashMap<String?, String?>?): Single<ResponseUser>



}