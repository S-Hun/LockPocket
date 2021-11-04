package com.example.lockpocket;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Point;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.lockpocket.utils.BitmapConverter;
import com.example.lockpocket.utils.PreferenceManager;
import com.example.lockpocket.utils.TableFloater;

public class CommunityDetail extends AppCompatActivity {

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_community_detail);
        ImageView back_btn = (ImageView)findViewById(R.id.btn_back);
        back_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        Intent intent = getIntent();

        FrameLayout table = findViewById(R.id.ui_view);
        TextView id = findViewById(R.id.id);
        TextView date = findViewById(R.id.date);
        Button download = findViewById(R.id.btn_download);

        id.setText(intent.getStringExtra("id"));
        date.setText(intent.getStringExtra("date"));

        download.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AlertDialog.Builder(getApplicationContext()).setMessage("원래 잠금화면 설정을 지우고 덮어씁니다.")
                        .setTitle("주의")
                        .setPositiveButton("계속", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                PreferenceManager.setString(getApplicationContext(), "edit_lockscreen", intent.getStringExtra("ui"));
                                PreferenceManager.setString(getApplicationContext(), "edit_background", intent.getStringExtra("bg"));
                            }
                        })
                        .setNegativeButton("취소", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                // nothing to do
                            }
                        }).show();
            }
        });

        table.post(new Runnable() {
            @Override
            public void run() {
                int w = table.getWidth() + 100;
                TableFloater tb = new TableFloater(getApplicationContext(), intent.getStringExtra("ui"));
                ViewGroup vg = tb.template(new Point(w/2, w), 32);
                FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(w/2, w);
                params.gravity = Gravity.CENTER_HORIZONTAL;
                vg.setLayoutParams(params);
                if(!intent.getStringExtra("bg").equals("")) {
                    Bitmap bitmap = BitmapConverter.StringToBitmap(intent.getStringExtra("bg"));
                    vg.setBackground(new BitmapDrawable(getApplication().getResources(), bitmap));
                }
                table.addView(vg);
            }
        });
    }
}
