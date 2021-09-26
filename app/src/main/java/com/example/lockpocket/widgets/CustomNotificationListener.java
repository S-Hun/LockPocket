package com.example.lockpocket.widgets;

import android.content.Context;
import android.service.notification.NotificationListenerService;
import android.service.notification.StatusBarNotification;
import android.util.Log;

public interface CustomNotificationListener {
    void setValue (String packageName) ;
}

