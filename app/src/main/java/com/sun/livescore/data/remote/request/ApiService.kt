package com.sun.livescore.data.remote.request

import com.sun.livescore.data.model.country.CountryResponse
import com.sun.livescore.data.model.event.EventResponse
import com.sun.livescore.data.model.league.LeagueResponse
import com.sun.livescore.data.model.score.fixture.FixtureResponse
import com.sun.livescore.data.model.score.history.HistoryResponse
import com.sun.livescore.data.model.standing.StandingResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET(API_GET_FIXTURES)
    fun getScoresFixtures(@Query(DATE) date: String): Single<FixtureResponse>

    @GET(API_GET_HISTORY)
    fun getScoresHistory(@Query(FROM) from: String, @Query(TO) to: String): Single<HistoryResponse>

    @GET(API_GET_COUNTRIES)
    fun getCountries(): Single<CountryResponse>

    @GET(API_GET_LEAGUES)
    fun getLeagues(@Query(COUNTRY) country: String): Single<LeagueResponse>

    @GET(API_GET_STANDINDS)
    fun getStandings(@Query(LEAGUE) leagueId: String, @Query(SEASON) seasonId: String): Single<StandingResponse>

    @GET(API_GET_LIVE)
    fun getLiveScores(): Single<HistoryResponse>

    @GET(API_GET_EVENTS)
    fun getEvents(@Query(ID_MATCH) id: String): Single<EventResponse>

    companion object {

        const val API_GET_FIXTURES = "api-client/fixtures/matches.json"
        const val API_GET_HISTORY = "api-client/scores/history.json"
        const val API_GET_COUNTRIES = "api-client/countries/list.json"
        const val API_GET_LEAGUES = "api-client/leagues/list.json"
        const val API_GET_STANDINDS = "api-client/leagues/table.json"
        const val API_GET_LIVE = "api-client/scores/live.json"
        const val API_GET_EVENTS = "api-client/scores/events.json"
        const val COUNTRY = "country"
        const val LEAGUE = "league"
        const val SEASON = "season"
        const val KEY = "key"
        const val SECRET = "secret"
        const val DATE = "date"
        const val FROM = "from"
        const val TO = "to"
        const val ID_MATCH = "id"
    }
}
