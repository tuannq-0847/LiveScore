package com.sun.livescore.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.sun.livescore.data.model.Schedule.Companion.TABLE_NAME

@Entity(tableName = TABLE_NAME )
data class Schedule(
    @PrimaryKey
    var idTeam: String,
    var date: String,
    var time: String
) {

    companion object {
        const val TABLE_NAME = "schedule"
    }
}
