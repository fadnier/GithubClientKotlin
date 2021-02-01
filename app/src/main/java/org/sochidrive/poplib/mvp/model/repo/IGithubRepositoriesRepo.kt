package org.sochidrive.poplib.mvp.model.repo

import io.reactivex.rxjava3.core.Single
import org.sochidrive.poplib.mvp.model.entity.GithubRepository
import org.sochidrive.poplib.mvp.model.entity.GithubUser

interface IGithubRepositoriesRepo {
    fun getRepositories(user: GithubUser): Single<List<GithubRepository>>
}