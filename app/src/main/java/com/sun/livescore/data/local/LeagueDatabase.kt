package com.sun.livescore.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.sun.livescore.data.local.LeagueDatabase.Companion.VERSION_DATABASE
import com.sun.livescore.data.local.dao.LeagueDao
import com.sun.livescore.data.model.league.League

@Database(entities = [League::class], version = VERSION_DATABASE)
abstract class LeagueDatabase : RoomDatabase() {

    abstract fun leagueDao(): LeagueDao

    companion object {
        private const val LEAGUE_DB = "league.db"
        const val VERSION_DATABASE = 2
        @Volatile
        private var instance: LeagueDatabase? = null

        fun getInstance(context: Context) = instance ?: synchronized(this) {
            instance ?: createDatabase(context).also { instance = it }
        }

        private fun createDatabase(context: Context) =
            Room.databaseBuilder(
                context.applicationContext,
                LeagueDatabase::class.java, LEAGUE_DB
            ).build()
    }
}
