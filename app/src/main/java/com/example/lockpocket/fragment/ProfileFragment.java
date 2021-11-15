package com.example.lockpocket.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.lockpocket.R;

import org.jetbrains.annotations.NotNull;

public class ProfileFragment extends Fragment {
    View view;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_profile, container, false);

        View.OnClickListener onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch(v.getId()){
                    case R.id.pass_edit:
                        Toast.makeText(getContext(), "", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.signout_btn:
                        Toast.makeText(getContext(), "", Toast.LENGTH_SHORT).show();
                        break;
                }
            }
        };

        view.findViewById(R.id.pass_edit).setOnClickListener(onClickListener);
        view.findViewById(R.id.signout_btn).setOnClickListener(onClickListener);

        return view;
    }
}
