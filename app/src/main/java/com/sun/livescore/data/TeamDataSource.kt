package com.sun.livescore.data

import com.sun.livescore.data.model.team.Team
import io.reactivex.Single

class TeamDataSource {
    interface Local {
        fun saveFavoriteTeam(team: Team)
        fun getTeams(): Single<List<Team>>
        fun queryTeams(query: String?): Single<List<Team>>
    }
}
