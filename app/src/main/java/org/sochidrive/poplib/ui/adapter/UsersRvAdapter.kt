package org.sochidrive.poplib.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_user.view.*
import org.sochidrive.poplib.R
import org.sochidrive.poplib.mvp.model.image.IImageLoader
import org.sochidrive.poplib.mvp.presenter.list.IUsersListPresenter
import org.sochidrive.poplib.mvp.view.list.UserItemView

class UsersRvAdapter(val presenter: IUsersListPresenter, val imageLoader : IImageLoader<ImageView>) : RecyclerView.Adapter<UsersRvAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_user, parent, false))


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.pos = position
        holder.containerView.setOnClickListener { presenter.itemClickListener?.invoke(holder) }
        presenter.bindView(holder)
    }

    override fun getItemCount() = presenter.getCount()

    inner class ViewHolder(override val containerView: View) : RecyclerView.ViewHolder(containerView), UserItemView, LayoutContainer {
        override var pos = -1

        override fun setLogin(text: String) = with(containerView){
            tv_login.text = text
        }

        override fun loadAvatar(url: String) = with(containerView) {
            imageLoader.loadInto(url, iv_avatar)
        }
    }

}