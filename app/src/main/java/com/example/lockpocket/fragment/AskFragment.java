package com.example.lockpocket.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.lockpocket.R;
import com.example.lockpocket.utils.PreferenceManager;

public class AskFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_ask, container, false);
        TextView tv_title = view.findViewById(R.id.ask_title);
        TextView tv_nickname = view.findViewById(R.id.ask_nickname);
        TextView tv_contents = view.findViewById(R.id.ask_contents);
        tv_nickname.setText(PreferenceManager.getString(getContext(), "Id"));

        Button reg_btn = view.findViewById(R.id.reg_btn);
        reg_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title = tv_title.getText().toString();
                String contents = tv_contents.getText().toString();

                if(title.length() < 8) {
                    Toast.makeText(getContext(), "제목은 8자 이상으로 작성해주세요", Toast.LENGTH_SHORT).show();
                } else if(contents.length() < 20) {
                    Toast.makeText(getContext(), "내용은 20자 이상으로 작성해주세요", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getContext(), "등록되었습니다", Toast.LENGTH_SHORT).show();
                    tv_title.setText("");
                    tv_contents.setText("");
                }
            }
        });

        return view;
    }
}
