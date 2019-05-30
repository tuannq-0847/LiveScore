package com.sun.livescore.service

import android.app.AlarmManager
import android.app.PendingIntent
import android.app.Service
import android.content.Context
import android.content.Intent
import android.os.IBinder
import android.os.SystemClock
import com.sun.livescore.ui.favorite.AlarmReceiver
import java.util.ArrayList
import java.util.Calendar

class ScheduleService : Service() {
    override fun onBind(intent: Intent?): IBinder? {
        return null
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        val data = intent?.getIntegerArrayListExtra(EXTRA_SCHEDULE)
        data?.let {
            val calendar = Calendar.getInstance()
            val notificationIntent = Intent(this, AlarmReceiver::class.java)
            val pendingIntent =
                PendingIntent.getBroadcast(this, REQUEST_CODE, notificationIntent, PendingIntent.FLAG_CANCEL_CURRENT)
            calendar.set(
                data[YEAR],
                data[MONTH],
                data[DAY],
                data[HOUR],
                data[MIN]
            )
            val futureInMillis = SystemClock.elapsedRealtime() + calendar.timeInMillis - System.currentTimeMillis()
            val alarmManager = this.getSystemService(Context.ALARM_SERVICE) as AlarmManager
            alarmManager.set(AlarmManager.ELAPSED_REALTIME_WAKEUP, futureInMillis, pendingIntent)
        }
        return START_STICKY
    }

    companion object {

        @JvmStatic
        fun getServiceIntent(context: Context?, data: List<Int>?): Intent {
            val intent = Intent(context, ScheduleService::class.java)
            intent.putIntegerArrayListExtra(EXTRA_SCHEDULE, data as ArrayList<Int>)
            return intent
        }

        const val EXTRA_SCHEDULE = "extra_schedule"
        const val YEAR = 0
        const val REQUEST_CODE = 100
        const val MONTH = 1
        const val DAY = 2
        const val HOUR = 3
        const val MIN = 4
    }
}
