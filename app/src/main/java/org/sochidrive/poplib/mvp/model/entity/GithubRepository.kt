package org.sochidrive.poplib.mvp.model.entity

import android.os.Parcelable
import com.google.gson.annotations.Expose
import kotlinx.android.parcel.Parcelize

@Parcelize
class GithubRepository (
    @Expose val id: String,
    @Expose val name: String? = null,
    @Expose val forksCount: String? = null,
    @Expose val fullName: String? = null
): Parcelable