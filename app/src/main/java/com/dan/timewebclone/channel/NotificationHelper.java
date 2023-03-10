package com.dan.timewebclone.channel;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.ContextWrapper;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;

import androidx.annotation.RequiresApi;
import androidx.core.app.NotificationCompat;
import androidx.core.app.Person;
import androidx.core.graphics.drawable.IconCompat;


import com.dan.timewebclone.R;

import java.util.Date;

public class NotificationHelper extends ContextWrapper {

    private static final String CHANNEL_ID = "com.dan.timewebclone";
    private static final String CHANNEL_NAME = "TimeWebClone";

    private NotificationManager manager;

    public NotificationHelper(Context base) {
        super(base);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            createChannels();
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private void createChannels() {
        NotificationChannel notificationChannel = new NotificationChannel(
                CHANNEL_ID,
                CHANNEL_NAME,
                NotificationManager.IMPORTANCE_HIGH
        );

        notificationChannel.enableLights(true);
        notificationChannel.enableVibration(true);
        notificationChannel.setLightColor(Color.GRAY);
        notificationChannel.setLockscreenVisibility(Notification.VISIBILITY_PRIVATE);
        NotificationManager notificationManager = getSystemService(NotificationManager.class);
        notificationManager.createNotificationChannel(notificationChannel);
        //getManager().createNotificationChannel(notificationChannel);

    }

    public NotificationManager getManager() {
        if (manager == null) {
            manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        }
        return manager;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public Notification.Builder getNotification(String title, String body, PendingIntent intent, Uri soundUri) {
        return new Notification.Builder(getApplicationContext(), CHANNEL_ID)
                .setContentTitle(title)
                .setContentText(body)
                .setAutoCancel(true)
                .setColor(Color.GRAY)
                .setSound(soundUri)
                .setContentIntent(intent)
                .setSmallIcon(R.mipmap.ic_launchermobile)
                .setStyle(new Notification.BigTextStyle().bigText(body).setBigContentTitle(title));
    }

    public NotificationCompat.Builder getNotificationOldApi(String title, String body, PendingIntent intent, Uri soundUri) {
        return new NotificationCompat.Builder(getApplicationContext(), CHANNEL_ID)
                .setContentTitle(title)
                .setContentText(body)
                .setAutoCancel(true)
                .setColor(Color.GRAY)
                .setSound(soundUri)
                .setContentIntent(intent)
                .setSmallIcon(R.mipmap.ic_launchermobile)
                .setStyle(new NotificationCompat.BigTextStyle().bigText(body).setBigContentTitle(title));
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public Notification.Builder getNotificationActions(
            String title,
            String body,
            Uri soundUri,
            Notification.Action acceptAction ,
            Notification.Action cancelAction) {
        return new Notification.Builder(getApplicationContext(), CHANNEL_ID)
                .setContentTitle(title)
                .setContentText(body)
                .setAutoCancel(true)
                .setSound(soundUri)
                .setSmallIcon(R.mipmap.ic_launchermobile)
                .addAction(acceptAction)
                .addAction(cancelAction)
                .setStyle(new Notification.BigTextStyle()
                        .bigText(body).setBigContentTitle(title));
    }

    public NotificationCompat.Builder getNotificationOldAPIActions(
            String title,
            String body,
            Uri soundUri,
            NotificationCompat.Action acceptAction,
            NotificationCompat.Action cancelAction) {
        return new NotificationCompat.Builder(getApplicationContext(), CHANNEL_ID)
                .setContentTitle(title)
                .setContentText(body)
                .setAutoCancel(true)
                .setSound(soundUri)
                .setSmallIcon(R.mipmap.ic_launchermobile)
                .addAction(acceptAction)
                .addAction(cancelAction)
                .setStyle(new NotificationCompat.BigTextStyle().bigText(body).setBigContentTitle(title));
    }
}
