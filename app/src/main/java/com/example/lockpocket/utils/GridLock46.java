package com.example.lockpocket.utils;

import android.content.ClipData;
import android.content.ClipDescription;
import android.content.Context;
import android.graphics.Color;
import android.graphics.Point;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.ShapeDrawable;
import android.util.Log;
import android.util.Size;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.lockpocket.EditActivity;
import com.example.lockpocket.R;

import java.util.Iterator;
import java.util.LinkedList;

public class GridLock46 extends LockTable {
    static final String TAG = "GridLock46";

    public GridLock46(Context context, ViewGroup layout) {
        super(context, layout);
        for(int i=0; i<24; i++)
            state.add(0);
        widgets = new LinkedList<>();
        tableWidget = new int[]{
                5, 7, 8, 1, 2, 4, 6
        };
    }

    @Override
    public boolean checkPosition(Point p, Size s, int value) {
        // check logic here.
        for(int i=p.y; i<p.y+s.getHeight(); i++)
        {
            for(int j=p.x; j<p.x+s.getWidth(); j++)
            {
                if(i >= 6 || j >= 4) return false;
                int comp = state.get(i*4+j);
                if(comp > 0 && comp != value) return false;
            }
        }
        return true;
    }

    @Override
    public void setUpPosition(Point p, Size s, int id) {
        // disable rule here.
        widgets.get(searchWidgetIndexWithId(id)).setPoint(p);
        for(int i=p.y; i<p.y+s.getHeight(); i++)
        {
            for(int j=p.x; j<p.x+s.getWidth(); j++)
            {
                state.set(i*4+j, id);
            }
        }
    }

    @Override
    public void clearPosition(int src, int dst){
        for(int i=0; i<6; i++)
        {
            for(int j=0; j<4; j++)
            {
                if(state.get(i*4+j) == src) {
                    state.set(i*4+j, dst);
                }
            }
        }
    }

    @Override
    public void insertWidget(int x, int y, int type, Size vs) {
        int w = WidgetList.getId(type).w, h = WidgetList.getId(type).h;

        if(!checkPosition(new Point(x, y), new Size(w, h), 0xffffffff)) return;

        Widget widget = new WidgetBuilder()
                .setPoint(x, y)
                .setType(type)
                .setViewSize(vs)
                .build();
        widget.getView().setOnLongClickListener(new WidgetLongClickListener());

        widgets.add(widget);
        setUpPosition(new Point(x, y), new Size(w,h), widget.getId());
        int count = layout.getChildCount();
        layout.addView(widget.getView(), count);
        Log.d(TAG, "INSERT VIEW : [x]" + Integer.toString(x) + " [y]" + Integer.toString(y) + " [type]" + Integer.toString(type));
    }

    @Override
    public void removeWidget(int id) {
        // id from param of view.getId()
        int idx = searchWidgetIndexWithId(id);
        widgets.remove(idx);
        layout.removeView(layout.findViewById(id));

        clearPosition(id, 0);

        return;
    }

    @Override
    public String widgetToString(Widget w) {
        int type = w.getType();
        int attr = w.getAttr();
        Point p = w.getPoint();
        int x = p.x; int y = p.y;
        String res = "";
        res = Integer.toString(type) + ":" + Integer.toString(x) + ":" + Integer.toString(y) + ":" + Integer.toString(attr);
        return res;
    }

    @Override
    public String tableToString() {
        String res = "grid46/";
        for(Widget w : widgets){
            res += widgetToString(w) + "/";
        }
        return res;
    }

    @Override
    public void stringToTable(String s, Size vs) {
        String[] list = s.split("/");
        for(int i=1; i < list.length; i++){
            String li = list[i];
            if(li.equals("")) break;
            String[] info = li.split(":");
            int type = Integer.parseInt(info[0]);
            int x = Integer.parseInt(info[1]);
            int y = Integer.parseInt(info[2]);
            insertWidget(new Point(x, y), type, vs);
        }
    }

