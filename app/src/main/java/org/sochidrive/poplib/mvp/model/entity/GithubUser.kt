package org.sochidrive.poplib.mvp.model.entity

import android.os.Parcelable
import io.reactivex.rxjava3.core.Observable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class GithubUser(
    val login: String
) : Parcelable {
    fun getLogin() = Observable.fromCallable { return@fromCallable login }
}