package com.sun.livescore.data.repository

import com.sun.livescore.data.model.score.fixture.DataFixture
import com.sun.livescore.data.model.score.fixture.Fixture
import com.sun.livescore.data.model.score.fixture.FixtureResponse
import com.sun.livescore.data.model.score.history.DataHistory
import com.sun.livescore.data.model.score.history.History
import com.sun.livescore.data.model.score.history.HistoryResponse
import com.sun.livescore.data.remote.ScoreDataSource
import com.sun.livescore.data.remote.ScoreRemoteDataSource
import io.reactivex.Single

class ScoreRepository(private val scoreDataSource: ScoreRemoteDataSource) : ScoreDataSource.Remote {
    override fun getScoresHistory(
        key: String,
        secret: String,
        from: String,
        to: String
    ): Single<HistoryResponse> =
        scoreDataSource.getScoresHistory(key, secret, from, to)

    override fun getScoresFixtures(
        key: String,
        secret: String,
        date: String
    ): Single<FixtureResponse> =
        scoreDataSource.getScoresFixtures(key, secret, date)
}
