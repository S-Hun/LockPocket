package com.example.lockpocket.utils;

import android.graphics.Color;

import com.example.lockpocket.R;

public class WidgetList {
    public static class Type {
        final public int w, h, attr, icon, color;
        Type(int _w, int _h, int _attr, int _icon, int _color) { w = _w; h = _h; attr = _attr; icon = _icon; color = _color; }
        Type(int _w, int _h, int _attr, int _icon, String _color) { w = _w; h = _h; attr = _attr; icon = _icon; color = Color.parseColor(_color); }
    }
    // TYPE MAX 127
    public static final byte WG_WIFI = 1;
    public static final byte WG_DATA = 2;
    public static final byte WG_PLANE = 3;
    public static final byte WG_FLASH = 4;;
    public static final byte WG_NOTIFICATION = 5;
    public static final byte WG_CLOCK = 6;
    public static final byte WG_CALL = 7;
    public static final byte WG_CAMERA = 8;

    public static final Type WIFI = new Type(1, 1, 0, R.drawable.ic_wifi, "#3A7CA5");
    public static final Type DATA = new Type(1, 1, 0, R.drawable.ic_sync_alt, "#C6AC8F");
    public static final Type PLANE = new Type(1, 1, 0, R.drawable.ic_flight, "#3F84E5");
    public static final Type FLASH = new Type(1, 1, 0, R.drawable.ic_flashlight, "#0F1A20");
    public static final Type NOTIFICATION = new Type(4, 2, 0, R.drawable.ic_notifications, "#00000000");
    public static final Type CLOCK = new Type(2, 2, 0, R.drawable.ic_watch, "#00000000");
    public static final Type CALL = new Type(1, 1, 0, R.drawable.ic_call, "#33CC33");
    public static final Type CAMERA = new Type(1, 1, 0, R.drawable.ic_photo_camera, "#F42C04");

    public static String getName(int type){
        if(type == 1) return "[1X1]WIFI";
        else if(type == 2) return "[1X1]데이터";
        else if(type == 3) return "[1X1]비행기";
        else if(type == 4) return "[1X1]손전등";
        else if(type == 5) return "[4X2]상단 알림";
        else if(type == 6) return "[2X2]시계";
        else if(type == 7) return "[1X1]전화";
        else if(type == 8) return "[1X1]카메라";
        else return "";
    }

    public static Type getId(int type){
        if(type == 1) return WIFI;
        else if(type == 2) return DATA;
        else if(type == 3) return PLANE;
        else if(type == 4) return FLASH;
        else if(type == 5) return NOTIFICATION;
        else if(type == 6) return CLOCK;
        else if(type == 7) return CALL;
        else if(type == 8) return CAMERA;
        else return new Type(0, 0, 0, R.drawable.ic_launcher_foreground, "#00000000");
    }
}
