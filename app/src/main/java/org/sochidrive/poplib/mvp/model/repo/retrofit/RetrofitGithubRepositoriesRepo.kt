package org.sochidrive.poplib.mvp.model.repo.retrofit

import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import org.sochidrive.poplib.mvp.model.api.IDataSource
import org.sochidrive.poplib.mvp.model.cache.room.RoomGithubRepositoriesCache
import org.sochidrive.poplib.mvp.model.entity.GithubRepository
import org.sochidrive.poplib.mvp.model.entity.GithubUser
import org.sochidrive.poplib.mvp.model.entity.room.Database
import org.sochidrive.poplib.mvp.model.entity.room.RoomGithubRepository
import org.sochidrive.poplib.mvp.model.network.INetworkStatus
import org.sochidrive.poplib.mvp.model.repo.IGithubRepositoriesRepo
import java.lang.RuntimeException

class RetrofitGithubRepositoriesRepo(val api: IDataSource, val networkStatus: INetworkStatus, val cache: RoomGithubRepositoriesCache) :
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