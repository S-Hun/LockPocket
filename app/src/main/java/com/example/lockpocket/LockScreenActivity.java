package com.example.lockpocket;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.WindowManager;
import android.widget.Toast;

public class LockScreenActivity extends AppCompatActivity {
    private static View[] pos;
    private SharedPreferences pref;
    private SharedPreferences.Editor editor;
    String UI;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_lock);
        UnlockBar unlock = (UnlockBar)findViewById(R.id.unlock);
        pref = getSharedPreferences("pref", Activity.MODE_PRIVATE);
        editor = pref.edit();

        pos = new View[]{
                findViewById(R.id.pos1),
                findViewById(R.id.pos2),
                findViewById(R.id.pos3),
                findViewById(R.id.pos4),
                findViewById(R.id.pos5),
                findViewById(R.id.pos6),
                findViewById(R.id.pos7),
                findViewById(R.id.pos8),
                findViewById(R.id.pos9),
                findViewById(R.id.pos10),
                findViewById(R.id.pos11),
                findViewById(R.id.pos12)
        };

        UI = pref.getString("UI", "ZZZZZZZZZZZZ");
        if(UI.length() < 12) UI = "ZZZZZZZZZZZZ";

        for(int i=0; i<UI.length(); i++){
            if(UI.charAt(i)=='A') pos[i].setBackground(getDrawable(R.drawable.background_red));
            else if(UI.charAt(i)=='B') pos[i].setBackground(getDrawable(R.drawable.background_blue));
            else if(UI.charAt(i)=='C') pos[i].setBackground(getDrawable(R.drawable.background_green));
            else {
                pos[i].setBackground(getDrawable(R.drawable.background_white));
            }
        }

        // Attach listener
        unlock.setOnUnlockListenerRight(new UnlockBar.OnUnlockListener() {
            @Override
            public void onUnlock()
            {
                Toast.makeText(LockScreenActivity.this, "Right Action", Toast.LENGTH_SHORT).show();
                finish();
            }
        });


        unlock.setOnUnlockListenerLeft(new UnlockBar.OnUnlockListener() {
            @Override
            public void onUnlock()
            {
                Toast.makeText(LockScreenActivity.this, "Left Action", Toast.LENGTH_SHORT).show();
                finish();
            }
        });


    }

    @Override
    public void onAttachedToWindow() {

        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON|
                WindowManager.LayoutParams.FLAG_DISMISS_KEYGUARD|
//                WindowManager.LayoutParams.FLAG_TURN_SCREEN_ON|
                WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED);

        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);
        super.onAttachedToWindow();

    }

    @Override
    protected void onResume() {
        super.onResume();
        ((LockApplication) getApplication()).lockScreenShow = true;
    }

    @Override
    protected void onPause() {
        super.onPause();
        ((LockApplication) getApplication()).lockScreenShow = false;
    }

    @Override
    public void onBackPressed() {
        //super.onBackPressed();
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        return false;
    }
}
