package org.sochidrive.poplib.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import kotlinx.android.synthetic.main.fragment_user.*
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter
import org.sochidrive.poplib.App
import org.sochidrive.poplib.R
import org.sochidrive.poplib.mvp.model.entity.GithubUser
import org.sochidrive.poplib.mvp.presenter.UserPresenter
import org.sochidrive.poplib.mvp.view.UserView
import org.sochidrive.poplib.ui.BackButtonListener
import org.sochidrive.poplib.ui.adapter.ReposotoriesRVAdapter

class UserFragment() : MvpAppCompatFragment(), UserView, BackButtonListener {

    companion object {
        private const val USER_ARG = "user"
        fun newInstance(user: GithubUser) = UserFragment().apply {
            arguments = Bundle().apply { putParcelable(USER_ARG,user) }
        }
    }

    var adapter: ReposotoriesRVAdapter? = null

    val presenter: UserPresenter by moxyPresenter {
        val user: GithubUser = arguments?.getParcelable<GithubUser>(USER_ARG) as GithubUser
        UserPresenter(
            AndroidSchedulers.mainThread(),
            user).apply { App.instance.appComponent.inject(this) }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?) =
        View.inflate(context, R.layout.fragment_user, null)

    override fun init() {
        rv_repositories.layoutManager = LinearLayoutManager(context)
        adapter = ReposotoriesRVAdapter(presenter.repositoriesListPresenter)
        rv_repositories.adapter = adapter
    }

    override fun updateList() {
        adapter?.notifyDataSetChanged()
    }

    override fun backPressed() = presenter.backClick()

}