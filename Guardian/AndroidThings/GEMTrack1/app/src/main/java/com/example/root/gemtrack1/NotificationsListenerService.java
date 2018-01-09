package com.example.root.gemtrack1;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import com.example.root.gemtrack.R;
import com.google.android.gms.gcm.GcmListenerService;

import static android.R.attr.smallIcon;
import static android.content.ContentValues.TAG;

/**
 * Created by root on 8/6/17.
 */
public class NotificationsListenerService extends GcmListenerService {
    @Override
    public void onMessageReceived(String from, Bundle data) {
        String userID = data.getString("UserID");
        String time = data.getString("Time");
        Log.d(TAG, "From: " + from);
        Log.d(TAG, "UserID: " + userID);
        Log.d(TAG, "Time: " + time);

        if (from.startsWith("/topics/my_little_topic")) {
            Log.d(TAG,"Push from the little topic");
//              startActivity(new Intent(android.provider.Settings.ACTION_LOCATION_SOURCE_SETTINGS));
              Intent myintent2 = new Intent(this,Buttonchoice.class);
              myintent2.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
              startActivity(myintent2);
//            NotificationCompat.Builder mBuilder =
//                    new NotificationCompat.Builder(this).setLargeIcon(BitmapFactory.decodeResource(
//                            getResources(), R.mipmap.ic_launcher))
//                            .setSmallIcon(R.drawable.common_google_signin_btn_icon_dark_focused)  //mandatory
//                            .setContentTitle("GEMTrack")
//                            .setContentText("Help Needed");
//
//            Intent resultIntent = new Intent(this, ChooseToHelp.class);
//            // Because clicking the notification opens a new ("special") activity, there's
//            // no need to create an artificial back stack.
//            PendingIntent resultPendingIntent =
//                    PendingIntent.getActivity(
//                            this,
//                            0,
//                            resultIntent,
//                            PendingIntent.FLAG_UPDATE_CURRENT
//                    );
//
//            mBuilder.setContentIntent(resultPendingIntent);
//          // Sets an ID for the notification
//            int mNotificationId = 001;
//            // Gets an instance of the NotificationManager service
//            NotificationManager mNotifyMgr =
//                    (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
//            // Builds the notification and issues it.
//            mNotifyMgr.notify(mNotificationId, mBuilder.build());

        } else {
            Log.d(TAG,"Push from IRRELEVANT");
        }
    }

}
