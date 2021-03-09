package com.example.x_smartcity_s.Adapter;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.x_smartcity_s.R;
import com.example.x_smartcity_s.bean.BusList;
import com.example.x_smartcity_s.bean.BusStationById;
import com.example.x_smartcity_s.net.OKHttpTo;
import com.example.x_smartcity_s.net.OkHttpLo;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Fragment_Service_smartbus_msg_2 extends Fragment {

    private ImageView titleBack;
    private TextView titleTitle;
    private TextView txtQizhi;
    private EditText edName;
    private EditText edTel;
    private Spinner spinnerShangche;
    private Spinner spinnerXiache;
    private Button btnXiayibu;
    private BusList busList;
    private String desc;
    private String s;
    private List<BusStationById> busStationByIds;
    private List<String> strings;
    private Service_Smartbus_msg_2_adapter adapter;
    private String dizhi1="",dizhi2="";

    public Fragment_Service_smartbus_msg_2(BusList busList, String desc) {
        this.busList = busList;
        this.desc = desc;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.fragment_service_smartbus_msg_2, container, false);
        initView(view);
        titleTitle.setText("乘车信息");
        s = busList.getStartSite()+"-----"+busList.getEndSite();

        txtQizhi.setText(busList.getStartSite()+"-----"+busList.getEndSite());

        getOkHttp();

        sip();



        btn();


        return view;
    }

    private void btn() {
        btnXiayibu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = edName.getText().toString();
                String tel = edTel.getText().toString();
                if (name.equals("")){
                    Toast.makeText(getContext(),"请输入姓名",Toast.LENGTH_SHORT).show();
                }else if (tel.equals("")){
                    Toast.makeText(getContext(),"请输入手机号",Toast.LENGTH_SHORT).show();
                }else if (dizhi1.equals("")){
                    Toast.makeText(getContext(),"请输入上车地点",Toast.LENGTH_SHORT).show();
                }else if (dizhi2.equals("")){
                    Toast.makeText(getContext(),"请输入下车地点",Toast.LENGTH_SHORT).show();
                }else {
                    getFragment(new Fragment_Service_smartbus_msg_3(s,name,tel,dizhi1,dizhi2,desc,busList));
                }
            }
        });

        titleBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragment(new Fragment_Service_smartbus_msg_1(busList));
            }
        });



    }


    private void sip() {
        spinnerXiache.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                dizhi2 = strings.get(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        spinnerShangche.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                dizhi1 = strings.get(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }

    private void getOkHttp() {
        if (busStationByIds == null){
            busStationByIds = new ArrayList<>();
        }else {
            busStationByIds.clear();
        }
        new OKHttpTo()
                .setUrl("busStationById")
                .setJSONObject("busid",busList.getBusid())
                .setOkHttpLo(new OkHttpLo() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        busStationByIds.addAll(new Gson().fromJson(jsonObject.optJSONArray("ROWS_DETAIL").toString(),
                                new TypeToken<List<BusStationById>>(){}.getType()));
                        getdata();
                    }

                    @Override
                    public void onFailure(IOException obj) {

                    }
                }).start();
        
        
    }
    private void getdata() {
        strings = new ArrayList<>();
        for (int i=0;i<busStationByIds.size();i++){
            BusStationById byId = busStationByIds.get(i);
            strings.add(byId.getSiteName());
        }

        adapter = new Service_Smartbus_msg_2_adapter(strings,getContext());
        spinnerShangche.setAdapter(adapter);
        spinnerXiache.setAdapter(adapter);

    }


    private void getFragment(Fragment fragment) {
        FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.fragment_home,fragment).commit();
    }

    private void initView(View view) {
        titleBack = view.findViewById(R.id.title_back);
        titleTitle = view.findViewById(R.id.title_title);
        txtQizhi = view.findViewById(R.id.txt_qizhi);
        edName = view.findViewById(R.id.ed_name);
        edTel = view.findViewById(R.id.ed_tel);
        spinnerShangche = view.findViewById(R.id.spinner_shangche);
        spinnerXiache = view.findViewById(R.id.spinner_xiache);
        btnXiayibu = view.findViewById(R.id.btn_xiayibu);
    }
}
