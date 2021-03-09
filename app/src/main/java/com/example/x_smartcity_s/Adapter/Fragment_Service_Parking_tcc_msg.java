package com.example.x_smartcity_s.Adapter;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.x_smartcity_s.Fragment.Service.Fragment_Service_Parking_tcc;
import com.example.x_smartcity_s.R;
import com.example.x_smartcity_s.bean.GetParkInfor;

/**
 * author : 关鑫
 * Github : XGKerwin
 * date   : 2021/2/23  15:53
 */

public class Fragment_Service_Parking_tcc_msg extends Fragment {

    private GetParkInfor parkInfor;
    private ImageView titleBack;
    private TextView titleTitle;
    private TextView title1;
    private TextView jvli;
    private TextView zhuangtai;
    private TextView shoufei;
    private TextView shengyu;
    private TextView dizhi;

    public Fragment_Service_Parking_tcc_msg(GetParkInfor parkInfor) {
        this.parkInfor = parkInfor;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.fragment_service_parking_tcc_msg, container, false);
        initView(view);
        titleTitle.setText("停车场详情");

        title1.setText(parkInfor.getParkName());
        jvli.setText("距离：" + parkInfor.getDistance() + "M");
        shoufei.setText("收费标准：" + parkInfor.getRateRefer());
        shengyu.setText("剩余车位：" + parkInfor.getSpaceNum());
        dizhi.setText("地址：" + parkInfor.getAddress());

        switch (parkInfor.getIsOpen()) {
            case "Y":
                zhuangtai.setText("对外开放");
                break;
            case "N":
                zhuangtai.setText("未开放");
                break;
        }

        titleBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragment(new Fragment_Service_Parking_tcc());
            }
        });

        return view;
    }

    private void getFragment(Fragment fragment) {
        FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.fragment_home,fragment).commit();
    }

    private void initView(View view) {
        titleBack = view.findViewById(R.id.title_back);
        titleTitle = view.findViewById(R.id.title_title);
        title1 = view.findViewById(R.id.title1);
        jvli = view.findViewById(R.id.jvli);
        zhuangtai = view.findViewById(R.id.zhuangtai);
        shoufei = view.findViewById(R.id.shoufei);
        shengyu = view.findViewById(R.id.shengyu);
        dizhi = view.findViewById(R.id.dizhi);
    }
}
