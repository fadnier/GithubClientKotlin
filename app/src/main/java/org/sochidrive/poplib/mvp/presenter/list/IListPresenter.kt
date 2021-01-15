package org.sochidrive.poplib.mvp.presenter.list

import org.sochidrive.poplib.mvp.view.list.IItemView

interface IListPresenter<V : IItemView> {
    var itemClickListener: ((V) -> Unit)?
    fun bindView(view: V)
    fun getCount(): Int
}