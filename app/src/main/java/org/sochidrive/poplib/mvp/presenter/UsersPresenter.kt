package org.sochidrive.poplib.mvp.presenter

import moxy.MvpPresenter
import org.sochidrive.poplib.mvp.model.entity.GithubUser
import org.sochidrive.poplib.mvp.model.entity.GithubUsersRepo
import org.sochidrive.poplib.mvp.presenter.list.IUsersListPresenter
import org.sochidrive.poplib.mvp.view.UsersView
import org.sochidrive.poplib.mvp.view.list.UserItemView
import org.sochidrive.poplib.navigation.Screens
import ru.terrakok.cicerone.Router

class UsersPresenter(val router: Router, val usersRepo: GithubUsersRepo) : MvpPresenter<UsersView>() {

    class UsersListPresenter : IUsersListPresenter {
        override var itemClickListener: ((UserItemView) -> Unit)? = null

        val users = mutableListOf<GithubUser>()

        override fun bindView(view: UserItemView) {
            val user = users[view.pos]
            view.setLogin(user.login)
        }

        override fun getCount() = users.size
    }

    val usersListPresenter = UsersListPresenter()

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.init()
        loadData()

        usersListPresenter.itemClickListener = { view ->
            router.navigateTo(Screens.UserScreen(usersListPresenter.users[view.pos].login))
        }
    }

    fun loadData() {
        val users = usersRepo.getUsers().subscribe({
            usersListPresenter.users.clear()
            usersListPresenter.users.addAll(it)
            viewState.updateUsersList()
        },{
            it.printStackTrace()
        })
    }

    fun backClick(): Boolean {
        router.exit()
        return true
    }
}