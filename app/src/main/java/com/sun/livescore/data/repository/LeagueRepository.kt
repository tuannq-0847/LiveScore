package com.sun.livescore.data.repository

import com.sun.livescore.data.model.league.LeagueResponse
import com.sun.livescore.data.remote.LeagueDataSource
import com.sun.livescore.data.remote.LeagueRemoteDataSource
import io.reactivex.Single

class LeagueRepository(private val leagueRemoteDataSource: LeagueRemoteDataSource) : LeagueDataSource.Remote {
    override fun getLeagues(country: String): Single<LeagueResponse> = leagueRemoteDataSource.getLeagues(country)
}
