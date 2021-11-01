package com.example.lockpocket.utils;

import android.content.Context;
import android.graphics.Point;
import android.util.Log;
import android.util.Size;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.example.lockpocket.R;

import java.util.ArrayList;
import java.util.List;

public class LockTable {
    protected Context context = null;
    protected ViewGroup layout = null;
    public List<Integer> state = new ArrayList<>();
    public List<Widget> widgets = null;
    static final String TAG = "LockTable Parent";

    LockTable(Context context, ViewGroup layout) {
        this.context = context;
        this.layout = layout;
        state = new ArrayList<>();
    }

    class Widget {
        int type;
        int attr;
        Point point;
        RelativeLayout view;

        Widget(Point p, Size s, int type){
            if(p == null || s == null) return;
            this.point = new Point(p);
            this.type = type;
            this.attr = WidgetList.getId(type).attr;

            view = generateViewGroup(s);
            view.setId(View.generateViewId());
        }

        private RelativeLayout generateViewGroup(Size _vs) {
            // _s is "View frame size"
            RelativeLayout res = getViewGroup(type, _vs);

            final int PAD = 36;
            // w, h are "Widget size"
            int w = WidgetList.getId(type).w;
            int h = WidgetList.getId(type).h;

            ViewGroup.MarginLayoutParams marginParams = (ViewGroup.MarginLayoutParams) res.getLayoutParams();
            marginParams.leftMargin = point.x * _vs.getWidth() + PAD / 2;
            marginParams.topMargin = point.y * _vs.getHeight() + PAD / 2;
            res.setLayoutParams(marginParams);

            res.setTag(R.string.role, "placed_widget");
            res.setTag(R.string.role_describe, type);
            res.setTag(WidgetList.getName(type));
            res.setBackgroundResource(R.drawable.bg_widget);

            return res;
        }

        public void setPoint(Point _p){ this.point.set(_p.x, _p.y); }
        public void setPoint(int _x, int _y) { this.point.set(_x, _y); }
        public void setType(int _t) { this.type = _t; }
        public void setAttr(int _a) { this.attr = _a; }

        public int getId(){ return view.getId(); }
        public Point getPoint() { return new Point(point); }
        public int getType() { return type; }
        public int getAttr() { return attr; }
        public RelativeLayout getView() { return view; }
    }

    public class WidgetBuilder {
        int type = 0;
        Point p = null;
        Size s = null;

        public WidgetBuilder setType(int type) {
            this.type = type;
            return this;
        }

        public WidgetBuilder setPoint(Point p) {
            this.p = p;
            return this;
        }

        public WidgetBuilder setPoint(int x, int y) {
            this.p = new Point(x, y);
            return this;
        }

        public WidgetBuilder setViewSize(Size s) {
            this.s = s;
            return this;
        }

        public WidgetBuilder setViewSize(int w, int h) {
            this.s = new Size(w, h);
            return this;
        }

        public Widget build() {
            return new Widget(p, s, type);
        }
    }

    public boolean checkPosition(Point p, Size s, int value) {
        // check logic here.
        return false;
    }

    public void setUpPosition(Point p, Size s, int value) {
        // disable rule here.
        for(int i=p.y; i<p.y+s.getHeight(); i++)
        {
            for(int j=p.x; j<p.x+s.getWidth(); j++)
            {
                state.set(i+j, value);
            }
        }
    }

    public void clearPosition(int src, int dst){
        for(int i=0; i<0; i++)
        {
            for(int j=0; j<0; j++)
            {
                if(state.get(i*4+j) == src) {
                    state.set(i*4+j, dst);
                }
            }
        }
    }

    public int searchWidgetIndexWithId(int id) {
        if(id <= 0) return -1;
        for(int i = 0; i < widgets.size(); i++) {
            if(widgets.get(i).getId() == id)
                return i;
        }
        return -1;
    }

    public void insertWidget(int x, int y, int type, Size vs) {
        Point p = new Point(x, y);
        Size s = new Size(WidgetList.getId(type).w, WidgetList.getId(type).h);

        if(!checkPosition(p, s, 0xffffffff))
        {
            // insert disabled.
            return;
        }

        Widget widget = new WidgetBuilder()
                .setPoint(x, y)
                .setViewSize(vs)
                .setType(type)
                .build();

        setUpPosition(p, s, widget.getId());
        widgets.add(widget);
        Log.d(TAG, "INSERT VIEW : [x]" + Integer.toString(x) + " [y]" + Integer.toString(y) + " [type]" + Integer.toString(type));
    }

    public void insertWidget(Point p, int type, Size vs) {
        insertWidget(p.x, p.y, type, vs);
    }

    public void removeWidget(Point p) {
        // get id from state
        int id = state.get(p.x+p.y); // specify position role
        int idx = searchWidgetIndexWithId(id);
        Widget widget = widgets.get(idx);
        widgets.remove(widget);

        int type = widget.getType();
        int w = WidgetList.getId(type).w;
        int h = WidgetList.getId(type).h;
        setUpPosition(widget.getPoint(), new Size(w, h), 0);

        return;
    }

    public String widgetToString(Widget w) { return ""; }
    public String tableToString() { return ""; }
    public View stringToWidget(String s) { return null; }
    public void stringToTable(ViewGroup v, String s, Size vs) { return; };

    public RelativeLayout getViewGroup(int type, Size vs) {
        RelativeLayout result;
        Log.d("IN LOCK", "getViewGroup");
        return null;
    }
}
