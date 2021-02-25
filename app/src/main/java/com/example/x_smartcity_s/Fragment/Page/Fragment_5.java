package com.example.x_smartcity_s.Fragment.Page;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.x_smartcity_s.Fragment.Fragment_Settings;
import com.example.x_smartcity_s.Fragment.Fragment_home;
import com.example.x_smartcity_s.R;

/**
 * author : 关鑫
 * Github : XGKerwin
 * date   : 2021/2/10  16:32
 */
public class Fragment_5 extends Fragment {

    private Button btnSettings;
    private Button btnStart;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.fragment_5, container, false);
        initView(view);

        btnSettings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragment(new Fragment_Settings());
            }
        });

        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragment(new Fragment_home());
            }
        });


        return view;
    }

    private void getFragment(Fragment fragment) {
        FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.fragment,fragment).commit();
    }

    private void initView(View view) {
        btnSettings = view.findViewById(R.id.btn_settings);
        btnStart = view.findViewById(R.id.btn_start);
    }
}
