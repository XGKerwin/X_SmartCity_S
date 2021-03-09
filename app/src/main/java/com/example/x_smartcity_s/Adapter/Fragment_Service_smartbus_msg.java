package com.example.x_smartcity_s.Adapter;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.x_smartcity_s.Fragment.Service.Fragment_Service_Smartbus;
import com.example.x_smartcity_s.R;
import com.example.x_smartcity_s.bean.BusList;

public class Fragment_Service_smartbus_msg extends Fragment {

    private BusList busList;
    private ImageView titleBack;
    private TextView titleTitle;
    private TextView qizhi;
    private ImageView imag;
    private TextView piaojia;
    private TextView licheng;
    private Button xiayibu;

    public Fragment_Service_smartbus_msg(BusList list) {
        this.busList = list;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.fragment_service_smartbus_msg, container, false);
        initView(view);

        titleTitle.setText(busList.getBusid()+"路站点");

        qizhi.setText(busList.getStartSite()+"-------"+busList.getEndSite());
        piaojia.setText("票价："+busList.getPrice()+"元");
        licheng.setText("里程："+busList.getMileage()+"KM");
        int s = busList.getBusid();
        if (s%2==0){
            imag.setImageResource(R.drawable.ditu);
        }else {
            imag.setImageResource(R.drawable.ditu2);
        }

        xiayibu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragment(new Fragment_Service_smartbus_msg_1(busList));
            }
        });

        titleBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragment(new Fragment_Service_Smartbus());
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
        qizhi = view.findViewById(R.id.qizhi);
        imag = view.findViewById(R.id.imag);
        piaojia = view.findViewById(R.id.piaojia);
        licheng = view.findViewById(R.id.licheng);
        xiayibu = view.findViewById(R.id.xiayibu);
    }
}
