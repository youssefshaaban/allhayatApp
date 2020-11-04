package app.alhyatt.center

import android.app.Application
import app.alhyatt.center.data.AppPreferencesHelper
import app.alhyatt.center.di.AppInjector
import com.facebook.FacebookSdk
import com.facebook.appevents.AppEventsLogger


import org.koin.android.ext.android.get


class MyApp : Application() {

    lateinit var appPreferencesHelper: AppPreferencesHelper

    override fun onCreate() {
        super.onCreate()
        AppInjector.init(this)
        instance = this
        appPreferencesHelper = get()
        FacebookSdk.sdkInitialize(this);
        AppEventsLogger.activateApp(this);
    }


    fun getappPreferencesHelper(): AppPreferencesHelper {
        return appPreferencesHelper
    }

    companion object {
        lateinit var instance: MyApp

        fun getApp(): MyApp {
            return instance
        }

    }


}