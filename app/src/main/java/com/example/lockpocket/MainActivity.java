package com.example.lockpocket;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.app.KeyguardManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;
import com.example.lockpocket.account.LoginRequest;
import com.example.lockpocket.account.LogoutRequest;
import com.example.lockpocket.fragment.AskFragment;
import com.example.lockpocket.fragment.HelpFragment;
import com.example.lockpocket.fragment.HomeFragment;
import com.example.lockpocket.utils.AppNetwork;
import com.example.lockpocket.utils.Encryption;
import com.example.lockpocket.utils.LockScreen;
import com.example.lockpocket.utils.PreferenceManager;
import com.google.android.material.navigation.NavigationView;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    private FragmentManager fragmentManager;
    private HomeFragment homeFragment;
    private AskFragment askFragment;
    private HelpFragment helpFragment;
    private FragmentTransaction transaction;
    private DrawerLayout drawerLayout;
    private Context context;
    private Toolbar toolbar;

    private View drawerButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        androidx.preference.PreferenceManager.setDefaultValues(this, R.xml.settings_template, false);

        context = this;

        fragmentManager = getSupportFragmentManager();

        homeFragment = new HomeFragment();
        askFragment = new AskFragment();
        helpFragment = new HelpFragment();

        transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.task_frame, homeFragment).commitAllowingStateLoss();

        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout_main);
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        View nav_header = navigationView.getHeaderView(0);

        TextView email = nav_header.findViewById(R.id.email_id);
        TextView nickname = nav_header.findViewById(R.id.nickname);
        email.setText(PreferenceManager.getString(getApplicationContext(), "Id"));
        nickname.setText(PreferenceManager.getString(getApplicationContext(), "userName"));
        
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {
                menuItem.setChecked(true);
                drawerLayout.closeDrawers();

                int id = menuItem.getItemId();
                String title = menuItem.getTitle().toString();

                if(id == R.id.nav_ask){
                    transaction = fragmentManager.beginTransaction();
                    transaction.replace(R.id.task_frame, askFragment).commitAllowingStateLoss();
                }
                else if(id == R.id.nav_help){
                    transaction = fragmentManager.beginTransaction();
                    transaction.replace(R.id.task_frame, helpFragment).commitAllowingStateLoss();
                }
                else if(id == R.id.nav_setting){
                    Toast.makeText(context, "잠금화면에서 알림을 받을 수 있도록 알림 가져오기 설정을 허용해주세요", Toast.LENGTH_LONG).show();
                    Intent intent = new Intent("android.settings.ACTION_NOTIFICATION_LISTENER_SETTINGS" ) ; // NEED PERMISSION
                    startActivity(intent) ;
                }
                else if(id == R.id.nav_logout){
                    Toast.makeText(context, "로그아웃하였습니다.", Toast.LENGTH_SHORT).show();

                    String userID = PreferenceManager.getString(getApplicationContext(), "Id");
                    String ui = PreferenceManager.getString(getApplicationContext(), "edit_lockscreen");
                    String bg = PreferenceManager.getString(getApplicationContext(), "edit_background");
                    Log.i("GetID", userID);
                    Log.i("GetBG", bg);
                    Log.i("GetLS", ui);
                    Response.Listener<String> responseListener = new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            Log.i("LogoutReq", response);
                        }
                    };
                    try {
                        LogoutRequest logoutRequest = new LogoutRequest(userID, ui, bg, responseListener, getApplicationContext());
                        RequestQueue queue = Volley.newRequestQueue(MainActivity.this);
                        queue.add(logoutRequest);
                    } catch (IOException e) {
                        Log.d("LogoutReq: ", e.getMessage());
                    }

                    PreferenceManager.clear(getApplicationContext());
                    Intent ToLogin = new Intent(getApplicationContext(), SigninActivity.class);
                    ToLogin.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                    LockScreen.getInstance().deactivate();
                    startActivity(ToLogin);
                }

                return true;
            }
        });
        
        drawerButton = findViewById(R.id.drawer_btn);
        drawerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerLayout.openDrawer(Gravity.RIGHT);
            }
        });

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
        transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.task_frame, homeFragment).commitAllowingStateLoss();

    }
    public void TemplateButton(){
        Intent intent = new Intent(getApplicationContext(), TemplateActivity.class);
        startActivity(intent);
    }
    public void CommunityButton(){
        Intent intent = new Intent(getApplicationContext(), CommunityActivity.class);
        startActivity(intent);
    }

}