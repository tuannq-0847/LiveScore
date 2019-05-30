package com.sun.livescore.data.local.source

import com.sun.livescore.data.ScheduleDataSource
import com.sun.livescore.data.local.dao.ScheduleDao
import com.sun.livescore.data.model.Schedule

class ScheduleLocalDataSource(private val scheduleDao: ScheduleDao) : ScheduleDataSource.Local {
    override fun saveSchedule(schedule: Schedule) = scheduleDao.saveSchedule(schedule)

    override fun removeSchedule(teamId: String) = scheduleDao.remove(teamId)
}
