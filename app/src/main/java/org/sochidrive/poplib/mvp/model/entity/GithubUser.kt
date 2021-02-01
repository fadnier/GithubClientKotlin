package org.sochidrive.poplib.mvp.model.entity

import android.os.Parcelable
import com.google.gson.annotations.Expose
import io.reactivex.rxjava3.core.Observable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class GithubUser(
    @Expose val id: String,
    @Expose val login : String,
    @Expose val avatarUrl: String? = null,
    @Expose val reposUrl: String? = null
) : Parcelable {
    fun getLogin() = Observable.fromCallable { return@fromCallable login }
}