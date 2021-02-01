package org.sochidrive.poplib.mvp.model.repo.retrofit

import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import org.sochidrive.poplib.mvp.model.api.IDataSource
import org.sochidrive.poplib.mvp.model.cache.IGithubUsersCache
import org.sochidrive.poplib.mvp.model.entity.GithubUser
import org.sochidrive.poplib.mvp.model.entity.room.Database
import org.sochidrive.poplib.mvp.model.entity.room.RoomGithubUser
import org.sochidrive.poplib.mvp.model.network.INetworkStatus
import org.sochidrive.poplib.mvp.model.repo.IGithubUsersRepo

class RetrofitGithubUsersRepo(val api: IDataSource, val networkStatus: INetworkStatus, val cache: IGithubUsersCache) : IGithubUsersRepo {
    override fun getUsers() = networkStatus.isOnlineSingle().flatMap { isOnline ->
        if (isOnline) {
            api.getUsers().flatMap {users ->
                cache.putUsers(users).toSingleDefault(users)
            }
        } else {
            cache.getUsers()
        }
    }.subscribeOn(Schedulers.io())
}