package com.example.lockpocket.shareui;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.TableLayout;

import com.example.lockpocket.R;

public class UserUIActivity extends TableLayout {

    public UserUIActivity(Context context) {
        super(context);
        init(context);
    }

    public UserUIActivity(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    private void init(Context context){
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.activity_userui, this, true);
    }
}
