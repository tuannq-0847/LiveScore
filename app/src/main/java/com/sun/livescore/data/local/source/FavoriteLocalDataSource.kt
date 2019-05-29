package com.sun.livescore.data.local.source

import com.sun.livescore.data.TeamDataSource
import com.sun.livescore.data.local.dao.TeamDao
import com.sun.livescore.data.model.team.Team
import io.reactivex.Single

class FavoriteLocalDataSource(private val teamDao: TeamDao) : TeamDataSource.Local {
    override fun getAllTeams(): Single<List<Team>> = teamDao.getAllTeams()

    override fun saveFavTeam(team: Team) = teamDao.insertTeamFav(team)
}
