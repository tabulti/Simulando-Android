package com.simulando.joaopaulodribeiro.simulando.broadcasts;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import com.simulando.joaopaulodribeiro.simulando.R;
import com.simulando.joaopaulodribeiro.simulando.page.AnswerTestActivity;

/**
 * Created by joao.paulo.d.ribeiro on 03/10/2017.
 */

public class TimerBroadcastReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Log.i("Script", "-> Alarme");

        generateNotification(
                context,
                new Intent(context, AnswerTestActivity.class),
                "Nova Mensagem",
                "Rápido, o tempo está acabando",
                "Faltam apenas 10 minutos para o fim do seu simulado"
        );
    }

    public void generateNotification(Context context, Intent intent, CharSequence ticker,
                                     CharSequence title, CharSequence description) {

        NotificationManager nm = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        PendingIntent p = PendingIntent.getActivity(context, 0, intent, 0);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(context);
        builder.setTicker(ticker);
        builder.setContentTitle(title);
        builder.setContentText(description);
        builder.setSmallIcon(R.drawable.welcome_logo);
        builder.setLargeIcon(BitmapFactory.decodeResource(context.getResources(), R.drawable.welcome_logo));
        builder.setContentIntent(p);

        Notification n = builder.build();
        n.vibrate = new long[] {150, 300, 150, 600};
        n.flags = Notification.FLAG_AUTO_CANCEL;
        nm.notify(R.drawable.welcome_logo, n);

        try {
            Uri sound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
            Ringtone soundPlay = RingtoneManager.getRingtone(context, sound);
            soundPlay.play();
        } catch (Exception e) {}
    }
}
