package org.sochidrive.githubclient.mvp.model

class Model {
    private val counters = mutableListOf(0,0,0)

    fun getCurrent(index: Int) = counters[index]

    fun set(index: Int, value: Int) = counters.set(index,value)

    fun next(index: Int): Int {
        counters[index]++
        return getCurrent(index)
    }
}