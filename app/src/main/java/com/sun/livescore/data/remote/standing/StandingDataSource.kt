package com.sun.livescore.data.remote.standing

import com.sun.livescore.data.model.standing.StandingResponse
import io.reactivex.Single

interface StandingDataSource {
    interface Remote {
        fun getStanding(leagueId: String, seasonId: String): Single<StandingResponse>
    }
}
