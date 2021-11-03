package com.example.lockpocket;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.lakue.pagingbutton.LakuePagingButton;
import com.lakue.pagingbutton.OnPageSelectListener;

import java.util.ArrayList;
import java.util.List;

public class CommunityActivity extends AppCompatActivity {
    ViewTreeObserver.OnGlobalLayoutListener mGlobalLayoutListener;

    private GridView gridView;                      // GridView 뷰
    private boolean lastItemVisibleFlag = false;    // 리스트 스크롤이 마지막 셀(맨 바닥)로 이동했는지 체크할 변수
    private List<String> list;                      // String 데이터를 담고있는 리스트
    private ListViewAdapter adapter;                // 리스트뷰의 아답터
    private int page = 0;                           // 페이징변수. 초기 값은 0 이다.
    private final int OFFSET = 10;                  // 한 페이지마다 로드할 데이터 갯수.
    private boolean mLockListView = false;          // 데이터 불러올때 중복안되게 하기위한 변수
    LakuePagingButton lpb_buttonlist;
    int pages = 1;
    int max_page = 30;

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


                int width = footer.getWidth();
                int height = footer.getHeight();

                gridview.setPadding(0, 0, 0, height * 2);
                // gridview.setLayoutParams(control);
                

//리스너 제거를 위한 메소드

                removeOnGlobalLayoutListener(footer.getViewTreeObserver(), mGlobalLayoutListener);

            }

        };
        footer.getViewTreeObserver().addOnGlobalLayoutListener(mGlobalLayoutListener);
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
        gridView = (GridView) findViewById(R.id.listview);
        list = new ArrayList<String>();
        adapter = new ListViewAdapter(this, list);
        gridView.setAdapter(adapter);

        lpb_buttonlist = findViewById(R.id.lpb_buttonlist);

        lpb_buttonlist.setPageItemCount(5);
        lpb_buttonlist.addBottomPageButton(max_page,1);

        lpb_buttonlist.setOnPageSelectListener(new OnPageSelectListener() {
            //BeforeButton Click
            @Override
            public void onPageBefore(int now_page) {
                lpb_buttonlist.addBottomPageButton(max_page,now_page);
                getItem(now_page);
            }

            @Override
            public void onPageCenter(int now_page) {
                getItem(now_page);
                //  lpb_buttonlist.addBottomPageButton(max_page,page);
            }

            //NextButton Click
            @Override
            public void onPageNext(int now_page) {
                lpb_buttonlist.addBottomPageButton(max_page,now_page);
                getItem(now_page);
            }
        });
        getItem(0);
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
    private void getItem(int page_num){

        // 리스트에 다음 데이터를 입력할 동안에 이 메소드가 또 호출되지 않도록 mLockListView 를 true로 설정한다.
        mLockListView = true;

        // 다음 20개의 데이터를 불러와서 리스트에 저장한다.
        list.clear();
        for(int i = 0; i < 10; i++){
            String label = "Label " + ((page_num * OFFSET) + i);
            list.add(label);
        }
        adapter.notifyDataSetChanged();
        mLockListView = false;
        gridView.setSelection(0);
    }

}
