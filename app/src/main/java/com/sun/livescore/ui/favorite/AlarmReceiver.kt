package com.sun.livescore.ui.favorite

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.sun.livescore.R

class AlarmReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        context?.let {
            val builder = NotificationCompat.Builder(context, CHANNEL_ID)
                .setSmallIcon(R.drawable.ic_launcher_background)
                .setContentText(context.getString(R.string.content_new_match))
                .setContentTitle(context.getString(R.string.new_match))
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                val name = context.getString(R.string.name_notification)
                val descriptionText = context.getString(R.string.description)
                val importance = NotificationManager.IMPORTANCE_DEFAULT
                val channel = NotificationChannel(CHANNEL_ID, name, importance).apply {
                    description = descriptionText
                }
                val notificationManager: NotificationManager =
                    context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
                notificationManager.createNotificationChannel(channel)
                with(NotificationManagerCompat.from(context)) {
                    notify(notification_id, builder.build())
                }
            }
        }
    }

    companion object {
        const val CHANNEL_ID = "8080"
        const val notification_id = 123
    }
}
