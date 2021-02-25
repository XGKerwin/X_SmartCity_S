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
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.x_smartcity_s.Adapter.Service_Outpatient_msg_registered_Department_adapter;
import com.example.x_smartcity_s.R;
import com.example.x_smartcity_s.bean.DeptList;
import com.example.x_smartcity_s.bean.HospitalList;
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
 * date   : 2021/2/23  11:33
 */
public class Fragment_Service_Outpatient_msg_registered_Department extends Fragment {
    private ImageView titleBack;
    private TextView titleTitle;
    private RecyclerView recyclerview;
    private HospitalList hospitalList;
    private List<DeptList> deptLists;
    private Service_Outpatient_msg_registered_Department_adapter adapter;

    public Fragment_Service_Outpatient_msg_registered_Department(HospitalList list) {
        this.hospitalList = list;
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.fragment_service_outpatient_msg_registered_department, container, false);
        initView(view);
        titleTitle.setText("科室选择");
        titleBack.setVisibility(View.GONE);
        getOkHttp();

        return view;
    }

    private void getOkHttp() {
        if (deptLists == null){
            deptLists = new ArrayList<>();
        }else {
            deptLists.clear();
        }
        new OKHttpTo()
                .setUrl("deptList")
                .setJSONObject("hospitalId",hospitalList.getHospitalId())
                .setOkHttpLo(new OkHttpLo() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        deptLists.addAll(new Gson().fromJson(jsonObject.optJSONArray("ROWS_DETAIL").toString(),
                                new TypeToken<List<DeptList>>(){}.getType()));

                        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
                        recyclerview.setLayoutManager(linearLayoutManager);

                        adapter = new Service_Outpatient_msg_registered_Department_adapter(deptLists,getActivity(),hospitalList);
                        recyclerview.setAdapter(adapter);

                    }

                    @Override
                    public void onFailure(IOException obj) {

                    }
                }).start();

    }

    private void initView(View view) {
        titleBack = view.findViewById(R.id.title_back);
        titleTitle = view.findViewById(R.id.title_title);
        recyclerview = view.findViewById(R.id.recyclerview);
    }
}
