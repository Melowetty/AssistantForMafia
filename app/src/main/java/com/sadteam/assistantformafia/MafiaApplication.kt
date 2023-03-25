package com.sadteam.assistantformafia

import android.app.Application

class MafiaApplication: Application() {
    override fun onCreate() {
        super.onCreate()
        instance = this
    }
    companion object {
        lateinit var instance: MafiaApplication
    }
}