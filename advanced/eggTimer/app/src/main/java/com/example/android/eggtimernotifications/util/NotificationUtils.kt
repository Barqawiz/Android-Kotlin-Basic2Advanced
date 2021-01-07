/*
 * Copyright (C) 2019 Google Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.android.eggtimernotifications.util

import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import androidx.core.app.NotificationCompat
import com.example.android.eggtimernotifications.MainActivity
import com.example.android.eggtimernotifications.R
import com.example.android.eggtimernotifications.receiver.SnoozeReceiver

// Notification ID.
private val NOTIFICATION_ID = 0
private val REQUEST_CODE = 0
private val FLAGS = 0 // means one shot intent

// Step 1.1 send notification extension for the notification manager
/**
 * Builds and delivers the notification.
 *
 * @param context, activity context.
 *
 * The minimum to send notification --- > 1.2, 1.3, 1.4
 * Add activity intent to the notification --- > 1.11, 1.12, 1.13
 *
 */
fun NotificationManager.sendNotification(messageBody: String, applicationContext: Context) {
    // Create the content intent for the notification, which launches
    // this activity
    // Step 1.11 create intent
    val contentIntent = Intent(applicationContext, MainActivity::class.java)

    // Step 1.12 create PendingIntent
    val contentPendingIntent = PendingIntent.getActivity(
        applicationContext,
        NOTIFICATION_ID,
        contentIntent,
        PendingIntent.FLAG_UPDATE_CURRENT
    )

    // Step 2.0 add style
    val eggImage = BitmapFactory.decodeResource(
        applicationContext.resources, R.drawable.cooked_egg
    )
    val pigPicStyle = NotificationCompat.BigPictureStyle()
        .bigPicture(eggImage).bigLargeIcon(null)

    // Step 2.2 add snooze action
    val snoozIntent = Intent(applicationContext, SnoozeReceiver::class.java)
    val snoozPendingIntent: PendingIntent = PendingIntent.getBroadcast(
        applicationContext, REQUEST_CODE, snoozIntent, FLAGS
    )

    // Step 1.2 - Build the notification
    val builder = NotificationCompat.Builder(
        applicationContext, applicationContext.getString(R.string.egg_notification_channel_id))

    // TODO: Step 1.8 use the new 'breakfast' notification channel

    // Step 1.3 set title, text and icon to builder
        .setSmallIcon(R.drawable.cooked_egg)
        .setContentTitle(applicationContext.getString(R.string.notification_title))
        .setContentText(messageBody)

    // Step 1.13 set content intent
        .setContentIntent(contentPendingIntent)
        .setAutoCancel(true)

        // Step 2.1 add style to builder
        .setStyle(pigPicStyle)
        .setLargeIcon(eggImage)

        // Step 2.3 add snooze action
        .addAction(
            R.drawable.egg_icon, applicationContext.getString(R.string.snooze), snoozPendingIntent
        )

        // Step 2.5 set priority
        .setPriority(NotificationCompat.PRIORITY_HIGH)

    // Step 1.4 call notify
    notify(NOTIFICATION_ID, builder.build())

}

// TODO: Step 1.14 Cancel all notifications
fun NotificationManager.cancelNotifications() {
    cancelAll()
}