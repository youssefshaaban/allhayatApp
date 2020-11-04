package app.alhyatt.center.ui.profile

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import app.alhyatt.center.base.BaseViewModel
import app.alhyatt.center.data.pojo.User
import app.alhyatt.center.data.repositery.AccountRepo
import app.alhyatt.center.utilis.rx.SchedulerProvider
import app.alhyatt.center.utilis.rx.with
import okhttp3.MultipartBody

class ProfileViewModel(
    private val accountRepo: AccountRepo,
    private val schedulerProvider: SchedulerProvider
) : BaseViewModel() {


    var pathes: List<String>? = null
    var userData = MutableLiveData<User>()
    fun uploadImage(part: MultipartBody.Part) {
        setLoading(true)
        add {
            accountRepo.uploadImage(part).with(schedulerProvider).subscribe({
                pathes = it.images
                setLoading(false)
            }, {
                setErrorNetwork(it)
            })
        }
    }

    fun updateProfile(map: HashMap<String?, String?>) {
        setLoading(true)
        if (!pathes.isNullOrEmpty()) {
            map["image"] = pathes!![0]
        }
        add {
            accountRepo.updateProfile(map = map).with(schedulerProvider).subscribe({
                if (it.status) {
                    userData.value = it.user
                } else {
                    setErrorMessage(it.message)
                }
                setLoading(false)
            }, {
                setErrorNetwork(it)
            })
        }
    }
}