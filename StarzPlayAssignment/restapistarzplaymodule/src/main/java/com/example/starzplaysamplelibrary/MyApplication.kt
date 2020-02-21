package com.example.starzplaysamplelibrary

import android.app.Application
import android.content.Context
import android.content.ContextWrapper
import android.content.res.Configuration
import androidx.multidex.MultiDexApplication
import com.akexorcist.localizationactivity.core.LocalizationApplicationDelegate
import com.example.starzplaysamplelibrary.modules.dataSourceModule
import com.example.starzplaysamplelibrary.modules.networkModule
import com.example.starzplaysamplelibrary.modules.viewModelModule

import com.pixplicity.easyprefs.library.Prefs
import org.koin.android.ext.android.startKoin

class MyApplication : MultiDexApplication() {

    val localizationDelegate = LocalizationApplicationDelegate(this)

    override fun onCreate() {
        super.onCreate()



        startKoin(
            androidContext = this,
            modules = listOf(
                networkModule,
                viewModelModule,
                dataSourceModule
            )
        )

        Prefs.Builder()
            .setContext(this)
            .setMode(ContextWrapper.MODE_PRIVATE)
            .setPrefsName(packageName)
            .setUseDefaultSharedPreference(true)
            .build()

    }

    override fun attachBaseContext(base: Context) {
        super.attachBaseContext(localizationDelegate.attachBaseContext(base))
    }


    override fun onConfigurationChanged(newConfig: Configuration?) {
        super.onConfigurationChanged(newConfig)
        localizationDelegate.onConfigurationChanged(this)
    }

    override fun getApplicationContext(): Context {
        return localizationDelegate.getApplicationContext(super.getApplicationContext())
    }
}