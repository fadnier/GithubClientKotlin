package org.sochidrive.poplib.mvp.model.api

import io.reactivex.rxjava3.core.Single
import org.sochidrive.poplib.mvp.model.entity.GithubUser
import retrofit2.http.GET

interface IDataSource {
    @GET("/users")
    fun getUsers(): Single<List<GithubUser>>
}