package com.github.emmpann.bagimam

import android.app.Application
import com.github.emmpann.bagimam.di.viewModelModule
import com.github.emmpann.core.di.preferencesManager
import com.github.emmpann.core.di.repositoryModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class Application : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger(Level.NONE)
            androidContext(this@Application)
            modules(
                listOf(
                    repositoryModule,
                    viewModelModule,
                    preferencesManager
                )
            )
        }
    }
}