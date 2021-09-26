package com.example.lockpocket.utils;

import java.util.Iterator;
import java.util.LinkedList;

public class GridLock46 implements LockTable {
    int _idStack = 1;
    int[][] disable = new int[4][6];

    LinkedList<Widget> widgets = new LinkedList<>();

    GridLock46() { for(int i=0; i<24; i++) disable[i/6][i%6] = -1; widgets = new LinkedList<>(); }

    public int generateID(){
        // Here is id generate algorithm.
        return _idStack++;
    }

    public int insert(int _x, int _y, byte _type) {
        int w = WidgetList.getID(_type).w, h = WidgetList.getID(_type).h;
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

        int w = WidgetList.getID(type).w, h = WidgetList.getID(type).h;
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
