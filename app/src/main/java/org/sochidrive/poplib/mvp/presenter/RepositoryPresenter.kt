package org.sochidrive.poplib.mvp.presenter

import moxy.MvpPresenter
import org.sochidrive.poplib.mvp.model.entity.GithubRepository
import org.sochidrive.poplib.mvp.view.RepositoryView
import ru.terrakok.cicerone.Router
import javax.inject.Inject

class RepositoryPresenter(val githubRepository: GithubRepository) : MvpPresenter<RepositoryView>() {

    @Inject lateinit var router: Router

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.init()
        viewState.setId(githubRepository.id ?: "")
        viewState.setTitle(githubRepository.name ?: "")
        viewState.setForksCount(githubRepository.forksCount.toString() ?: "")
        viewState.setFullName(githubRepository.fullName?: "")
    }

    fun backPressed(): Boolean {
        router.exit()
        return true
    }
}