package com.example.x_smartcity_s.Adapter;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.x_smartcity_s.Fragment.Service.Fragment_Service_Smartbus;
import com.example.x_smartcity_s.R;
import com.example.x_smartcity_s.bean.BusList;
import com.example.x_smartcity_s.net.OKHttpTo;
import com.example.x_smartcity_s.net.OkHttpLo;

import org.json.JSONObject;

import java.io.IOException;

public class Fragment_Service_smartbus_msg_3 extends Fragment {


    private String s;
    private String name, tel, dizhi1, dizhi2, desc;
    private BusList busList;
    private TextView dizhi;
    private TextView shangche;
    private TextView xiache;
    private TextView shijian;
    private Button btnTijiao;
    private TextView txtname;
    private TextView txttel;
    private ImageView titleBack;
    private TextView titleTitle;

    public Fragment_Service_smartbus_msg_3(String s, String name, String tel, String dizhi1, String dizhi2, String desc, BusList busList) {
        this.busList = busList;
        this.name = name;
        this.tel = tel;
        this.s = s;
        this.dizhi1 = dizhi1;
        this.dizhi2 = dizhi2;
        this.desc = desc;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.fragment_service_smartbus_msg_3, container, false);
        initView(view);



        titleTitle.setText("提交订单");
        dizhi.setText(s);
        shangche.setText("上车："+dizhi1);
        xiache.setText("下车："+dizhi2);
        shijian.setText("乘车时间：" + desc);
        txtname.setText("乘客姓名："+name);
        txttel.setText("手机号："+tel);


        btnTijiao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getOkHttp();
            }
        });


        titleBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragment(new Fragment_Service_smartbus_msg_2(busList,desc));
            }
        });



        return view;
    }

    private void getOkHttp() {
        new OKHttpTo()
                .setUrl("setOrderBus")
                .setJSONObject("busid",busList.getBusid())
                .setJSONObject("name",name)
                .setJSONObject("phone",tel)
                .setJSONObject("upsite",dizhi1)
                .setJSONObject("downsite",dizhi2)
                .setJSONObject("traveltime",desc)
                .setJSONObject("isPay","N")
                .setOkHttpLo(new OkHttpLo() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        Toast.makeText(getContext(),"提交成功",Toast.LENGTH_SHORT).show();
                        getFragment(new Fragment_Service_Smartbus());
                    }

                    @Override
                    public void onFailure(IOException obj) {

                    }
                }).start();

    }

    private void getFragment(Fragment fragment) {
        FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.fragment_home,fragment).commit();
    }

    private void initView(View view) {
        titleBack = view.findViewById(R.id.title_back);
        titleTitle = view.findViewById(R.id.title_title);
        dizhi = view.findViewById(R.id.dizhi);
        txtname = view.findViewById(R.id.txtname);
        txttel = view.findViewById(R.id.txttel);
        shangche = view.findViewById(R.id.shangche);
        xiache = view.findViewById(R.id.xiache);
        shijian = view.findViewById(R.id.shijian);
        btnTijiao = view.findViewById(R.id.btn_tijiao);
    }
}
