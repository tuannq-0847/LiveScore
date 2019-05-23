package com.sun.livescore.data.remote.score

import com.sun.livescore.data.model.score.fixture.FixtureResponse
import com.sun.livescore.data.model.score.history.HistoryResponse
import io.reactivex.Single

interface ScoreDataSource {
    interface Remote {
        fun getScoresFixtures(date: String): Single<FixtureResponse>
        fun getScoresHistory(from: String, to: String): Single<HistoryResponse>
        fun getLiveScores(): Single<HistoryResponse>
    }
}
