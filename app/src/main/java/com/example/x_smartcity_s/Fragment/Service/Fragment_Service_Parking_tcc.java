package com.example.x_smartcity_s.Fragment.Service;

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
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.x_smartcity_s.Adapter.Service_Parking_tcc_adapter;
import com.example.x_smartcity_s.R;
import com.example.x_smartcity_s.bean.GetParkInfor;
import com.example.x_smartcity_s.net.OKHttpTo;
import com.example.x_smartcity_s.net.OkHttpLo;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * author : 关鑫
 * Github : XGKerwin
 * date   : 2021/2/23  15:23
 */
public class Fragment_Service_Parking_tcc extends Fragment {

    private ImageView titleBack;
    private TextView titleTitle;
    private RecyclerView recyclerview;
    private TextView txtGengduo;
    private List<GetParkInfor> getParkInfors;
    private Service_Parking_tcc_adapter adapter;
    private int i;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.fragment_service_parking_tcc, container, false);
        initView(view);
        titleTitle.setText("附近停车场");

        getParkInfor();

        titleBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragment(new Fragment_Service_Parking());
            }
        });

        btn();

        return view;
    }

    private void btn() {
        txtGengduo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
                recyclerview.setLayoutManager(linearLayoutManager);

                adapter = new Service_Parking_tcc_adapter(getParkInfors,getParkInfors.size(),getActivity());
                recyclerview.setAdapter(adapter);

            }
        });
    }

    private void getParkInfor() {
        if (getParkInfors == null) {
            getParkInfors = new ArrayList<>();
        } else {
            getParkInfors.clear();
        }
        new OKHttpTo()
                .setUrl("getParkInfor")
                .setOkHttpLo(new OkHttpLo() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        getParkInfors.addAll(new Gson().fromJson(jsonObject.optJSONArray("ROWS_DETAIL").toString(),
                                new TypeToken<List<GetParkInfor>>() {
                                }.getType()));

                        Collections.sort(getParkInfors, new Comparator<GetParkInfor>() {
                            @Override
                            public int compare(GetParkInfor o1, GetParkInfor o2) {
                                return o1.getDistance() - o2.getDistance();
                            }
                        });

                        i = getParkInfors.size();

                        if (adapter == null) {
                            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
                            recyclerview.setLayoutManager(linearLayoutManager);

                            if (i<=5){
                                adapter = new Service_Parking_tcc_adapter(getParkInfors,getParkInfors.size(),getActivity());
                                recyclerview.setAdapter(adapter);
                            }else {
                                adapter = new Service_Parking_tcc_adapter(getParkInfors,5,getActivity());
                                recyclerview.setAdapter(adapter);
                            }

                        } else {
                            adapter.notifyDataSetChanged();
                        }


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
        recyclerview = view.findViewById(R.id.recyclerview);
        txtGengduo = view.findViewById(R.id.txt_gengduo);
    }
}
