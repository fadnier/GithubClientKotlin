package org.sochidrive.poplib.mvp.presenter

import moxy.MvpPresenter
import org.sochidrive.poplib.mvp.view.UserView
import ru.terrakok.cicerone.Router

class UserPresenter (val router: Router, val name: String) : MvpPresenter<UserView>() {

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        loadData()
    }

    fun loadData() {
        viewState.displayUser(name)
    }

    fun backClick(): Boolean {
        router.exit()
        return true
    }
}