package org.sochidrive.poplib.di.modules

import dagger.Module
import dagger.Provides
import org.sochidrive.poplib.mvp.model.api.IDataSource
import org.sochidrive.poplib.mvp.model.cache.IGithubRepositoriesCache
import org.sochidrive.poplib.mvp.model.cache.IGithubUsersCache
import org.sochidrive.poplib.mvp.model.network.INetworkStatus
import org.sochidrive.poplib.mvp.model.repo.IGithubRepositoriesRepo
import org.sochidrive.poplib.mvp.model.repo.IGithubUsersRepo
import org.sochidrive.poplib.mvp.model.repo.retrofit.RetrofitGithubRepositoriesRepo
import org.sochidrive.poplib.mvp.model.repo.retrofit.RetrofitGithubUsersRepo
import javax.inject.Singleton

@Module
class RepoModule {
    @Singleton
    @Provides
    fun usersRepo(api: IDataSource, networkStatus: INetworkStatus, cache: IGithubUsersCache) : IGithubUsersRepo =
        RetrofitGithubUsersRepo(api, networkStatus, cache)

    @Singleton
    @Provides
    fun repositoriesRepo(api: IDataSource, networkStatus: INetworkStatus, cache: IGithubRepositoriesCache): IGithubRepositoriesRepo =
        RetrofitGithubRepositoriesRepo(api, networkStatus, cache)
}