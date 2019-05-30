package com.sun.livescore.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.sun.livescore.data.local.ScheduleDatabase.Companion.VERSION_DATABASE
import com.sun.livescore.data.local.dao.ScheduleDao
import com.sun.livescore.data.model.Schedule

@Database(entities = [Schedule::class], version = VERSION_DATABASE)
abstract class ScheduleDatabase : RoomDatabase() {

    abstract fun scheduleDao(): ScheduleDao

    companion object {
        private const val DATE_TIME_DB = "schedule.db"
        const val VERSION_DATABASE = 2
        @Volatile
        private var instance: ScheduleDatabase? = null

        fun getInstance(context: Context) = instance ?: synchronized(this) {
            instance ?: createDatabase(context).also { instance = it }
        }

        private fun createDatabase(context: Context) =
            Room.databaseBuilder(
                context.applicationContext,
                ScheduleDatabase::class.java, DATE_TIME_DB
            ).build()
    }
}
