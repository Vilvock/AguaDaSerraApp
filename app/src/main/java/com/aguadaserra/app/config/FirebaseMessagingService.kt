package com.aguadaserra.app.config

import android.annotation.SuppressLint
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Intent
import android.media.RingtoneManager
import android.os.Build
import android.util.Log
import androidx.core.app.NotificationCompat
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import com.aguadaserra.app.MainActivity
import com.aguadaserra.app.R
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage

class FirebaseMessagingService: FirebaseMessagingService() {

    private val TAG = "notifica"
    private var intent: Intent? = null

    override fun onMessageReceived(remoteMessage: RemoteMessage) {
        super.onMessageReceived(remoteMessage)
        //log para ver os dados que estao vindo
        Log.i(TAG, "onMessageReceived: " + remoteMessage.data)

        if (remoteMessage.data.isNotEmpty()) {
            val title = remoteMessage.data["titulo"]
            val body = remoteMessage.data["descricao"]
            val type = remoteMessage.data["type"]
            val idTo = remoteMessage.data["id_para"]
            val idFrom = remoteMessage.data["id_de"]

            setMessage(title, body, type, idTo, idFrom)
        }
    }

    override fun onNewToken(s: String) {
        super.onNewToken(s)
        Log.i(TAG, "onNewToken: $s")
    }

    @SuppressLint("UnspecifiedImmutableFlag")
    private fun setMessage(title: String?, body: String?, type: String?, idTo: String?, idFrom: String?) {

        val channel = getString(R.string.default_notification_channel_id)
        val uriSound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)

        intent = Intent(this, MainActivity::class.java)

        val broadcastManager = LocalBroadcastManager.getInstance(this)
        val intentBroadcast = Intent("Notification")

        when (type) {

            else -> {

//                intentBroadcast.putExtra("REFRESH_CHAT", "666")
            }
        }

        val pendingIntent: PendingIntent = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            PendingIntent.getActivity(
                this, 0, intent, PendingIntent.FLAG_MUTABLE)
        } else {
            PendingIntent.getActivity(
                this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT)
        }

        broadcastManager.sendBroadcast(intentBroadcast)

        val notification = NotificationCompat.Builder(this, channel)
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setContentTitle(title)
            .setContentText(body)
            .setSmallIcon(R.mipmap.ic_launcher_round)
            .setSound(uriSound)
            .setAutoCancel(true)
            .setContentIntent(pendingIntent)
        val notificationManager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager


        val nChannel =
            NotificationChannel(channel, "channel", NotificationManager.IMPORTANCE_HIGH)
        notificationManager.createNotificationChannel(nChannel)
        notificationManager.notify(0, notification.build())

    }
}