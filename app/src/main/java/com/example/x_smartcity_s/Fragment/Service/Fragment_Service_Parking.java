package com.example.x_smartcity_s.Fragment.Service;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.x_smartcity_s.Fragment.Fragment_Service;
import com.example.x_smartcity_s.R;

/**
 * author : 关鑫
 * Github : XGKerwin
 * date   : 2021/2/23  15:15
 */
public class Fragment_Service_Parking extends Fragment {

    private ImageView titleBack;
    private TextView titleTitle;
    private LinearLayout btnlist;
    private LinearLayout btnTcc;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.fragment_service_parking, container, false);
        initView(view);
        titleTitle.setText("停车场");
        btn();



        return view;
    }

    private void btn() {
        btnTcc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragment(new Fragment_Service_Parking_tcc());
            }
        });

        btnlist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragment(new Fragment_Service_Parking_list());
            }
        });

        titleBack.setOnClickListener(new View.OnClickListener() {
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
        titleBack = view.findViewById(R.id.title_back);
        titleTitle = view.findViewById(R.id.title_title);
        btnlist = view.findViewById(R.id.btn_list);
        btnTcc = view.findViewById(R.id.btn_tcc);
    }
}
