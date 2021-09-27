package com.example.lockpocket;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.lockpocket.fragment.HomeFragment;
import com.example.lockpocket.utils.PreferenceManager;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity {

    private FragmentManager fragmentManager;
    private HomeFragment homeFragment;
    private FragmentTransaction transaction;

    private DrawerLayout drawerLayout;
    private Context context;
    private Toolbar toolbar;

    private View drawerButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context = this;

        fragmentManager = getSupportFragmentManager();

        homeFragment = new HomeFragment();

        transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.task_frame, homeFragment).commitAllowingStateLoss();

        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout_main);
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {
                menuItem.setChecked(true);
                drawerLayout.closeDrawers();

                int id = menuItem.getItemId();
                String title = menuItem.getTitle().toString();

                if(id == R.id.nav_ask){
                    Toast.makeText(context, title + ": 문의를 확인합니다.", Toast.LENGTH_SHORT).show();
                }
                else if(id == R.id.nav_help){
                    Toast.makeText(context, title + ": 도움말을 확인합니다.", Toast.LENGTH_SHORT).show();
                }
                else if(id == R.id.nav_setting){
                    Toast.makeText(context, title + ": 설정 정보를 확인합니다.", Toast.LENGTH_SHORT).show();
                }
                else if(id == R.id.nav_logout){
                    Toast.makeText(context, "로그아웃하였습니다.", Toast.LENGTH_SHORT).show();
                    PreferenceManager.clear(getApplicationContext());
                    Intent ToLogin = new Intent(getApplicationContext(), SigninActivity.class);
                    ToLogin.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                    // LockScreen.getInstance().deactivate();
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

        ImageButton homeButton = (ImageButton) findViewById(R.id.home_btn);
        ImageButton templateButton = (ImageButton) findViewById(R.id.template_btn);
        ImageButton communityButton = (ImageButton) findViewById(R.id.community_btn);
        ImageButton.OnClickListener onClickListener = new ImageButton.OnClickListener() {
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
        finish();
    }
    public void TemplateButton(){
        Intent intent = new Intent(getApplicationContext(), TemplateActivity.class);
        startActivity(intent);
        finish();
    }
    public void CommunityButton(){
        Intent intent = new Intent(getApplicationContext(), CommunityActivity.class);
        startActivity(intent);
        finish();
    }
//    /*
//    hardkgosai by stackoverflow(2021-06-27)
//    https://stackoverflow.com/questions/21724420/how-to-hide-navigation-bar-permanently-in-android-activity
//     */
//    public void hideSystemUI(Window window) { //pass getWindow();
//
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
//
//            window.getInsetsController().hide(WindowInsets.Type.systemBars());
//
//        } else {
//
//            View decorView = window.getDecorView();
//
//            int uiVisibility = decorView.getSystemUiVisibility();
//
//            uiVisibility |= View.SYSTEM_UI_FLAG_LOW_PROFILE;
//            uiVisibility |= View.SYSTEM_UI_FLAG_FULLSCREEN;
//            uiVisibility |= View.SYSTEM_UI_FLAG_HIDE_NAVIGATION;
//            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
//                uiVisibility |= View.SYSTEM_UI_FLAG_IMMERSIVE;
//                uiVisibility |= View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY;
//            }
//
//            decorView.setSystemUiVisibility(uiVisibility);
//        }
//    }
}