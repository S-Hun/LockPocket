package com.example.lockpocket.utils;

import android.util.Log;

import java.util.Iterator;
import java.util.LinkedList;

public class LockTable {

    // Move to attributes in Type class.
    static Type[] typeList = {
            new Type(1, 1, 0),
            new Type(1, 1, 0),
            new Type(1, 1, 0),
            new Type(3, 2, 0),
            new Type(3, 4, 0)
    };

    boolean[][] disable = new boolean[5][6];

    static private class Widget {
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

        int _idStack = 1;

        Widget(int sx, int sy, byte _type){
            setPoint(sx, sy);
            id = generateID();
            attr = 0x0000;
            type = _type;
        }

        private void setPoint(int _x, int _y){ pos.setX(_x); pos.setY(_y); }
        private int generateID(){
            // Here is id generate algorithm.
            return _idStack++;
        }
        public int getID(){ return id; }
        public int getX() { return pos.getX(); }
        public int getY() { return pos.getY(); }
        public int getType() { return type; }
    }

    LinkedList<Widget> widgets = new LinkedList<>();

    public int insert(int _x, int _y, byte _type) {
        int w = typeList[_type].w, h = typeList[_type].h;

        for(int i=_y; i < _y + h; i++) {
            for (int j = _x; j < _x + w; j++)
                if (disable[i][j]) return -1; // return widget
        }

        widgets.add(new Widget(_x, _y, _type));

        // fill blank space.
        for(int i=_y; i < _y + h; i++){
            for(int j=_x; j < _x + w; j++)
                disable[i][j] = true;
        }
        return 0;
    }

    public int remove(int _id) {
        int x = 0, y = 0, type = 0;
        Iterator<Widget> iterator = widgets.iterator();
        while(iterator.hasNext()) {
            Widget temp = iterator.next();
            if(temp.getID() == _id){
                x = temp.getX();
                y = temp.getY();
                type = temp.getType();
                widgets.remove(temp);
                break;
            }
        }

        int w = typeList[type].w, h = typeList[type].h;
        // fill blank space.
        for(int i=y; i < y + h; i++){
            for(int j=x; j < x + w; j++)
                disable[i][j] = false;
        }

        return -1;
    }

}
