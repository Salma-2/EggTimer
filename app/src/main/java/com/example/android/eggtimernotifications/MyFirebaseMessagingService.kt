package com.example.android.eggtimernotifications

import android.app.NotificationManager
import android.util.Log
import androidx.core.content.ContextCompat
import com.example.android.eggtimernotifications.util.sendNotification
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage

class MyFirebaseMessagingService : FirebaseMessagingService() {

    /**
     * Called when message is received.
     *
     * @param remoteMessage Object representing the message received from Firebase Cloud Messaging.
     */

    override fun onMessageReceived(remoteMessage: RemoteMessage?) {
        Log.d(TAG, "From ${remoteMessage?.from}")

        remoteMessage?.data?.let {
            Log.d(TAG, "Message data payload: $it")
        }

        // to see a notification regardless of if the app is in foreground or background.
        remoteMessage?.notification?.let {
            Log.d(TAG, " Message Notification Body ${it.body}")
            sendNotification(it.body!!)
        }
    }

    override fun onNewToken(token: String?) {
        Log.d(TAG, "New Token $token")

        sendRegistrationToServer(token)
    }

    private fun sendNotification(messageBody: String) {
        val notificationManager =
            ContextCompat.getSystemService(applicationContext,
                NotificationManager::class.java) as NotificationManager
        notificationManager.sendNotification(messageBody, applicationContext)
    }

    private fun sendRegistrationToServer(token: String?) {
        // send token to the server
    }

    companion object {
        private const val TAG = "MyFirebaseMsgService"
    }
}