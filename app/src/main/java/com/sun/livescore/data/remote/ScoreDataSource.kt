package com.sun.livescore.data.remote

import com.sun.livescore.data.model.score.fixture.DataFixture
import com.sun.livescore.data.model.score.fixture.Fixture
import com.sun.livescore.data.model.score.fixture.FixtureResponse
import com.sun.livescore.data.model.score.history.DataHistory
import com.sun.livescore.data.model.score.history.History
import com.sun.livescore.data.model.score.history.HistoryResponse
import io.reactivex.Single

interface ScoreDataSource {
    interface Remote {
        fun getScoresFixtures(key: String, secret: String, date: String): Single<FixtureResponse>
        fun getScoresHistory(
            key: String,
            secret: String,
            from: String,
            to: String
        ): Single<HistoryResponse>
    }
}
