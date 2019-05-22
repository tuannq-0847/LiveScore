package com.sun.livescore.data.remote.standing

import com.sun.livescore.data.remote.request.ApiService

class StandingRemoteDataSource(private val apiService: ApiService) : StandingDataSource.Remote {
    override fun getStanding(leagueId: String, seasonId: String) = apiService.getStandings(leagueId, seasonId)
}
