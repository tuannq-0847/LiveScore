package com.sun.livescore.data.remote

import com.sun.livescore.data.model.score.fixture.FixtureResponse
import com.sun.livescore.data.model.score.history.HistoryResponse
import com.sun.livescore.data.remote.request.ApiService
import io.reactivex.Single

class ScoreRemoteDataSource(private val apiService: ApiService) : ScoreDataSource.Remote {
    override fun getScoresHistory(from: String, to: String): Single<HistoryResponse> = apiService.getScoresHistory(from, to)
    override fun getScoresFixtures(date: String): Single<FixtureResponse> = apiService.getScoresFixtures(date)
}
