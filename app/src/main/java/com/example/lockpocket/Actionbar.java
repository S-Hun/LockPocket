package com.example.lockpocket;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;

import androidx.drawerlayout.widget.DrawerLayout;

public class Actionbar extends LinearLayout {
    public Actionbar(Context context) {
        super(context);
    }

    public Actionbar(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public Actionbar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    public Actionbar(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context);
    }

    private void init(Context context) {
        View view = View.inflate(context, R.layout.actionbar, this);
    }
    public static DrawerLayout drawerLayout;
    public static View drawerView;

}
