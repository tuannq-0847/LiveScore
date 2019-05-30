package com.sun.livescore.data.repository

import com.sun.livescore.data.local.source.LeagueLocalDataSource
import com.sun.livescore.data.model.league.League
import com.sun.livescore.data.remote.league.LeagueDataSource
import io.reactivex.Single

class LeagueLocalRepository(private val leagueLocalDataSource: LeagueLocalDataSource) : LeagueDataSource.Local {
    override fun getLocalLeagues(): Single<List<League>> = leagueLocalDataSource.getLocalLeagues()
}
