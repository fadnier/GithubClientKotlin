package org.sochidrive.poplib.mvp.model.repo.retrofit

import io.reactivex.rxjava3.schedulers.Schedulers
import org.sochidrive.poplib.mvp.model.api.IDataSource
import org.sochidrive.poplib.mvp.model.repo.IGithubUsersRepo

class RetrofitGithubUsersRepo(val api: IDataSource) : IGithubUsersRepo {
    override fun getUsers() = api.getUsers().subscribeOn(Schedulers.io())
}