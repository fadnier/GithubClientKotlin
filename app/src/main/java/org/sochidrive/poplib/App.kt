package org.sochidrive.poplib

import android.app.Application
import dagger.internal.DaggerCollections
import org.sochidrive.poplib.di.AppComponent
import org.sochidrive.poplib.di.DaggerAppComponent
import org.sochidrive.poplib.di.modules.AppModule
import org.sochidrive.poplib.mvp.model.entity.room.Database
import ru.terrakok.cicerone.Cicerone
import ru.terrakok.cicerone.Router

class App : Application() {

    lateinit var appComponent: AppComponent
        private set

    companion object {
        lateinit var instance: App
    }

    override fun onCreate() {
        super.onCreate()
        instance = this

        appComponent = DaggerAppComponent.builder()
            .appModule(AppModule(this))
            .build()
    }
}