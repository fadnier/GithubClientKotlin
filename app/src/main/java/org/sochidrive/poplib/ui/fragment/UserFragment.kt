package org.sochidrive.poplib.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_user.*
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter
import org.sochidrive.poplib.R
import org.sochidrive.poplib.mvp.presenter.UserPresenter
import org.sochidrive.poplib.mvp.view.UserView
import org.sochidrive.poplib.App
import org.sochidrive.poplib.ui.BackButtonListener

class UserFragment() : MvpAppCompatFragment(), UserView, BackButtonListener {
    companion object {
        private const val USER = "user"
        fun newInstance(name: String) = UserFragment().apply {
            arguments = Bundle().apply { putString(USER,name) }
        }
    }

    val presenter by moxyPresenter {
        val user: String = arguments?.getString(USER) ?: ""
        UserPresenter(App.instance.router, user)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?) =
        View.inflate(context, R.layout.fragment_user, null)

    override fun displayUser(user: String) {
        textView.text = user
    }

    override fun backPressed() = presenter.backClick()

}