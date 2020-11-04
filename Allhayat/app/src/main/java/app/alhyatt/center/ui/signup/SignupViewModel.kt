package app.alhyatt.center.ui.signup

import androidx.lifecycle.MutableLiveData
import app.alhyatt.center.base.BaseViewModel
import app.alhyatt.center.data.pojo.User
import app.alhyatt.center.data.repositery.AccountRepo
import app.alhyatt.center.utilis.rx.SchedulerProvider
import app.alhyatt.center.utilis.rx.with
import java.util.HashMap


class SignupViewModel(private val accountRepo: AccountRepo, private val schedulerProvider: SchedulerProvider) :
    BaseViewModel(){

    val userSocial=MutableLiveData<User>()

    fun signup(map:HashMap<String?,String?>){
        setLoading(true)
        add {
            accountRepo.signUp(map).with(schedulerProvider).subscribe({
                if (it.status){
                    userSocial.value=it.user
                }else{
                    setErrorMessage(it.message)
                }
                setLoading(false)
            },{
                setErrorNetwork(it)
            })
        }
    }

}