package com.sun.livescore.data

import com.sun.livescore.data.model.Schedule

interface ScheduleDataSource {
    interface Local {
        fun saveSchedule(schedule: Schedule)
        fun removeSchedule(teamId: String)
    }
}
