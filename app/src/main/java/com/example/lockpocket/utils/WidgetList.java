package com.example.lockpocket.utils;

public class WidgetList {
    public static class Type {
        final public int w, h, attr;
        Type(int _w, int _h, int _attr) {w = _w; h = _h; attr = _attr;}
    }
    // TYPE MAX 127
    public static final byte WG_WIFI = 0;
    public static final byte WG_DATA = 1;
    public static final byte WG_PLANE = 2;
    public static final byte WG_FLASH = 3;
    public static final byte WG_NOTIFICATION = 5;

    public static final Type WIFI = new Type(1, 1, 0);
    public static final Type DATA = new Type(1, 1, 0);
    public static final Type PLANE = new Type(1, 1, 0);
    public static final Type FLASH = new Type(1, 1, 0);
    public static final Type NOTIFICATION = new Type(4, 2, 0);

    public static String getName(int type){
        if(type == 1) return "WIFI";
        else if(type == 2) return "DATA";
        else if(type == 3) return "PLANE";
        else if(type == 4) return "FLASH";
        else if(type == 5) return "NOTIFICATION";
        else return "";
    }

    public static Type getId(int type){
        if(type == 1) return WIFI;
        else if(type == 2) return DATA;
        else if(type == 3) return PLANE;
        else if(type == 4) return FLASH;
        else if(type == 5) return NOTIFICATION;
        else return new Type(0, 0, 0);
    }
}
