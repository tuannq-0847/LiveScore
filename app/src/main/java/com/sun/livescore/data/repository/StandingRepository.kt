package com.sun.livescore.data.repository

import com.sun.livescore.data.model.standing.StandingResponse
import com.sun.livescore.data.remote.standing.StandingDataSource
import com.sun.livescore.data.remote.standing.StandingRemoteDataSource
import io.reactivex.Single

class StandingRepository(private val standingRemoteDataSource: StandingRemoteDataSource) : StandingDataSource.Remote {
    override fun getStanding(leagueId: String, seasonId: String): Single<StandingResponse> =
        standingRemoteDataSource.getStanding(leagueId, seasonId)
}
