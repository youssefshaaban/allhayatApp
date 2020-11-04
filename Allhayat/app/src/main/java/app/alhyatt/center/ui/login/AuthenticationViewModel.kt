package app.alhyatt.center.ui.login

import androidx.lifecycle.MutableLiveData
import app.alhyatt.center.base.BaseViewModel
import app.alhyatt.center.data.pojo.User
import app.alhyatt.center.data.repositery.AccountRepo
import app.alhyatt.center.utilis.rx.SchedulerProvider
import app.alhyatt.center.utilis.rx.with


class AuthenticationViewModel(private val accountRepo: AccountRepo, private val schedulerProvider: SchedulerProvider) :
    BaseViewModel(){

    val userSocial=MutableLiveData<User>()
    val gotToSignUp=MutableLiveData<Boolean>()
    fun loginSocial(userName:String){
        setLoading(true)
        add {
            accountRepo.loginSocial(userName).with(schedulerProvider).subscribe({
                if (it.status){
                    userSocial.value=it.user
                }else{
                    gotToSignUp.value=true
                }
                setLoading(false)
            },{
                setErrorNetwork(it)
            })
        }
    }
    fun login(mobile:String,password:String){
        setLoading(true)
        add {
            accountRepo.login(mobile,password).with(schedulerProvider).subscribe({
                if (it.status){
                    userSocial.value=it.user
                }else{
                    gotToSignUp.value=true
                }
                setLoading(false)
            },{
                setErrorNetwork(it)
            })
        }
    }
}