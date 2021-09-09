package com.example.lockpocket.utils;

import java.util.ArrayList;
import java.util.List;

public class WidgetList {
    static class Type {
        public String name;
        public int w, h, attr;
        Type(String n, int _w, int _h, int _attr) {name = n; w = _w; h = _h; attr = _attr;}
    }

    private static WidgetList Instance = new WidgetList();
    private List<Type> widgets= new ArrayList<Type>();

    private WidgetList() {
        widgets.add(new Type("wifi", 1, 1, 0));
        widgets.add(new Type("data", 1, 1, 0));
        widgets.add(new Type("plane", 1, 1, 0));
        widgets.add(new Type("flash", 1, 1, 0));
    }

    public static WidgetList getInstance() {
        return Instance;
    }

    public static final byte WG_WIFI = 0;
    public static final byte WG_DATA = 1;
    public static final byte WG_PLANE = 2;
    public static final byte WG_FLASH = 3;

    public String getWidgetName(int type){
        return widgets.get(type).name;
    }

    public Type getWidgetType(int type){
        return widgets.get(type);
    }
}
