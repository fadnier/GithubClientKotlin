package org.sochidrive.poplib.mvp.model.cache

import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single
import org.sochidrive.poplib.mvp.model.entity.GithubRepository
import org.sochidrive.poplib.mvp.model.entity.GithubUser

interface IGithubRepositoriesCache {
    fun getUserRepos(user: GithubUser): Single<List<GithubRepository>>
    fun putUserRepos(user: GithubUser, repositories: List<GithubRepository>): Completable
}