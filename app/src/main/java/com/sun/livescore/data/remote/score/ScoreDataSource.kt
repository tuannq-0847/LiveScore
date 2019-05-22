package com.sun.livescore.data.remote.score

import com.sun.livescore.data.model.score.fixture.DataFixture
import com.sun.livescore.data.model.score.fixture.Fixture
import com.sun.livescore.data.model.score.fixture.FixtureResponse
import com.sun.livescore.data.model.score.history.DataHistory
import com.sun.livescore.data.model.score.history.History
import com.sun.livescore.data.model.score.history.HistoryResponse
import io.reactivex.Single

interface ScoreDataSource {
    interface Remote {
        fun getScoresFixtures(date: String): Single<FixtureResponse>
        fun getScoresHistory(from: String, to: String): Single<HistoryResponse>
    }
}
