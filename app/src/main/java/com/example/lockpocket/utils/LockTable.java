package com.example.lockpocket.utils;

import android.util.Log;

import java.util.Iterator;
import java.util.LinkedList;

public interface LockTable {
    class Widget {
        static class Pos {
            byte value = 0x00;
            int x = 0, y = 0;
            Pos() { setPos((byte) 0x00); }
            public void setPos(byte p){
                value = p;
                x = (p & 0xf0) >> 1;
                y = p & 0x0f;
            }
            public void setX(int _x){
                if(_x >= 16) {
                    Log.d("WIDGET X:", "set x smaller.");
                    return;
                }
                x = _x;
                value = (byte) ((0x0f & value) | 0x10 * _x);
            }
            public void setY(int _y){
                if(_y >= 16) {
                    Log.d("WIDGET Y:", "set y smaller.");
                    return;
                }
                y = _y;
                value = (byte) ((0xf0 & value) | _y);
            }
            public int getValue(){ return value & 0xff; }
            public int getX(){ return x; }
            public int getY() { return y; }
        }
        int id;
        byte type;
        int attr;
        Pos pos;

        Widget(int _id, int sx, int sy, byte _type){
            pos = new Pos();
            setPoint(sx, sy);
            id = _id;
            attr = 0x0000;
            type = _type;
        }

        private void setPoint(int _x, int _y){ pos.setX(_x); pos.setY(_y); }
        public int getID(){ return id; }
        public int getX() { return pos.getX(); }
        public int getY() { return pos.getY(); }
        public int getType() { return type; }
    }
    default int generateID() {
        // Here is id generate algorithm.
        return 0;
    }
    default int insert(int _x, int _y, byte _type){
        return 0;
    }
    default int remove(int _id){
        return 0;
    }
}
