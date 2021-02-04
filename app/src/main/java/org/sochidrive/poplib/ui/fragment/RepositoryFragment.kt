package org.sochidrive.poplib.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_repository.*
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter
import org.sochidrive.poplib.App
import org.sochidrive.poplib.R
import org.sochidrive.poplib.mvp.model.entity.GithubRepository
import org.sochidrive.poplib.mvp.presenter.RepositoryPresenter
import org.sochidrive.poplib.mvp.view.RepositoryView
import org.sochidrive.poplib.ui.BackButtonListener
import org.sochidrive.poplib.ui.adapter.ReposotoriesRVAdapter

class RepositoryFragment : MvpAppCompatFragment(), RepositoryView, BackButtonListener {

    companion object {
        private const val REPOSITORY_ARG = "repository"

        fun newInstance(repository: GithubRepository) = RepositoryFragment().apply {
            arguments = Bundle().apply {
                putParcelable(REPOSITORY_ARG, repository)
            }
        }
    }

    var adapter: ReposotoriesRVAdapter? = null

    val presenter: RepositoryPresenter by moxyPresenter {
        val repository = arguments?.getParcelable<GithubRepository>(REPOSITORY_ARG) as GithubRepository
        RepositoryPresenter(repository).apply { App.instance.appComponent.inject(this) }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?) =
        View.inflate(context, R.layout.fragment_repository, null)

    override fun init() {}

    override fun setId(text: String) {
        tv_id.text = text
    }

    override fun setTitle(text: String) {
        tv_title.text = text
    }

    override fun setForksCount(text: String) {
        tv_forksCount.text = text
    }

    override fun setFullName(text: String) {
        tv_fullname.text = text
    }

    override fun backPressed() = presenter.backPressed()
}