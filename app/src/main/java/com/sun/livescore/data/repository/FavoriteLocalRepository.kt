package com.sun.livescore.data.repository

import com.sun.livescore.data.TeamDataSource
import com.sun.livescore.data.local.source.FavoriteLocalDataSource
import com.sun.livescore.data.model.team.Team

class FavoriteLocalRepository(private val favoriteLocalDataSource: FavoriteLocalDataSource) : TeamDataSource.Local {
    override fun queryTeams(query: String?) = favoriteLocalDataSource.queryTeams(query)
    override fun getTeams() = favoriteLocalDataSource.getTeams()
    override fun saveFavoriteTeam(team: Team) = favoriteLocalDataSource.saveFavoriteTeam(team)
}
