package org.sochidrive.poplib.mvp.model.repo

import io.reactivex.rxjava3.core.Single
import org.sochidrive.poplib.mvp.model.entity.GithubUser

interface IGithubUsersRepo {
    fun getUsers(): Single<List<GithubUser>>
}