package com.example.notification

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.graphics.BitmapFactory
import android.os.Build
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.example.notification.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    val CHANNEL_ID = "MyChannelID"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.button.setOnClickListener {
            // Make a channel if necessary
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
                // Create the NotificationChannel, but only on API 26+ because
                // the NotificationChannel class is new and not in the support library
                val name = "MyChannelName"
                val description_text = "NotificationChannel description"
                val importance = NotificationManager.IMPORTANCE_HIGH
                val notificationChannel = NotificationChannel(CHANNEL_ID, name, importance).apply{
                    description=description_text
                }

                // Register the channel with the system
                val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
                notificationManager.createNotificationChannel(notificationChannel)
            }

            // Create the notification
            val builder = NotificationCompat.Builder(this, CHANNEL_ID)
                .setSmallIcon(R.drawable.ic_launcher_foreground)
                .setLargeIcon(BitmapFactory.decodeResource(this.resources, R.drawable.ic_launcher_foreground))
                .setContentTitle("MyNotification title")
                .setContentText("MyNotification text")
                .setPriority(NotificationCompat.PRIORITY_HIGH)


            // Send the notification
            // notificationId is a unique int for each notification that you must define
            val notificationId = 1
            NotificationManagerCompat.from(this).notify(notificationId, builder.build())
        }
    }
}