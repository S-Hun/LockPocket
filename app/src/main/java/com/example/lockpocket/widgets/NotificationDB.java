package com.example.lockpocket.widgets;

import android.app.Notification;
import android.util.Log;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class NotificationDB {

    private class noti {
        int id; // notification id
        String appName; // notification app name
        String appDesc; // notification app desc

        public noti(int id, String appName, String appDesc) {
            this.id = id;
            this.appName = appName;
            this.appDesc = appDesc;
        }

        public int getId() {
            return id;
        }

        public String getAppDesc() {
            return appDesc;
        }

        public String getAppName() {
            return appName;
        }
    }

    public List<noti> data;

    private NotificationDB() {
        data = new LinkedList<>();
    }

    private static class InstanceClass {
        private static final NotificationDB unique = new NotificationDB();
    }

    public static NotificationDB getInstance() {
        return InstanceClass.unique;
    }

    public int find(int id) {
        int i = 0;
        for(noti d : getInstance().data) {
            if(d.id == id) return id;
            i++;
        }
        return -1;
    }

    public int add(int id, String appName, String appTest) {
        if(getInstance().find(id) == -1) return 0;
        getInstance().data.add(new noti(id, appName, appTest));
        return 1;
    }

    public int remove(int id) {
        int idx = getInstance().find(id);
        if(idx == -1) {
            Log.e("ERROR|", "system can't find id");
            return 0;
        }
        getInstance().data.remove(idx);
        return 1;
    }
}
