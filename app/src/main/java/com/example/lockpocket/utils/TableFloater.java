package com.example.lockpocket.utils;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Point;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.example.lockpocket.R;

import java.util.ArrayList;

public class TableFloater {
    private Context context;
    private String data;

    public TableFloater(Context context, String data) {
        this.context = context;
        this.data = data; // widgetView/type:x:y:attr/0:0:0:0/0:0:0:0/0:0:0:0
    }

    public ViewGroup template(Point size) {
        return template(size, 16);
    }
    public ViewGroup template(Point size, int pad) {
        return template(size, pad, 5);
    }
    public ViewGroup template(Point size, int pad, int textSize) {
        String[] list = data.split("/");
        LinearLayout base = new LinearLayout(context);
        base.setOrientation(LinearLayout.VERTICAL);

        FrameLayout lock = new FrameLayout(context);
        lock.setBackgroundColor(Color.parseColor("#33000000"));

        ImageView ic_lock = new ImageView(context);
        FrameLayout.LayoutParams ic_lockParam = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        ic_lockParam.gravity = Gravity.CENTER;
        ic_lock.setImageResource(R.drawable.ic_lock);
        ic_lock.setLayoutParams(ic_lockParam);
        lock.addView(ic_lock);

        LinearLayout.LayoutParams lockParam = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        lockParam.weight = 1;
        lock.setLayoutParams(lockParam);
        base.addView(lock);

        if(list[0].equals("grid46")) {
            RelativeLayout table = new RelativeLayout(context);
            LinearLayout.LayoutParams tableParam = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            tableParam.weight = 3;
            table.setLayoutParams(tableParam);
            base.addView(table, 0);

            LockTable pattern = new GridLock46(context, base);
            for(int i=1; i < list.length; i++){
                String li = list[i];
                if(li.equals("")) break;
                String[] info = li.split(":");
                int type = Integer.parseInt(info[0]);
                int x = Integer.parseInt(info[1]);
                int y = Integer.parseInt(info[2]);
                int w = WidgetList.getId(type).w;
                int h = WidgetList.getId(type).h;

                int vw = size.x / 4;
                int vh = (int) (size.y * (1/7.0));

                ViewGroup v = pattern.getViewGroup(type, new Point(x, y), pad, textSize);
                RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(vw * w, vh * h);
                params.setMargins(vw * x, vh * y, 0, 0);
                v.setLayoutParams(params);
                table.addView(v);
            }
        }
        return base;
    }
}
