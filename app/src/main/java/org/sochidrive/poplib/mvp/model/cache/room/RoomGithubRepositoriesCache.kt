package org.sochidrive.poplib.mvp.model.cache.room

import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import org.sochidrive.poplib.mvp.model.cache.IGithubRepositoriesCache
import org.sochidrive.poplib.mvp.model.entity.GithubRepository
import org.sochidrive.poplib.mvp.model.entity.GithubUser
import org.sochidrive.poplib.mvp.model.entity.room.Database
import org.sochidrive.poplib.mvp.model.entity.room.RoomGithubRepository

class RoomGithubRepositoriesCache(var db: Database) : IGithubRepositoriesCache {

    override fun getUserRepos(user: GithubUser) = Single.fromCallable {
        val roomUser = db.userDao.findByLogin(user.login) ?: throw RuntimeException("No such user in cache")
        return@fromCallable db.repositoryDao.findForUser(roomUser.id)
                .map { GithubRepository(it.id, it.name, it.forksCount, it.fullName) }

    }.subscribeOn(Schedulers.io())

    override fun putUserRepos(user: GithubUser, repositories: List<GithubRepository>) = Completable.fromAction {
        val roomUser =   db.userDao.findByLogin(user.login)  ?: throw RuntimeException("No such user in cache")
        val roomRepos = repositories.map {
            RoomGithubRepository(it.id, it.name ?: "", it.forksCount ?: 0, it.fullName?:"", roomUser.id)
        }
        db.repositoryDao.insert(roomRepos)

    }.subscribeOn(Schedulers.io())
}