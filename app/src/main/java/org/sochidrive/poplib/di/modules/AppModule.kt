package org.sochidrive.poplib.di.modules

import dagger.Module
import dagger.Provides
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Scheduler
import org.sochidrive.poplib.App

@Module
class AppModule(val app: App) {
    @Provides
    fun app(): App {
        return app
    }

    @Provides
    fun mainThreadScheduler(): Scheduler = AndroidSchedulers.mainThread()
}