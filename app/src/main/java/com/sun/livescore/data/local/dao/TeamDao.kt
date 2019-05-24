package com.sun.livescore.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.sun.livescore.data.model.team.Team
import io.reactivex.Single

@Dao
abstract class TeamDao {

    @Insert
    abstract fun insertTeamFav(team: Team)

    @Delete
    abstract fun delete(team: Team)

    @Query("select * from team")
    abstract fun getAllTeams(): Single<List<Team>>
}
