package com.sun.livescore.data.repository

import com.sun.livescore.data.model.score.fixture.FixtureResponse
import com.sun.livescore.data.model.score.history.HistoryResponse
import com.sun.livescore.data.remote.score.ScoreDataSource
import com.sun.livescore.data.remote.score.ScoreRemoteDataSource
import io.reactivex.Single

class ScoreRepository(private val scoreDataSource: ScoreRemoteDataSource) : ScoreDataSource.Remote {
    override fun getLiveScores(): Single<HistoryResponse> =
        scoreDataSource.getLiveScores()

    override fun getScoresHistory(from: String, to: String): Single<HistoryResponse> =
        scoreDataSource.getScoresHistory(from, to)

    override fun getScoresFixtures(date: String): Single<FixtureResponse> = scoreDataSource.getScoresFixtures(date)
}
