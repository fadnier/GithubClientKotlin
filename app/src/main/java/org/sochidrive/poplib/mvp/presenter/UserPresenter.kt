package org.sochidrive.poplib.mvp.presenter

import io.reactivex.rxjava3.core.Scheduler
import moxy.MvpPresenter
import org.sochidrive.poplib.App
import org.sochidrive.poplib.mvp.model.entity.GithubRepository
import org.sochidrive.poplib.mvp.model.entity.GithubUser
import org.sochidrive.poplib.mvp.model.repo.IGithubRepositoriesRepo
import org.sochidrive.poplib.mvp.presenter.list.IRepositoryListPresenter
import org.sochidrive.poplib.mvp.view.UserView
import org.sochidrive.poplib.mvp.view.list.RepositoryItemView
import org.sochidrive.poplib.navigation.Screens
import ru.terrakok.cicerone.Router
import javax.inject.Inject

class UserPresenter (val mainThreadScheduler: Scheduler, val user: GithubUser) : MvpPresenter<UserView>() {

    @Inject
    lateinit var router: Router

    @Inject lateinit var repositoriesRepo: IGithubRepositoriesRepo

    class RepositoriesListPresenter : IRepositoryListPresenter {
        val repositories = mutableListOf<GithubRepository>()
        override var itemClickListener: ((RepositoryItemView) -> Unit)? = null
        override fun getCount() = repositories.size

        override fun bindView(view: RepositoryItemView) {
            val repository = repositories[view.pos]
            repository.name?.let { view.setName(it) }
        }
    }

    val repositoriesListPresenter = RepositoriesListPresenter()

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.init()
        loadData()
        App.instance.appComponent.inject(this)
        repositoriesListPresenter.itemClickListener = { itemView ->
            val repository = repositoriesListPresenter.repositories[itemView.pos]
            router.navigateTo(Screens.RepositoryScreen(repository))
        }
    }

    fun loadData() {
        repositoriesRepo.getRepositories(user)
            .observeOn(mainThreadScheduler)
            .subscribe({ repositories ->
                repositoriesListPresenter.repositories.clear()
                repositoriesListPresenter.repositories.addAll(repositories)
                viewState.updateList()
            }, {
                println("Error: ${it.message}")
            })
    }

    fun backClick(): Boolean {
        router.exit()
        return true
    }
}