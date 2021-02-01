package org.sochidrive.poplib.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import kotlinx.android.synthetic.main.fragment_users.*
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter
import org.sochidrive.poplib.ApiHolder
import org.sochidrive.poplib.App
import org.sochidrive.poplib.R
import org.sochidrive.poplib.mvp.model.repo.retrofit.RetrofitGithubUsersRepo
import org.sochidrive.poplib.mvp.presenter.UsersPresenter
import org.sochidrive.poplib.mvp.view.UsersView
import org.sochidrive.poplib.ui.BackButtonListener
import org.sochidrive.poplib.ui.adapter.UsersRvAdapter
import org.sochidrive.poplib.ui.image.GlideImageLoader

class UsersFragment : MvpAppCompatFragment(), UsersView, BackButtonListener {

    companion object {
        fun newInstance() = UsersFragment()
    }

    val presenter by moxyPresenter {
        UsersPresenter(
            AndroidSchedulers.mainThread(),
            RetrofitGithubUsersRepo(ApiHolder.api),
            App.instance.router)
    }

    var adapter: UsersRvAdapter? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?) =
        View.inflate(context, R.layout.fragment_users, null)

    override fun init() {
        rv_users.layoutManager = LinearLayoutManager(requireContext())
        adapter = UsersRvAdapter(presenter.usersListPresenter, GlideImageLoader())
        rv_users.adapter = adapter
    }

    override fun updateUsersList() {
        adapter?.notifyDataSetChanged()
    }

    override fun backPressed() = presenter.backClick()

}