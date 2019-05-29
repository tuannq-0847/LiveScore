package com.sun.livescore.data

import com.sun.livescore.data.model.team.Team
import io.reactivex.Single

class TeamDataSource {
    interface Local {
        fun saveFavTeam(team: Team)
        fun getAllTeams(): Single<List<Team>>
    }
}
