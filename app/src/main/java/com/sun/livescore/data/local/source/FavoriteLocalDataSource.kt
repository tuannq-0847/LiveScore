package com.sun.livescore.data.local.source

import com.sun.livescore.data.TeamDataSource
import com.sun.livescore.data.local.dao.TeamDao
import com.sun.livescore.data.model.team.Team
import io.reactivex.Single

class FavoriteLocalDataSource(private val teamDao: TeamDao) : TeamDataSource.Local {
    override fun queryTeams(query: String?): Single<List<Team>> = teamDao.queryTeam(query)

    override fun getTeams(): Single<List<Team>> = teamDao.getTeams()

    override fun saveFavoriteTeam(teamId: String, key: String) = teamDao.insertFavoriteTeam(teamId, key)
}
