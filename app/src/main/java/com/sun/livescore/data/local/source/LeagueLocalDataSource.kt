package com.sun.livescore.data.local.source

import com.sun.livescore.data.local.dao.LeagueDao
import com.sun.livescore.data.model.league.League
import com.sun.livescore.data.remote.league.LeagueDataSource
import io.reactivex.Single

class LeagueLocalDataSource(private val leagueDao: LeagueDao) : LeagueDataSource.Local {
    override fun getLocalLeagues(): Single<List<League>> = leagueDao.getLocalLeagues()
}
