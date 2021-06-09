package com.example.lockpocket.shareui;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.provider.Contacts;
import android.text.Layout;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TextView;

import androidx.core.content.ContextCompat;

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

        LinearLayout[] layoutList = new LinearLayout[]{
                (LinearLayout)findViewById(R.id.view1),
                (LinearLayout)findViewById(R.id.view2),
                (LinearLayout)findViewById(R.id.view3),
                (LinearLayout)findViewById(R.id.view4)
        };

        UIView views[] = new UIView[4];

        for(int i=0; i<4; i++){
            views[i] = new UIView(
                    context,
                    layoutList[i]
            );
        }
    }
}
