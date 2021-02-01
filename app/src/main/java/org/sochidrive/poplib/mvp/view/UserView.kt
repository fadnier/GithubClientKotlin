package org.sochidrive.poplib.mvp.view

import moxy.MvpView
import moxy.viewstate.strategy.alias.AddToEndSingle

@AddToEndSingle
interface UserView: MvpView {
    fun init()
    fun updateList()
}