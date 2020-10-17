package com.example.allhayat

import android.app.Application
import com.example.allhayat.data.AppPreferencesHelper
import com.example.allhayat.di.AppInjector

import org.koin.android.ext.android.get


class MyApp : Application() {

    lateinit var appPreferencesHelper: AppPreferencesHelper

    override fun onCreate() {
        super.onCreate()
        AppInjector.init(this)
        instance = this
        appPreferencesHelper = get()

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