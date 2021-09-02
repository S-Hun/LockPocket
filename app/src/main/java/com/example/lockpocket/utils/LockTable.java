package com.example.lockpocket.utils;

public class LockTable {
    private class Widget {
        int id = -1;
        int attr = 0x0000;
        int x = -1, y = -1, w = -1, h = -1;

        private void setPoint(int _x, int _y){ x = _x; y = _y; }
        private void setSize(int _w, int _h){ w = _w; h = _h; }

        private Widget(int _id, int _attr, int _x, int _y, int _w, int _h){
            this.id = _id;
            this.attr = _attr;
            this.x = _x;
            this.y = _y;
            this.w = _w;
            this.h = _h;
        }


    }

    Widget[] p = new Widget[30];

}
