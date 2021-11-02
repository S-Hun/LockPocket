package com.example.lockpocket;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.lakue.pagingbutton.LakuePagingButton;
import com.lakue.pagingbutton.OnPageSelectListener;

import java.util.ArrayList;
import java.util.List;

public class ListTest extends AppCompatActivity {

    private GridView gridView;                      // GridView 뷰
    private boolean lastItemVisibleFlag = false;    // 리스트 스크롤이 마지막 셀(맨 바닥)로 이동했는지 체크할 변수
    private List<String> list;                      // String 데이터를 담고있는 리스트
    private ListViewAdapter adapter;                // 리스트뷰의 아답터
    private int page = 0;                           // 페이징변수. 초기 값은 0 이다.
    private final int OFFSET = 20;                  // 한 페이지마다 로드할 데이터 갯수.
    private boolean mLockListView = false;          // 데이터 불러올때 중복안되게 하기위한 변수
    LakuePagingButton lpb_buttonlist;
    int pages = 1;
    int max_page = 30;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.listview_paging);

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
                Toast.makeText(ListTest.this, ""+now_page, Toast.LENGTH_SHORT).show();
                getItem(now_page);
            }

            @Override
            public void onPageCenter(int now_page) {
                Toast.makeText(ListTest.this, ""+now_page, Toast.LENGTH_SHORT).show();
                getItem(now_page);
                //  lpb_buttonlist.addBottomPageButton(max_page,page);
            }

            //NextButton Click
            @Override
            public void onPageNext(int now_page) {
                Toast.makeText(ListTest.this, ""+now_page, Toast.LENGTH_SHORT).show();
                lpb_buttonlist.addBottomPageButton(max_page,now_page);
                getItem(now_page);
            }
        });
        getItem(0);
    }





    private void getItem(int page_num){

        // 리스트에 다음 데이터를 입력할 동안에 이 메소드가 또 호출되지 않도록 mLockListView 를 true로 설정한다.
        mLockListView = true;

        // 다음 20개의 데이터를 불러와서 리스트에 저장한다.
        list.clear();
        for(int i = 0; i < 20; i++){
            String label = "Label " + ((page_num * OFFSET) + i);
            list.add(label);
        }
        adapter.notifyDataSetChanged();
        mLockListView = false;
        gridView.setSelection(0);
    }
}