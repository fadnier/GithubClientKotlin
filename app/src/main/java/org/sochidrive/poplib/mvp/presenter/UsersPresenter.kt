package org.sochidrive.poplib.mvp.presenter

import io.reactivex.rxjava3.core.Scheduler
import moxy.MvpPresenter
import org.sochidrive.poplib.mvp.model.entity.GithubUser
import org.sochidrive.poplib.mvp.model.repo.IGithubUsersRepo
import org.sochidrive.poplib.mvp.presenter.list.IUsersListPresenter
import org.sochidrive.poplib.mvp.view.UsersView
import org.sochidrive.poplib.mvp.view.list.UserItemView
import org.sochidrive.poplib.navigation.Screens
import ru.terrakok.cicerone.Router
import javax.inject.Inject

class UsersPresenter(val mainThreadScheduler: Scheduler) : MvpPresenter<UsersView>() {
    @Inject
    lateinit var usersRepo: IGithubUsersRepo
    @Inject
    lateinit var router: Router

    class UsersListPresenter : IUsersListPresenter {
        override var itemClickListener: ((UserItemView) -> Unit)? = null

        val users = mutableListOf<GithubUser>()

        override fun bindView(view: UserItemView) {
            val user = users[view.pos]

            user.login?.let { view.setLogin(it) }
            user.avatarUrl?.let {view.loadAvatar(it)}
        }

        override fun getCount() = users.size
    }

    val usersListPresenter = UsersListPresenter()

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.init()
        loadData()

        usersListPresenter.itemClickListener = { view ->
            router.navigateTo(Screens.UserScreen(usersListPresenter.users[view.pos]))
        }
    }

    fun loadData() {
        usersRepo.getUsers()
            .observeOn(mainThreadScheduler)
            .subscribe({ users ->
                usersListPresenter.users.clear()
                usersListPresenter.users.addAll(users)
                viewState.updateUsersList()
            }, {
                println("Error: ${it.message}")
            })
    }

    fun backClick(): Boolean {
        router.exit()
        return true
    }
}