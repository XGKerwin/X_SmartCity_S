package com.example.x_smartcity_s.Fragment.Service;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.x_smartcity_s.Adapter.Service_smartbus_wo_adapter;
import com.example.x_smartcity_s.R;
import com.example.x_smartcity_s.bean.GetOrderBus;
import com.example.x_smartcity_s.net.OKHttpTo;
import com.example.x_smartcity_s.net.OkHttpLo;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Fragment_Service_smartbus_wo extends Fragment {

    private ImageView titleBack;
    private TextView titleTitle;
    private EditText edName;
    private Button btnChazhao;
    private TextView btnYizhifu;
    private TextView btnWeizhifu;
    private RecyclerView recyclerview;
    private List<GetOrderBus> getOrderBuses, buses;
    private Service_smartbus_wo_adapter adapter;
    private String abc;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.fragment_service_smartbus_wo, container, false);
        initView(view);
        titleTitle.setText("我的订单");

        getOkHttp(abc);

        btn();


        return view;
    }

    private void btn() {

        btnWeizhifu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getData("N");
            }
        });

        btnYizhifu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getData("Y");
            }
        });

        titleBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragment(new Fragment_Service_Smartbus());
            }
        });

        btnChazhao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s = edName.getText().toString();
                if (s.equals("")){
                    Toast.makeText(getContext(),"请输入姓名",Toast.LENGTH_SHORT).show();
                }else {
                    getOkHttp(s);
                }
            }
        });

    }

    private void getFragment(Fragment fragment) {
        FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.fragment_home,fragment).commit();
    }

    private void getOkHttp(String abc) {
        if (getOrderBuses == null) {
            getOrderBuses = new ArrayList<>();
        } else {
            getOrderBuses.clear();
        }
        new OKHttpTo()
                .setUrl("getOrderBus")
                .setJSONObject("name", abc)
                .setOkHttpLo(new OkHttpLo() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        getOrderBuses.addAll(new Gson().fromJson(jsonObject.optJSONArray("ROWS_DETAIL").toString(),
                                new TypeToken<List<GetOrderBus>>() {
                                }.getType()));
                        getData("Y");
                    }

                    @Override
                    public void onFailure(IOException obj) {

                    }
                }).start();


    }

    private void getData(String y) {
        if (buses == null) {
            buses = new ArrayList<>();
        } else {
            buses.clear();
        }

        for (int i = 0; i < getOrderBuses.size(); i++) {
            GetOrderBus bus = getOrderBuses.get(i);
            if (bus.getIsPay().equals(y)) {
                buses.add(bus);
            }
        }
        if (adapter == null) {
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
            recyclerview.setLayoutManager(linearLayoutManager);

            adapter = new Service_smartbus_wo_adapter(buses);
            recyclerview.setAdapter(adapter);
        } else {
            adapter.notifyDataSetChanged();
        }

    }

    private void initView(View view) {
        titleBack = view.findViewById(R.id.title_back);
        titleTitle = view.findViewById(R.id.title_title);
        edName = view.findViewById(R.id.ed_name);
        btnChazhao = view.findViewById(R.id.btn_chazhao);
        btnYizhifu = view.findViewById(R.id.btn_yizhifu);
        btnWeizhifu = view.findViewById(R.id.btn_weizhifu);
        recyclerview = view.findViewById(R.id.recyclerview);
    }
}