    @Override
    public RelativeLayout getViewGroup(int type, Point vs, int pad, int textSize) {
        RelativeLayout result = new RelativeLayout(context);
        if(type == 7 || type == 8 || type == 1 || type == 2 || type == 4) {
            result.setBackgroundResource(R.drawable.bg_widget);
            Drawable background = result.getBackground().mutate();
            setColor(background, WidgetList.getId(type).color);
            ImageView v1 = new ImageView(context);
            v1.setImageResource(WidgetList.getId(type).icon);
            v1.setPadding(pad, pad, pad, pad);
            RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
            params.alignWithParent = true;
            v1.setLayoutParams(params);
            result.addView(v1);
        } else if(type == 5) {
            result.setBackgroundResource(R.drawable.bg_widget);
            Drawable background = result.getBackground().mutate();
            setColor(background, "#000000", 0x33);
            TextView v1 = new TextView(context);
            v1.setId(View.generateViewId());
            v1.setText("알림");
            v1.setTextSize(textSize);
            v1.setTextColor(Color.parseColor("#ffffff"));
            v1.setTypeface(null, Typeface.BOLD);
            v1.setPadding(pad, pad, 0, pad / 2); // ltrb
            View v2 = new View(context);
            v2.setId(View.generateViewId());
            v2.setBackgroundColor(Color.parseColor("#ffffff"));
            RelativeLayout.LayoutParams params2 = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, 3);
            params2.addRule(RelativeLayout.BELOW, v1.getId());
            v2.setPadding(pad, 0, pad, pad / 2); // ltrb
            v2.setLayoutParams(params2);
            TextView v3 = new TextView(context);
            v3.setText("LockPocket 잠금화면이 실행중입니다.");
            v3.setTextSize(textSize);
            v3.setTextColor(Color.parseColor("#ffffff"));
            RelativeLayout.LayoutParams params3 = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
            params3.addRule(RelativeLayout.BELOW, v2.getId());
            v3.setPadding(pad, 0, 0, pad / 2); // ltrb
            v3.setLayoutParams(params3);
            result.addView(v1);
            result.addView(v2);
            result.addView(v3);
        } else if(type == 6) {
            TextView v1 = new TextView(context);
            v1.setId(View.generateViewId());
            v1.setPadding(pad * 3, 0, 0, 0);
            v1.setText("23");
            v1.setTypeface(null, Typeface.BOLD);
            v1.setTextColor(Color.parseColor("#ffffff"));
            v1.setAutoSizeTextTypeWithDefaults(TextView.AUTO_SIZE_TEXT_TYPE_UNIFORM);
            RelativeLayout.LayoutParams params1 = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, vs.y - 36);
            v1.setLayoutParams(params1);

            TextView v2 = new TextView(context);
            v2.setPadding(0, 0, pad * 3, 0);
            v2.setGravity(Gravity.END);
            v2.setText("59");
            v2.setTypeface(null, Typeface.BOLD);
            v2.setTextColor(Color.parseColor("#ffffff"));
            v2.setAutoSizeTextTypeWithDefaults(TextView.AUTO_SIZE_TEXT_TYPE_UNIFORM);
            RelativeLayout.LayoutParams params2 = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, vs.y - 18);
            params2.addRule(RelativeLayout.BELOW, v1.getId());
            params2.addRule(RelativeLayout.ALIGN_PARENT_END);
            v2.setLayoutParams(params2);
            result.addView(v1);
            result.addView(v2);

            pad = 0;
            vs.y += vs.y / 4;
        }
        int w = WidgetList.getId(type).w;
        int h = WidgetList.getId(type).h;
        ViewGroup.MarginLayoutParams marginParams = new ViewGroup.MarginLayoutParams(w * vs.x - pad, h * vs.y - pad);
        result.setLayoutParams(marginParams);
        return result;
    }

    private final class WidgetLongClickListener implements View.OnLongClickListener {
        @Override
        public boolean onLongClick(View v) {
            ClipData.Item item = new ClipData.Item(
                    (CharSequence) v.getTag()
            );
            String[] mimeTypes = {ClipDescription.MIMETYPE_TEXT_PLAIN};
            ClipData data = new ClipData(v.getTag().toString(), mimeTypes, item);
            CustomDragShadowBuilder shadowBuilder = new CustomDragShadowBuilder(v);



            v.startDrag(data,
                    shadowBuilder,
                    v,
                    0);

            v.setVisibility(View.INVISIBLE);

            return true;
        }
    }

    void setColor(Drawable background, String color) {
        setColor(background, color, 0xff);
    }
    void setColor(Drawable background, String color, int alpha) { setColor(background, Color.parseColor(color), alpha); }
    void setColor(Drawable background, int color) { setColor(background, color, 0xff); }
    void setColor(Drawable background, int color, int alpha) {
        if (background instanceof ShapeDrawable) {
            // cast to 'ShapeDrawable'
            ShapeDrawable shapeDrawable = (ShapeDrawable) background;
            shapeDrawable.getPaint().setColor(color);
            shapeDrawable.getPaint().setAlpha(alpha);
        } else if (background instanceof GradientDrawable) {
            // cast to 'GradientDrawable'
            GradientDrawable gradientDrawable = (GradientDrawable) background;
            gradientDrawable.setColor(color);
            gradientDrawable.setAlpha(alpha);
        } else if (background instanceof ColorDrawable) {
            // alpha value may need to be set again after this call
            ColorDrawable colorDrawable = (ColorDrawable) background;
            colorDrawable.setColor(color);
            colorDrawable.setAlpha(alpha);
        }
    }
}
