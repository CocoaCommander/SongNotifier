package edu.uw.ryanl32.httpjsonparser

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import edu.uw.ryanl32.httpjsonparser.DataClasses.Card
import kotlin.random.Random

private const val NEW_CARDS_CHANNEL_ID = "NEW_CARDS_CHANNEL_ID"

class CardNotificationManager(
    private val context: Context
) {
    private val notificationManager = NotificationManagerCompat.from(context)

    init {
        initNotificationChannels()
    }

    private fun initNotificationChannels() {
        initNewCardChannel()
    }

    private fun initNewCardChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            // Info about the channel
            val name = context.getString(R.string.new_cards)
            val descriptionText = context.getString(R.string.new_cards_channel_description)
            val importance = NotificationManager.IMPORTANCE_DEFAULT

            // Create channel object
            val channel = NotificationChannel(NEW_CARDS_CHANNEL_ID, name, importance).apply {
                description = descriptionText
            }

            // Tell the Android OS to create a channel
            notificationManager.createNotificationChannel(channel)
        }
    }

    fun publishNewEmailNotification(card: Card) {
        // Define the intent or action you want when user taps on notification

        val randomCard = card.data[Random.nextInt(0, card.data.size)]

        val intent = Intent(context, MainActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            putExtra(CARD_DATA_KEY, randomCard)
        }
        val pendingIntent: PendingIntent = PendingIntent.getActivity(context, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT) // dont forget to add PendingIntent.FLAG_UPDATE_CURRENT to send data over


        // Build information you want the notification to show
        val builder = NotificationCompat.Builder(context, NEW_CARDS_CHANNEL_ID)    // channel id from creating the channel
            .setSmallIcon(R.drawable.ic_baseline_note_24)
            .setContentTitle("Pokémon TCG App")
            .setContentText("New card available: ${randomCard.name}")
            .setContentIntent(pendingIntent)    // sets the action when user clicks on notification
            .setAutoCancel(true)    // This will dismiss the notification tap
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)

        // Tell the OS to publish the notification using the info
        with(notificationManager) {
            // notificationId is a unique int for each notification that you must define
            val notificationId = Random.nextInt()
            notify(notificationId, builder.build())
        }
    }
}