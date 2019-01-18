package com.thg.domain.repository

import com.thg.domain.entity.Repo
import com.thg.domain.entity.User
import io.reactivex.Single

interface GitHubBrowserRepository {
    fun getUserInfo(userName: String): Single<User>
    fun getUserRepos(userName: String): Single<List<Repo>>
}