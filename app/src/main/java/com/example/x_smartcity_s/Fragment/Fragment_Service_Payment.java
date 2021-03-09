package com.example.x_smartcity_s.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.x_smartcity_s.App;
import com.example.x_smartcity_s.R;

public class Fragment_Service_Payment extends Fragment {

    private LinearLayout btnFanhui;
    private LinearLayout btnShuifei;
    private LinearLayout btnDianfei;
    private LinearLayout btnHuhao;
    private LinearLayout btnZixun;
    private LinearLayout btnDiZidong;
    private LinearLayout btnDiHuhao;
    private String user;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.fragment_service_payment, container, false);
        initView(view);

        user = App.getUserida();

        btn();

        return view;
    }

    private void btn() {

        btnZixun.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragment(new Fragment_Service_Payment_News());
            }
        });

        btnHuhao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (user == null){
                    Toast.makeText(getContext(),"请登录",Toast.LENGTH_SHORT).show();
                }else {
                    getFragment(new Fragment_Service_Payment_Account());
                }
            }
        });

        btnShuifei.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (user == null){
                    Toast.makeText(getContext(),"请登录",Toast.LENGTH_SHORT).show();
                }else {
                    getFragment(new Fragment_Service_Payment_Cost(user,"水费"));
                }
            }
        });

        btnDianfei.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (user == null){
                    Toast.makeText(getContext(),"请登录",Toast.LENGTH_SHORT).show();
                }else {
                    getFragment(new Fragment_Service_Payment_Cost(user,"电费"));
                }
            }
        });

        btnFanhui.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragment(new Fragment_Service());
            }
        });

    }

    private void getFragment(Fragment fragment) {
        FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.fragment_home,fragment).commit();
    }

    private void initView(View view) {
        btnFanhui = view.findViewById(R.id.btn_fanhui);
        btnShuifei = view.findViewById(R.id.btn_shuifei);
        btnDianfei = view.findViewById(R.id.btn_dianfei);
        btnHuhao = view.findViewById(R.id.btn_huhao);
        btnZixun = view.findViewById(R.id.btn_zixun);
        btnDiZidong = view.findViewById(R.id.btn_di_zidong);
        btnDiHuhao = view.findViewById(R.id.btn_di_huhao);
    }
}
