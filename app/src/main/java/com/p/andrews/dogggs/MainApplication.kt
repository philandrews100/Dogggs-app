package com.p.andrews.dogggs

import android.app.Application
import com.p.andrews.feature.featureModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.GlobalContext.startKoin

class MainApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@MainApplication)
            modules(featureModule)
        }
    }
}
