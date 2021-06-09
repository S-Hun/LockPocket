package com.example.lockpocket.shareui;

import android.content.Context;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.lockpocket.R;
import com.example.lockpocket.account.PreferenceManager;

class UIView {
    Context context;
    ImageView img;
    TextView title;
    TextView nickname;
    Button button;
    String data;

    UIView(Context c, LinearLayout l) {
        context = c;
        img = l.findViewById(R.id.shared_ui);
        title = l.findViewById(R.id.title);
        nickname = l.findViewById(R.id.name);
        data = "ZZZZZZZZZZZZ";
        button = l.findViewById(R.id.download);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PreferenceManager.setString(context, "UI", data);
            }
        });
        // Temporal initial
        setContents(R.drawable.samsung2, "Blank Title", "Blank Name");
    }

    void setContents(int i, String t, String n){
        img.setImageResource(i);
        title.setText(t);
        nickname.setText(n);
    }
}
