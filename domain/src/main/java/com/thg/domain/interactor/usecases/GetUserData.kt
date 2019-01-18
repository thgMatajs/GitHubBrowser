package com.thg.domain.interactor.usecases

import com.thg.domain.entity.Repo
import com.thg.domain.entity.User
import com.thg.domain.interactor.SingleUseCase
import com.thg.domain.repository.GitHubBrowserRepository
import com.thg.domain.schedulers.SchedulersProvider
import io.reactivex.Single
import io.reactivex.functions.BiFunction

class GetUserData(
    private val gitHubRepository: GitHubBrowserRepository,
    schedulersProvider: SchedulersProvider
) : SingleUseCase<Pair<User, List<Repo>>, String>(schedulersProvider) {

    override fun buildUseCaseSingle(userName: String): Single<Pair<User, List<Repo>>> =
        Single.zip(
            gitHubRepository.getUserInfo(userName = userName),
            gitHubRepository.getUserRepos(userName = userName),
            BiFunction<User, List<Repo>, Pair<User, List<Repo>>> { t1, t2 ->
                Pair(t1, t2.sortedByDescending { it.startCount })
            }
        )
}