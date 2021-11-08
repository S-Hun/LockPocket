package com.example.lockpocket;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.GridView;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;
import com.example.lockpocket.fragment.DownloadRequest;
import com.example.lockpocket.paging.ListViewAdapter;
import com.lakue.pagingbutton.LakuePagingButton;
import com.lakue.pagingbutton.OnPageSelectListener;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CommunityActivity extends AppCompatActivity {
    ViewTreeObserver.OnGlobalLayoutListener mGlobalLayoutListener;

    private GridView gridView;                      // GridView 뷰
    private boolean lastItemVisibleFlag = false;    // 리스트 스크롤이 마지막 셀(맨 바닥)로 이동했는지 체크할 변수
    private List<String> bg;
    private List<String> id;
    private List<String> date;
    private List<String> ui;
    private ListViewAdapter adapter;                // 리스트뷰의 아답터
    private int page = 0;                           // 페이징변수. 초기 값은 0 이다.
    private final int OFFSET = 10;                  // 한 페이지마다 로드할 데이터 갯수.
    private boolean mLockListView = false;          // 데이터 불러올때 중복안되게 하기위한 변수
    LakuePagingButton lpb_buttonlist;
    int max_page = 30;
    String arr[];
    Context mContext;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_community);
        ConstraintLayout footer = (ConstraintLayout)findViewById(R.id.footer);


        mGlobalLayoutListener = new ViewTreeObserver.OnGlobalLayoutListener() {

            @Override

            public void onGlobalLayout() {
                LinearLayout gridview = (LinearLayout)findViewById(R.id.gridview);
                ConstraintLayout.LayoutParams control = (ConstraintLayout.LayoutParams) gridview.getLayoutParams();



                int height = footer.getHeight();

                gridview.setPadding(0, 0, 0, height * 2);


                removeOnGlobalLayoutListener(footer.getViewTreeObserver(), mGlobalLayoutListener);

            }

        };
        footer.getViewTreeObserver().addOnGlobalLayoutListener(mGlobalLayoutListener);
        ViewGroup homeButton = findViewById(R.id.home_btn);
        ViewGroup templateButton = findViewById(R.id.template_btn);
        ViewGroup communityButton = findViewById(R.id.community_btn);

        getReponse();
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
        gridView = (GridView) findViewById(R.id.listview);
        ui = new ArrayList<String>();
        date = new ArrayList<String>();
        id = new ArrayList<String>();
        bg = new ArrayList<String>();
        adapter = new ListViewAdapter(this, ui, date, id, bg);
        gridView.setAdapter(adapter);

        lpb_buttonlist = findViewById(R.id.lpb_buttonlist);
        lpb_buttonlist.setPageItemCount(5);
        lpb_buttonlist.addBottomPageButton(max_page,1);

        lpb_buttonlist.setOnPageSelectListener(new OnPageSelectListener() {
            //BeforeButton Click
            @Override
            public void onPageBefore(int now_page) {
                lpb_buttonlist.addBottomPageButton(max_page,now_page);
            }

            @Override
            public void onPageCenter(int now_page) {
                //  lpb_buttonlist.addBottomPageButton(max_page,page);
                getItem(now_page);
            }

            //NextButton Click
            @Override
            public void onPageNext(int now_page) {
                lpb_buttonlist.addBottomPageButton(max_page,now_page);
            }
        });
        getItem(1);
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

    public void IntentPreventer(Intent intent){
        intent.setAction(Intent.ACTION_MAIN);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.addCategory(Intent.CATEGORY_LAUNCHER);
    }
    private static void removeOnGlobalLayoutListener(ViewTreeObserver observer, ViewTreeObserver.OnGlobalLayoutListener listener) {
        if (observer == null) {
            return ;
        }

        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.JELLY_BEAN) {

            observer.removeGlobalOnLayoutListener(listener);
        } else {
            observer.removeOnGlobalLayoutListener(listener);
        }
    }


    private void getItem(int now_num){
        int offset = 10;
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                mLockListView = true;
                bg.clear();
                ui.clear();
                id.clear();
                date.clear();
                for(int i=4+(now_num-1) * 40; i<4+now_num * 40; i++)
                {

                    if(arr.length <= i )
                        break;
                    if(i % 4 == 0)
                        id.add(arr[i]);
                    else if(i % 4 == 1)
                        date.add(arr[i]);
                    else if(i % 4 == 2)
                        ui.add(arr[i]);
                    else {
                        bg.add(arr[i]);
                    }
                }
                adapter.notifyDataSetChanged();
                mLockListView = false;
                gridView.setSelection(0);
            }
        }, 1000);
    }
    private void getReponse(){

        Response.Listener<String> responseListener = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                arr = response.split(",");
            }
        };

        try {
            DownloadRequest downloadRequest = new DownloadRequest(responseListener, getApplicationContext());
            RequestQueue queue = Volley.newRequestQueue(CommunityActivity.this);
            queue.add(downloadRequest);
        } catch (IOException e) {
            Log.d("textQue: ", e.getMessage());
        }

    }
}