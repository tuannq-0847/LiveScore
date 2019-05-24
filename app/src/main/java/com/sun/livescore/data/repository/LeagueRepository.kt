package com.sun.livescore.data.repository

import com.sun.livescore.data.model.league.LeagueResponse
import com.sun.livescore.data.remote.league.LeagueDataSource
import com.sun.livescore.data.remote.league.LeagueRemoteDataSource
import io.reactivex.Single

class LeagueRepository(private val leagueRemoteDataSource: LeagueRemoteDataSource) : LeagueDataSource.Remote {
    override fun getLeagues(country: String): Single<LeagueResponse> = leagueRemoteDataSource.getLeagues(country)
    override fun getAllLeagues(): Single<LeagueResponse> = leagueRemoteDataSource.getAllLeagues()
}
