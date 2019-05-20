package com.sun.livescore.data.remote.request

import com.sun.livescore.data.model.leagues.LeagueResponse
import com.sun.livescore.data.model.score.fixture.FixtureResponse
import com.sun.livescore.data.model.score.history.HistoryResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET(API_GET_FIXTURES)
    fun getScoresFixtures(
        @Query(DATE) date: String
    ): Single<FixtureResponse>

    @GET(API_GET_HISTORY)
    fun getScoresHistory(
        @Query(FROM) from: String,
        @Query(TO) to: String
    ): Single<HistoryResponse>

    @GET(API_GET_LEAGUES)
    fun getLeagues(): Single<LeagueResponse>

    companion object {

        const val API_GET_FIXTURES = "api-client/fixtures/matches.json"
        const val API_GET_HISTORY = "api-client/scores/history.json"
        const val API_GET_LEAGUES = "api-client/countries/list.json"
        const val KEY = "key"
        const val SECRET = "secret"
        const val DATE = "date"
        const val FROM = "from"
        const val TO = "to"
    }
}
