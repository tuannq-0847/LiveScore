package com.sun.livescore.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.RoomDatabase
import com.huma.room_for_asset.RoomAsset
import com.sun.livescore.data.local.dao.TeamDao
import com.sun.livescore.data.model.team.Team

@Database(entities = [Team::class], version = 2)
abstract class TeamDatabase : RoomDatabase() {

    abstract fun teamDao(): TeamDao

    companion object {
        @Volatile
        private var instance: TeamDatabase? = null

        fun getInstance(context: Context) = instance ?: synchronized(this) {
            instance ?: createDatabase(context).also { instance = it }
        }

        private fun createDatabase(context: Context) =
            RoomAsset.databaseBuilder(
                context.applicationContext,
                TeamDatabase::class.java, "clubfav.db"
            ).build()
    }
}
