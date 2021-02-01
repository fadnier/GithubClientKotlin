package org.sochidrive.poplib.mvp.model.repo.retrofit

import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import org.sochidrive.poplib.mvp.model.api.IDataSource
import org.sochidrive.poplib.mvp.model.entity.GithubUser
import org.sochidrive.poplib.mvp.model.repo.IGithubRepositoriesRepo
import java.lang.RuntimeException

class RetrofitGithubRepositoriesRepo(val api: IDataSource): IGithubRepositoriesRepo {
    override fun getRepositories(user: GithubUser) = user.reposUrl?.let { api.getRepositories(user.reposUrl.toString()).subscribeOn(Schedulers.io()) }
        ?: Single.error(RuntimeException("User has no repos url"))
}