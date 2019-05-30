package com.sun.livescore.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.sun.livescore.data.model.Schedule

@Dao
abstract class ScheduleDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun saveSchedule(schedule: Schedule)

    @Query("delete from $SCHEDULE where idTeam = :teamId")
    abstract fun remove(teamId: String)

    companion object {
        const val SCHEDULE = "schedule"
    }
}
