package com.example.lockpocket;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.Point;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.lockpocket.utils.BitmapConverter;
import com.example.lockpocket.utils.PreferenceManager;
import com.example.lockpocket.utils.TableFloater;

public class TemplateActivity extends AppCompatActivity {
    String template;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_template);
        SharedPreferences pref = androidx.preference.PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        String templateSetting = pref.getString("key_edit_template", "");
        if(templateSetting.equals("")) {
            Toast.makeText(getApplicationContext(), "비정상적인 접근입니다", Toast.LENGTH_SHORT).show();
            finish();
        } else {
            if(PreferenceManager.getString(getApplicationContext(), "edit_lockscreen").equals("")){
                template = templateSetting + "/";
                PreferenceManager.setString(getApplicationContext(), "edit_lockscreen", template);
            } else {
                template = PreferenceManager.getString(getApplicationContext(), "edit_lockscreen");
            }
            placePreview();
        }

        ViewGroup homeButton = findViewById(R.id.home_btn);
        ViewGroup templateButton = findViewById(R.id.template_btn);
        ViewGroup communityButton = findViewById(R.id.community_btn);

        ViewGroup.OnClickListener onClickListener = new ViewGroup.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()) {
                    case R.id.home_btn:
                        HomeButton();
                        break ;
                    case R.id.template_btn:
                        TemplateButton();;
                        break ;
                    case R.id.community_btn:
                        CommunityButton();
                        break ;
                }
            }
        };
        homeButton.setOnClickListener(onClickListener);
        templateButton.setOnClickListener(onClickListener);
        communityButton.setOnClickListener(onClickListener);
    }

    public void HomeButton(){
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(intent);
    }
    public void TemplateButton(){
        Intent intent = new Intent(getApplicationContext(), TemplateActivity.class);
        startActivity(intent);
    }
    public void CommunityButton(){
        Intent intent = new Intent(getApplicationContext(), CommunityActivity.class);
        startActivity(intent);
    }

    void placePreview() {
        ViewGroup preview = findViewById(R.id.preview);
        int width = (int) (getWindowManager().getDefaultDisplay().getWidth() * (1.0/4));
        int height = (int) (getWindowManager().getDefaultDisplay().getHeight() * (1.0/4.0));
        TableFloater tf = new TableFloater(getApplicationContext(), template);
        ViewGroup vg = tf.template(new Point(width, height));
        FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(width, height);
        params.gravity = Gravity.CENTER;
        vg.setLayoutParams(params);
        String backgroundBitmapString = PreferenceManager.getString(getApplicationContext(), "edit_background");
        if(!backgroundBitmapString.equals("")) {
            Bitmap bitmap = BitmapConverter.StringToBitmap(backgroundBitmapString);
            vg.setBackground(new BitmapDrawable(getResources(), bitmap));
        }
        preview.addView(vg);
    }

    public void IntentPreventer(Intent intent){
        intent.setAction(Intent.ACTION_MAIN);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.addCategory(Intent.CATEGORY_LAUNCHER);
    }
}
