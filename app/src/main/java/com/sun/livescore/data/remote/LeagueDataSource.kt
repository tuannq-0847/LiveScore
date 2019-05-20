package com.sun.livescore.data.remote

import com.sun.livescore.data.model.leagues.LeagueResponse
import io.reactivex.Single

interface LeagueDataSource {
    interface Remote {
        fun getLeagueCountry(): Single<LeagueResponse>
    }
}
