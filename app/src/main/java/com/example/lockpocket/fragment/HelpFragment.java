package com.example.lockpocket.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;


import com.example.lockpocket.HelpDetail;
import com.example.lockpocket.R;


public class HelpFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_help, container, false);
        LinearLayout linearLayoutIntroduction = view.findViewById(R.id.introduction);
        LinearLayout linearLayoutCommunity_share = view.findViewById(R.id.community_share);
        LinearLayout linearLayoutEdit = view.findViewById(R.id.edit);
        LinearLayout linearLayoutCommunity_down = view.findViewById(R.id.community_download);
        LinearLayout linearLayoutAccount = view.findViewById(R.id.account);

        linearLayoutIntroduction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tv_appUsebutton();
            }
        });
        linearLayoutEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tv_makeLockbutton();
            }
        });
        linearLayoutCommunity_share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tv_shareLockbutton();
            }
        });
        linearLayoutCommunity_down.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tv_downLockbutton();
            }
        });
        linearLayoutAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tv_editProfilebuton();
            }
        });
        return view;
    }
    public void tv_appUsebutton(){
        Intent intent = new Intent(getActivity(), HelpDetail.class);
        intent.putExtra("num", 1);
        startActivity(intent);
    }
    public void tv_makeLockbutton(){
        Intent intent = new Intent(getActivity(), HelpDetail.class);
        intent.putExtra("num", 2);
        startActivity(intent);
    }
    public void tv_shareLockbutton(){
        Intent intent = new Intent(getActivity(), HelpDetail.class);
        intent.putExtra("num", 3);
        startActivity(intent);
    }
    public void tv_downLockbutton(){
        Intent intent = new Intent(getActivity(), HelpDetail.class);
        intent.putExtra("num", 4);
        startActivity(intent);
    }
    public void tv_editProfilebuton(){
        Intent intent = new Intent(getActivity(), HelpDetail.class);
        intent.putExtra("num", 5);
        startActivity(intent);
    }

}
