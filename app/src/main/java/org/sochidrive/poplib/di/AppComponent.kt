package org.sochidrive.poplib.di

import dagger.Component
import org.sochidrive.poplib.di.modules.*
import org.sochidrive.poplib.mvp.presenter.MainPresenter
import org.sochidrive.poplib.mvp.presenter.RepositoryPresenter
import org.sochidrive.poplib.mvp.presenter.UserPresenter
import org.sochidrive.poplib.mvp.presenter.UsersPresenter
import org.sochidrive.poplib.ui.MainActivity
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        ApiModule::class,
        AppModule::class,
        DatabaseModule::class,
        CiceroneModule::class,
        RepoModule::class
    ]
)
interface AppComponent {
    fun inject(mainActivity: MainActivity)
    fun inject(mainPresenter: MainPresenter)
    fun inject(usersPresenter: UsersPresenter)
    fun inject(userPresenter: UserPresenter)
    fun inject(repositoryPresenter: RepositoryPresenter)
}