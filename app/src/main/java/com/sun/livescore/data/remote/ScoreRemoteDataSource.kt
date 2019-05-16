package com.sun.livescore.data.remote

import com.sun.livescore.data.model.score.fixture.DataFixture
import com.sun.livescore.data.model.score.fixture.Fixture
import com.sun.livescore.data.model.score.fixture.FixtureResponse
import com.sun.livescore.data.model.score.history.DataHistory
import com.sun.livescore.data.model.score.history.History
import com.sun.livescore.data.model.score.history.HistoryResponse
import com.sun.livescore.data.remote.request.ApiService
import io.reactivex.Single

class ScoreRemoteDataSource(private val apiService: ApiService) : ScoreDataSource.Remote {
    override fun getScoresHistory(
        key: String,
        secret: String,
        from: String,
        to: String
    ): Single<HistoryResponse> =
        apiService.getScoresHistory(key, secret, from, to)

    override fun getScoresFixtures(
        key: String,
        secret: String,
        date: String
    ): Single<FixtureResponse> =
        apiService.getScoresFixtures(key, secret, date)
}
