package com.example.lockpocket;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lockpocket.fragment.AskFragment;
import com.example.lockpocket.fragment.HelpFragment;
import com.example.lockpocket.fragment.HomeFragment;
import com.example.lockpocket.utils.Encryption;
import com.example.lockpocket.utils.LockScreen;
import com.example.lockpocket.utils.PreferenceManager;
import com.google.android.material.navigation.NavigationView;

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
        Log.d("Encryption test", Encryption.SHA1("1234"));

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
                    Toast.makeText(context, title + ": 설정 정보를 확인합니다.", Toast.LENGTH_SHORT).show();
                }
                else if(id == R.id.nav_logout){
                    Toast.makeText(context, "로그아웃하였습니다.", Toast.LENGTH_SHORT).show();
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