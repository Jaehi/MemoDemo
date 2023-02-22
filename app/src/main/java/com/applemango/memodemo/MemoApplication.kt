package com.applemango.memodemo

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class MemoApplication : Application(){
    override fun onCreate() {
        super.onCreate()
    }
}