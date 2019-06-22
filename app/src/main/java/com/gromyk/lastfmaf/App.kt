package com.gromyk.lastfmaf

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import org.koin.dsl.KoinAppDeclaration

class App: Application() {
    override fun onCreate() {
        super.onCreate()
        initKoin()
    }

    private fun initKoin() {
        val modules = listOf(
            com.gromyk.api.di.api,
            com.gromyk.lastfmaf.di.viewModels
        )
        startKoin {
            androidContext(this@App)
            modules(modules)
        }
    }
}