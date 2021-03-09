package com.example.x_smartcity_s.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.x_smartcity_s.Adapter.Service_adapter;
import com.example.x_smartcity_s.Fragment.Service.Fragment_Service_Outpatient;
import com.example.x_smartcity_s.Fragment.Service.Fragment_Service_Parking;
import com.example.x_smartcity_s.Fragment.Service.Fragment_Service_Smartbus;
import com.example.x_smartcity_s.Fragment.Service.Fragment_Service_Violation;
import com.example.x_smartcity_s.Fragment.Service.Fragment_Service_event;
import com.example.x_smartcity_s.Fragment.Service.Fragment_Service_metro;
import com.example.x_smartcity_s.OnClickItem;
import com.example.x_smartcity_s.R;
import com.example.x_smartcity_s.bean.GetServiceByType;
import com.example.x_smartcity_s.net.OKHttpTo;
import com.example.x_smartcity_s.net.OkHttpLo;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * author : 关鑫
 * Github : XGKerwin
 * date   : 2021/2/10  17:08
 */
public class Fragment_Service extends Fragment {

    private EditText edSeek;
    private LinearLayout btnSousuo;
    private TextView service;
    private TextView pension;
    private TextView tourism;
    private TextView huanbao;
    private TextView treatment;
    private RecyclerView recyclerview;
    private List<GetServiceByType> typeList;
    private Service_adapter adapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.fragment_service, container, false);
        initView(view);

        getServiceByType("智慧服务");

        btn();

        return view;
    }

    private void btn() {

        service.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getServiceByType("智慧服务");
            }
        });

        pension.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getServiceByType("智慧养老");
            }
        });

        tourism.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getServiceByType("智慧旅游");
            }
        });

        huanbao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getServiceByType("智慧环保");
            }
        });

        treatment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getServiceByType("智慧医疗");
            }
        });

    }

    private void getServiceByType(String s) {
        if (typeList == null){
            typeList = new ArrayList<>();
        }else {
            typeList.clear();
        }
        new OKHttpTo()
                .setUrl("getServiceByType")
                .setJSONObject("serviceType",s)
                .setOkHttpLo(new OkHttpLo() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        typeList.addAll(new Gson().fromJson(jsonObject.optJSONArray("ROWS_DETAIL").toString(),
                                new TypeToken<List<GetServiceByType>>(){}.getType()));

                        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(),3);
                        recyclerview.setLayoutManager(gridLayoutManager);

                        if (adapter == null){
                            adapter = new Service_adapter(typeList);
                            recyclerview.setAdapter(adapter);
                        }else {
                            adapter.notifyDataSetChanged();
                        }
                        adapter.setOnClickItem(new OnClickItem() {
                            @Override
                            public void onClick(int position, String name) {
                                if (name.equals("地铁查询")){
                                    getFragment(new Fragment_Service_metro());
                                }else if (name.equals("活动")){
                                    getFragment(new Fragment_Service_event());
                                }else if (name.equals("违章查询")){
                                    getFragment(new Fragment_Service_Violation());
                                }else if (name.equals("门诊预约")){
                                    getFragment(new Fragment_Service_Outpatient());
                                }else if (name.equals("预约养老")){
                                    getFragment(new Fragment_Service_Pension());
                                }else if (name.equals("停车场")){
                                    getFragment(new Fragment_Service_Parking());
                                }else if (name.equals("智慧巴士")){
                                    getFragment(new Fragment_Service_Smartbus());
                                }else if (name.equals("生活缴费")){
                                    getFragment(new Fragment_Service_Payment());
                                }
                            }
                        });
                    }

                    @Override
                    public void onFailure(IOException obj) {

                    }
                }).start();

    }

    private void getFragment(Fragment fragment) {
        FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.fragment_home,fragment).addToBackStack(null).commit();
    }

    private void initView(View view) {
        edSeek = view.findViewById(R.id.ed_seek);
        btnSousuo = view.findViewById(R.id.btn_sousuo);
        service = view.findViewById(R.id.service);
        pension = view.findViewById(R.id.pension);
        tourism = view.findViewById(R.id.tourism);
        huanbao = view.findViewById(R.id.huanbao);
        treatment = view.findViewById(R.id.treatment);
        recyclerview = view.findViewById(R.id.recyclerview);
    }
}
