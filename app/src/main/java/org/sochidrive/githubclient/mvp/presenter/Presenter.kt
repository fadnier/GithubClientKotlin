package org.sochidrive.githubclient.mvp.presenter

import org.sochidrive.githubclient.mvp.model.Model
import org.sochidrive.githubclient.mvp.view.MainView

class Presenter(private val view: MainView) {
    private val model: Model = Model()

    private val INDEX0 = 0
    private val INDEX1 = 1
    private val INDEX2 = 2

    fun getCurrent1(): Int = model.getCurrent(INDEX0)
    fun getCurrent2(): Int = model.getCurrent(INDEX1)
    fun getCurrent3(): Int = model.getCurrent(INDEX2)

    fun counterClick1() {
        view.setButtonText1(model.next(INDEX0).toString())
    }
    fun counterClick2()   {
        view.setButtonText2(model.next(INDEX1).toString())
    }
    fun counterClick3()  {
        view.setButtonText3(model.next(INDEX2).toString())
    }
}