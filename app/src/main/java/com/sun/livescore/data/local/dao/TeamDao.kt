package com.sun.livescore.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import com.sun.livescore.data.model.team.Team
import io.reactivex.Single

@Dao
abstract class TeamDao {

    @Query("update $TEAM set `key` = $FOLLOW where teamId = :teamId")
    abstract fun insertFavoriteTeam(teamId: String)

    @Delete
    abstract fun delete(team: Team)

    @Query("select * from $TEAM")
    abstract fun getTeams(): Single<List<Team>>

    @Query("select * from $TEAM where name like '%' || :query || '%'")
    abstract fun queryTeam(query: String?): Single<List<Team>>

    @Query("update $TEAM set `key` = null where teamId = :teamId")
    abstract fun removeFavoriteTeam(teamId: String)

    companion object {
        const val TEAM = "team"
        const val FOLLOW = 1
    }
}
