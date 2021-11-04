package com.example.lockpocket.widgets;

import android.content.Context;
import android.service.notification.NotificationListenerService;
import android.service.notification.StatusBarNotification;

/*
REF LINK
https://www.tutorialspoint.com/how-to-read-all-the-coming-notifications-in-android
http://findnerd.com/list/view/How-to-read-notification-title-text-and-package-name-in-android/23198/
 */

public class NotificationService extends NotificationListenerService {
    private String TAG = this.getClass().getSimpleName();
    Context context;
    static CustomNotificationListener customNotificationListener;

    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();
    }

    @Override
    public void onNotificationPosted(StatusBarNotification sbn) {

        String title = sbn.getNotification().extras.getString("android.title");
        String text = sbn.getNotification().extras.getString("android.text");
        String package_name = sbn.getPackageName();

        if(!title.equals("null") && !text.equals("null")) {
            customNotificationListener.setValue(sbn.getPackageName() + " |" + title + ": " + text);
        }
    }

    @Override
    public void onNotificationRemoved(StatusBarNotification sbn) {
//        Log.i(TAG, "********** onNotificationRemoved");
//        Log.i(TAG, "ID :" + sbn.getId() + " \t " + sbn.getNotification().tickerText + " \t " + sbn.getPackageName());
//        myListener.setValue("Remove: " + sbn.getPackageName() + " " + sbn.getKey() + " " + sbn.getTag());

        String title = sbn.getNotification().extras.getString("android.title");
        String text = sbn.getNotification().extras.getString("android.text");
        String package_name = sbn.getPackageName();

        // customNotificationListener.setValue("Post: " + title + "\n" + text);
    }

    public void setListener(CustomNotificationListener customNotificationListener) {
        NotificationService.customNotificationListener = customNotificationListener;
    }
}
