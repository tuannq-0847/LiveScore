package com.sun.livescore.data.remote.league

import com.sun.livescore.data.model.league.League
import com.sun.livescore.data.model.league.LeagueResponse
import com.sun.livescore.data.model.team.Team
import io.reactivex.Single

interface LeagueDataSource {
    interface Remote {
        fun getLeagues(country: String): Single<LeagueResponse>
        fun getAllLeagues(): Single<LeagueResponse>
    }

    interface Local {
        fun getLocalLeagues(): Single<List<League>>
    }
}
