package com.sun.livescore.data.repository

import com.sun.livescore.data.TeamDataSource
import com.sun.livescore.data.local.source.FavoriteLocalDataSource

class FavoriteLocalRepository(private val favoriteLocalDataSource: FavoriteLocalDataSource) : TeamDataSource.Local {
    override fun removeFavoriteTeam(teamId: String) = favoriteLocalDataSource.removeFavoriteTeam(teamId)
    override fun queryTeams(query: String?) = favoriteLocalDataSource.queryTeams(query)
    override fun getTeams() = favoriteLocalDataSource.getTeams()
    override fun saveFavoriteTeam(teamId: String) = favoriteLocalDataSource.saveFavoriteTeam(teamId)
}
