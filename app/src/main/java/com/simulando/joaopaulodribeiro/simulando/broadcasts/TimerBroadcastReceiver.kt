package com.simulando.joaopaulodribeiro.simulando.broadcasts

import android.app.Notification
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import android.media.Ringtone
import android.media.RingtoneManager
import android.net.Uri
import android.support.v4.app.NotificationCompat
import android.util.Log

import com.simulando.joaopaulodribeiro.simulando.R
import com.simulando.joaopaulodribeiro.simulando.page.AnswerTestActivity

/**
 * Created by joao.paulo.d.ribeiro on 03/10/2017.
 */

class TimerBroadcastReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) {
        Log.i("Script", "-> Alarme")

        generateNotification(
                context,
                Intent(context, AnswerTestActivity::class.java),
                "Nova Mensagem",
                "Rápido, o tempo está acabando",
                "Faltam apenas 10 minutos para o fim do seu simulado"
        )
    }

    fun generateNotification(context: Context, intent: Intent, ticker: CharSequence,
                             title: CharSequence, description: CharSequence) {

        val nm = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        val p = PendingIntent.getActivity(context, 0, intent, 0)

        val builder = NotificationCompat.Builder(context)
        builder.setTicker(ticker)
        builder.setContentTitle(title)
        builder.setContentText(description)
        builder.setSmallIcon(R.drawable.welcome_logo)
        builder.setLargeIcon(BitmapFactory.decodeResource(context.resources, R.drawable.welcome_logo))
        builder.setContentIntent(p)

        val n = builder.build()
        n.vibrate = longArrayOf(150, 300, 150, 600)
        n.flags = Notification.FLAG_AUTO_CANCEL
        nm.notify(R.drawable.welcome_logo, n)

        try {
            val sound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)
            val soundPlay = RingtoneManager.getRingtone(context, sound)
            soundPlay.play()
        } catch (e: Exception) {
        }

    }
}
