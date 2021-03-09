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

import com.example.x_smartcity_s.Adapter.Service_Outpatient_adapter;
import com.example.x_smartcity_s.Fragment.Fragment_Service;
import com.example.x_smartcity_s.R;
import com.example.x_smartcity_s.bean.GetRankByHospitalId;
import com.example.x_smartcity_s.bean.HospitalList;
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
 * date   : 2021/2/23  10:55
 */

public class Fragment_Service_Outpatient extends Fragment {

    private ImageView titleBack;
    private TextView titleTitle;
    private RecyclerView recyclerview;
    private List<HospitalList> hospitalLists;
    private List<GetRankByHospitalId> rankByHospitalIds;
    private Service_Outpatient_adapter outpatient_adapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.fragment_service_outpatient, container, false);
        initView(view);

        titleTitle.setText("医院选择");

        gethospitalList();  //获取所有医院信息

        titleBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragment(new Fragment_Service());
            }
        });

        return view;
    }

    private void getFragment(Fragment fragment) {
        FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.fragment_home,fragment).commit();
    }

    private void gethospitalList() {
        if (hospitalLists == null){
            hospitalLists = new ArrayList<>();
        }else {
            hospitalLists.clear();
        }
        new OKHttpTo()
                .setUrl("hospitalList")
                .setOkHttpLo(new OkHttpLo() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        hospitalLists.addAll(new Gson().fromJson(jsonObject.optJSONArray("ROWS_DETAIL").toString(),
                                new TypeToken<List<HospitalList>>(){}.getType()));
                        getRating();
                    }

                    @Override
                    public void onFailure(IOException obj) {

                    }
                }).start();

    }

    private void getRating() {
        if (rankByHospitalIds == null){
            rankByHospitalIds = new ArrayList<>();
        }else {
            rankByHospitalIds.clear();
        }
        for (int i=1;i<=4;i++){
            new OKHttpTo()
                    .setUrl("getRankByHospitalId")
                    .setJSONObject("hospitalId",i)
                    .setOkHttpLo(new OkHttpLo() {
                        @Override
                        public void onResponse(JSONObject jsonObject) {
                            GetRankByHospitalId getRankByHospitalId = new Gson().fromJson(jsonObject.optJSONArray("ROWS_DETAIL").optJSONObject(0).toString(),
                                    GetRankByHospitalId.class);
                            rankByHospitalIds.add(getRankByHospitalId);

                            if (rankByHospitalIds.size()==4){

                                Collections.sort(rankByHospitalIds, new Comparator<GetRankByHospitalId>() {
                                    @Override
                                    public int compare(GetRankByHospitalId o1, GetRankByHospitalId o2) {
                                        return o1.getHospitalId() - o2.getHospitalId();
                                    }
                                });

                                if (outpatient_adapter == null){
                                    LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
                                    recyclerview.setLayoutManager(linearLayoutManager);
                                    outpatient_adapter = new Service_Outpatient_adapter(hospitalLists,rankByHospitalIds,getActivity());
                                }else {
                                    outpatient_adapter.notifyDataSetChanged();
                                }
                                recyclerview.setAdapter(outpatient_adapter);
                            }
                        }

                        @Override
                        public void onFailure(IOException obj) {

                        }
                    }).start();
        }

    }

    private void initView(View view) {
        titleBack = view.findViewById(R.id.title_back);
        titleTitle = view.findViewById(R.id.title_title);
        recyclerview = view.findViewById(R.id.recyclerview);
    }
}
