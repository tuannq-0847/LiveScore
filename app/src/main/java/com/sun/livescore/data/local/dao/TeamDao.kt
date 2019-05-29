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
    abstract fun insertFavoriteTeam(team: Team)

    @Delete
    abstract fun delete(team: Team)

    @Query("select * from $TEAM")
    abstract fun getTeams(): Single<List<Team>>

    @Query("select * from $TEAM where name like '%' || :query || '%'")
    abstract fun queryTeam(query: String?): Single<List<Team>>

    companion object {
        const val TEAM = "team"
    }
}
