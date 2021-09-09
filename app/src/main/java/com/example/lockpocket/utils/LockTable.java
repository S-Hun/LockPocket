package com.example.lockpocket.utils;

import android.util.Log;

import java.util.Iterator;
import java.util.LinkedList;

public class LockTable {

    int _widgetID = 1;
    int[][] disable = new int[5][6];
    short attribute = 0;
    String title = "";
    String author = "";
    String desc = "";

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
            public byte getValue(){ return (byte) (value & 0xff); }
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

        public void setPoint(int _x, int _y){ pos.setX(_x); pos.setY(_y); }
        public void setAttr(int _attr) { attr = _attr; }
        public int getID(){ return id; }
        public int getX() { return pos.getX(); }
        public int getY() { return pos.getY(); }
        public int getType() { return type; }
    }

    LinkedList<Widget> widgets = new LinkedList<>();

    LockTable() { for(int i=0; i<30; i++) disable[i/6][i%6] = -1; widgets = new LinkedList<>(); }

    private int generateID(){
        // Here is id generate algorithm.
        return _widgetID++;
    }

    public int insert(int _x, int _y, byte _type) {
        int w = WidgetList.getWidgetType(_type).w, h = WidgetList.getWidgetType(_type).h;
        int _id = generateID();

        for(int i=_y; i < _y + h; i++) {
            for (int j = _x; j < _x + w; j++)
                if (disable[i][j] > 0) return -1; // return widget
        }

        widgets.add(new Widget(_id, _x, _y, _type));

        // fill blank space.
        for(int i=_y; i < _y + h; i++){
            for(int j=_x; j < _x + w; j++)
                disable[i][j] = _id;
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

        int w = WidgetList.getWidgetType(type).w, h = WidgetList.getWidgetType(type).h;
        // fill blank space.
        for(int i=y; i < y + h; i++){
            for(int j=x; j < x + w; j++)
                disable[i][j] = -1;
        }

        return -1;
    }

    public void print() {
        Iterator<Widget> iterator = widgets.iterator();
        while(iterator.hasNext()) {
            Widget temp = iterator.next();
            System.out.println(Integer.toString(temp.getID()) + " " + Integer.toString(temp.getType()));
        }
    }

}
