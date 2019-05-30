package com.sun.livescore.data.local.dao

import androidx.room.Dao
import androidx.room.Query
import com.sun.livescore.data.model.league.League
import io.reactivex.Single

@Dao
abstract class LeagueDao {

    @Query("select * from $LEAGUE")
    abstract fun getLocalLeagues(): Single<List<League>>

    companion object {
        const val LEAGUE = "league"
    }
}
