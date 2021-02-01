package org.sochidrive.poplib.mvp.model.image

interface IImageLoader<T> {
    fun loadInto(url: String, container: T)
}