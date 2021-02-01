package org.sochidrive.poplib.mvp.model.repo.retrofit

import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import org.sochidrive.poplib.mvp.model.api.IDataSource
import org.sochidrive.poplib.mvp.model.entity.GithubRepository
import org.sochidrive.poplib.mvp.model.entity.GithubUser
import org.sochidrive.poplib.mvp.model.entity.room.Database
import org.sochidrive.poplib.mvp.model.entity.room.RoomGithubRepository
import org.sochidrive.poplib.mvp.model.network.INetworkStatus
import org.sochidrive.poplib.mvp.model.repo.IGithubRepositoriesRepo
import java.lang.RuntimeException

class RetrofitGithubRepositoriesRepo(val api: IDataSource, val networkStatus: INetworkStatus, val db: Database) :
    IGithubRepositoriesRepo {
    override fun getRepositories(user: GithubUser) = networkStatus.isOnlineSingle().flatMap {isOnline ->
        if (isOnline) {
            user.reposUrl?.let {url ->
                api.getRepositories(url).flatMap {repositories ->
                    Single.fromCallable{
                        val roomUser = user.login?.let { db.userDao.findByLogin(it) } ?: throw RuntimeException("No such user in cache")
                        val roomRepos = repositories.map { RoomGithubRepository(it.id ?: "", it.name ?: "", it.forksCount ?: 0, roomUser.id) }

                        db.repositoryDao.insert(roomRepos)

                        repositories
                    }
                }
            }
        } else {
            Single.fromCallable {
                val roomUser = user.login?.let { db.userDao.findByLogin(it) } ?: throw RuntimeException("No such user in cache")
                db.repositoryDao.findForUser(roomUser.id).map { GithubRepository(it.id, it.name, it.forksCount) }
            }
        }
    }.subscribeOn(Schedulers.io())
}