package com.sun.livescore.data.repository

import com.sun.livescore.data.ScheduleDataSource
import com.sun.livescore.data.local.source.ScheduleLocalDataSource
import com.sun.livescore.data.model.Schedule

class ScheduleRepository(private val scheduleLocalDataSource: ScheduleLocalDataSource) : ScheduleDataSource.Local {
    override fun saveSchedule(schedule: Schedule) = scheduleLocalDataSource.saveSchedule(schedule)

    override fun removeSchedule(teamId: String) = scheduleLocalDataSource.removeSchedule(teamId)
}
