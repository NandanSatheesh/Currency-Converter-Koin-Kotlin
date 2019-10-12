package com.example.currency_converter

import android.app.Application
import com.example.currency_converter.modules.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class CurrencyConverterApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger()
            androidContext(this@CurrencyConverterApplication)
            modules(appModule)
        }
    }
}