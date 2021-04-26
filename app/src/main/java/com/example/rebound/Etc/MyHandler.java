//package com.example.rebound;
//
//import android.app.Notification;
//import android.app.NotificationManager;
//import android.app.PendingIntent;
//import android.content.Context;
//import android.content.Intent;
//import android.graphics.BitmapFactory;
//import android.media.RingtoneManager;
//import android.net.Uri;
//import android.os.Bundle;
//
//import androidx.core.app.NotificationCompat;
//
//public class MyHandler extends Notification {
//    public static final int NOTIFICATION_ID = 1;
//    private NotificationManager mNotificationManager;
//    NotificationCompat.Builder builder;
//    Context ctx;
//
//    @Override
//    public void onReceive(Context context, Bundle bundle) {
//        ctx = context;
//        String nhMessage = bundle.getString("message");
//        sendNotification(nhMessage);
//        if (MainActivity.isVisible) {
//            MainActivity.mainActivity.ToastNotify(nhMessage);
//        }
//    }
//
//    private void sendNotification(String msg) {
//
//        Intent intent = new Intent(ctx, MainActivity.class);
//        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//
//        mNotificationManager = (NotificationManager)
//                ctx.getSystemService(Context.NOTIFICATION_SERVICE);
//
//        PendingIntent contentIntent = PendingIntent.getActivity(ctx, (int)(System.currentTimeMillis()/1000),
//                intent, PendingIntent.FLAG_ONE_SHOT);
//
//        NotificationCompat.BigTextStyle bigTextStyle = new NotificationCompat.BigTextStyle();
//        //bigTextStyle.setSummaryText("...");
//
//        Uri defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
//        NotificationCompat.Builder mBuilder =
//                new NotificationCompat.Builder(ctx)
//                        .setSmallIcon(R.mipmap.ic_launcher)
//                        .setLargeIcon(BitmapFactory.decodeResource(ctx.getResources(), R.mipmap.ic_launcher))
//                        //.setLargeIcon(R..ic_launcher)
//                        .setContentTitle("Notification Hub Demo")
//                        .setStyle(bigTextStyle
//                                .bigText(msg))
//                        .setSound(defaultSoundUri)
//                        .setDefaults(Notification.FLAG_NO_CLEAR)
//                        .setContentText(msg);
//
//        mBuilder.setContentIntent(contentIntent);
//        mNotificationManager.notify((int)(System.currentTimeMillis()/1000), mBuilder.build());
//    }