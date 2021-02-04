package org.sochidrive.poplib.mvp.model.repo.retrofit

import io.reactivex.rxjava3.schedulers.Schedulers
import org.sochidrive.poplib.mvp.model.api.IDataSource
import org.sochidrive.poplib.mvp.model.cache.IGithubRepositoriesCache
import org.sochidrive.poplib.mvp.model.entity.GithubUser
import org.sochidrive.poplib.mvp.model.network.INetworkStatus
import org.sochidrive.poplib.mvp.model.repo.IGithubRepositoriesRepo

class RetrofitGithubRepositoriesRepo(val api: IDataSource, val networkStatus: INetworkStatus, val cache: IGithubRepositoriesCache) :
    IGithubRepositoriesRepo {
    override fun getRepositories(user: GithubUser) = networkStatus.isOnlineSingle().flatMap {isOnline ->
        if (isOnline) {
            user.reposUrl?.let {url ->
                api.getRepositories(url).flatMap {repositories ->
                    cache.putUserRepos(user,repositories).toSingleDefault(repositories)
                }
            }
        } else {
            cache.getUserRepos(user)
        }
    }.subscribeOn(Schedulers.io())
}