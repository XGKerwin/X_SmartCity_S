package com.example.x_smartcity_s.Fragment.Service;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.x_smartcity_s.Adapter.Service_Outpatient_msg_registered_Department_Doctors_adapter;
import com.example.x_smartcity_s.App;
import com.example.x_smartcity_s.OnClickItem;
import com.example.x_smartcity_s.R;
import com.example.x_smartcity_s.bean.DoctorList;
import com.example.x_smartcity_s.bean.HospitalList;
import com.example.x_smartcity_s.net.OKHttpTo;
import com.example.x_smartcity_s.net.OkHttpLo;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONObject;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * author : 关鑫
 * Github : XGKerwin
 * date   : 2021/2/23  13:48
 */
public class Fragment_Service_Outpatient_msg_registered_Department_Doctors extends Fragment {

    private HospitalList hospitalList;
    private ImageView titleBack;
    private TextView titleTitle;
    private TextView txtPutong;
    private TextView txtZhuanjia;
    private TextView txtZanwu;
    private RecyclerView recyclerview;
    private List<DoctorList> doctorLists,doctors;
    private String name;
    private String sex;
    private String sfz;
    private String tel;
    private String csrq;
    private String dizhi;
    private Service_Outpatient_msg_registered_Department_Doctors_adapter adapter;

    public Fragment_Service_Outpatient_msg_registered_Department_Doctors(HospitalList hospitalList) {
        this.hospitalList = hospitalList;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.fragment_service_outpatient_msg_registered_department_doctors, container, false);
        initView(view);
        titleTitle.setText("医生选择");
        titleBack.setVisibility(View.GONE);
        getOkHttp();
        btn();
        return view;
    }

    private void btn() {
        txtPutong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txtZanwu.setVisibility(View.GONE);
                recyclerview.setVisibility(View.VISIBLE);
            }
        });

        txtZhuanjia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                recyclerview.setVisibility(View.GONE);
                txtZanwu.setVisibility(View.VISIBLE);
            }
        });



    }

    private void getOkHttp() {
        if (doctorLists == null){
            doctorLists = new ArrayList<>();
        }else {
            doctorLists.clear();
        }
        new OKHttpTo()
                .setUrl("doctorList")
                .setOkHttpLo(new OkHttpLo() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        doctorLists.addAll(new Gson().fromJson(jsonObject.optJSONArray("ROWS_DETAIL").toString(),
                                new TypeToken<List<DoctorList>>(){}.getType()));
                        getData();
                    }

                    @Override
                    public void onFailure(IOException obj) {

                    }
                }).start();
    }

    private String time;
    private void getData() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd E");
        Date date = new Date(System.currentTimeMillis());
        String str = simpleDateFormat.format(date);
        time = str+"下午14：00";

        if (doctors == null){
            doctors = new ArrayList<>();
        }else {
            doctors.clear();
        }

        for (int i=0;i<doctorLists.size();i++){
            DoctorList list = doctorLists.get(i);
            if (list.getHospitalId().equals(hospitalList.getHospitalId())){
                doctors.add(list);
            }
        }
        if (adapter == null){
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
            recyclerview.setLayoutManager(linearLayoutManager);

            adapter = new Service_Outpatient_msg_registered_Department_Doctors_adapter(doctors,time,getActivity(),getContext());
            recyclerview.setAdapter(adapter);
        }else {
            adapter.notifyDataSetChanged();
        }

        adapter.setOnClickItem(new OnClickItem() {
            @Override
            public void onClick(int position, String name1) {
                name = App.getName();
                sex = App.getSex();
                sfz = App.getSfz();
                tel = App.getTel();
                csrq = App.getCsrq();
                dizhi = App.getDizhi();
                getcreateCase(doctors.get(position));
            }
        });

    }

    private void getcreateCase(DoctorList doctorList) {
        new OKHttpTo()
                .setUrl("createCase")
                .setJSONObject("name",name)
                .setJSONObject("sex",sex)
                .setJSONObject("ID",sfz)
                .setJSONObject("birthday",csrq)
                .setJSONObject("tel",tel)
                .setJSONObject("address",dizhi)
                .setOkHttpLo(new OkHttpLo() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        getappointment(doctorList);   //预约挂号
                    }

                    @Override
                    public void onFailure(IOException obj) {

                    }
                }).start();

    }

    private void getappointment(DoctorList doctorList) {
        /**
         * {"pid":"371402199902041133","name":"赵子涵","phone":"13505347777","doctorId":2,"appTime":"2020-10-2 周四 下午14：00"}
         */
        new OKHttpTo()
                .setUrl("appointment")
                .setJSONObject("pid",sfz)
                .setJSONObject("name",name)
                .setJSONObject("phone",tel)
                .setJSONObject("doctorId",doctorList.getDoctorId())
                .setJSONObject("appTime",time)
                .setOkHttpLo(new OkHttpLo() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        Toast.makeText(getContext(),"预约成功",Toast.LENGTH_SHORT).show();
                        getFragment(new Fragment_Service_Outpatient_msg(hospitalList));
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
        txtPutong = view.findViewById(R.id.txt_putong);
        txtZhuanjia = view.findViewById(R.id.txt_zhuanjia);
        txtZanwu = view.findViewById(R.id.txt_zanwu);
        recyclerview = view.findViewById(R.id.recyclerview);
    }
}
