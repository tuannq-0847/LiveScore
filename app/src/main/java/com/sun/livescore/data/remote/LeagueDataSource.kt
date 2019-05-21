package com.sun.livescore.data.remote

import com.sun.livescore.data.model.league.LeagueResponse
import io.reactivex.Single

interface LeagueDataSource {
    interface Remote {
        fun getLeagues(country: String): Single<LeagueResponse>
    }
}
