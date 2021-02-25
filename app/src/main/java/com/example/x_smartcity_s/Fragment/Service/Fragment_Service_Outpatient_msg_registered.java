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

import com.example.x_smartcity_s.App;
import com.example.x_smartcity_s.R;
import com.example.x_smartcity_s.bean.GetUserInfo;
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
 * date   : 2021/2/23  11:23
 */
public class Fragment_Service_Outpatient_msg_registered extends Fragment {

    private HospitalList list;
    private ImageView titleBack;
    private TextView titleTitle;
    private EditText edName;
    private EditText edSex;
    private EditText edShenfenzheng;
    private EditText edChusheng;
    private EditText edTel;
    private EditText edDizhi;
    private Button btnTijiao;
    private ImageView xiayiye;
    private String user;
    private List<GetUserInfo> infos;

    public Fragment_Service_Outpatient_msg_registered(HospitalList list) {
        this.list = list;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.fragment_service_outpatient_msg_registered, container, false);
        initView(view);
        titleTitle.setText("就诊人信息");
        user = App.getUserida();

        getUser();

        btn();
        return view;
    }


    private String name,sex,shenfen,chusheng,tel,dizhi;
    private void btn() {
        btnTijiao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                name = edName.getText().toString();
                sex = edSex.getText().toString();
                shenfen = edShenfenzheng.getText().toString();
                tel = edTel.getText().toString();
                dizhi = edDizhi.getText().toString();
                chusheng = edChusheng.getText().toString();
                if (name.equals("")){
                    Toast.makeText(getContext(),"请输入姓名",Toast.LENGTH_SHORT).show();
                }else if (sex.equals("")){
                    Toast.makeText(getContext(),"请输入性别",Toast.LENGTH_SHORT).show();
                }else if (shenfen.equals("")){
                    Toast.makeText(getContext(),"请输入身份证",Toast.LENGTH_SHORT).show();
                }else if (chusheng.equals("")){
                    Toast.makeText(getContext(),"请输入出生日期",Toast.LENGTH_SHORT).show();
                }else if (tel.equals("")){
                    Toast.makeText(getContext(),"请输入手机号",Toast.LENGTH_SHORT).show();
                }else if (dizhi.equals("")){
                    Toast.makeText(getContext(),"请输入地址",Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(getContext(),"设置成功",Toast.LENGTH_SHORT).show();
                    xiayiye.setVisibility(View.VISIBLE);
                }
            }
        });


        xiayiye.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                App.setName(name);
                App.setSex(sex);
                App.setSfz(shenfen);
                App.setCsrq(chusheng);
                App.setTel(tel);
                App.setDizhi(dizhi);
                getFragment(new Fragment_Service_Outpatient_msg_registered_Department(list));     //进入科室
            }
        });

        titleBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragment(new Fragment_Service_Outpatient_msg(list));
            }
        });

    }




    private void getUser() {
        if (infos == null){
            infos = new ArrayList<>();
        }else {
            infos.clear();
        }
        new OKHttpTo()
                .setUrl("getUserInfo")
                .setJSONObject("userid",user)
                .setOkHttpLo(new OkHttpLo() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        infos.addAll(new Gson().fromJson(jsonObject.optJSONArray("ROWS_DETAIL").toString(),
                                new TypeToken<List<GetUserInfo>>(){}.getType()));
                        getshow();
                    }

                    @Override
                    public void onFailure(IOException obj) {

                    }
                }).start();

    }

    private void getshow() {
        edName.setText(infos.get(0).getName());
        edSex.setText(infos.get(0).getSex());
        edShenfenzheng.setText(infos.get(0).getId());
        edTel.setText(infos.get(0).getPhone());
    }

    private void getFragment(Fragment fragment) {
        FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.fragment_home,fragment).commit();

    }

    private void initView(View view) {
        titleBack = view.findViewById(R.id.title_back);
        titleTitle = view.findViewById(R.id.title_title);
        edName = view.findViewById(R.id.ed_name);
        edSex = view.findViewById(R.id.ed_sex);
        edShenfenzheng = view.findViewById(R.id.ed_shenfenzheng);
        edChusheng = view.findViewById(R.id.ed_chusheng);
        edTel = view.findViewById(R.id.ed_tel);
        edDizhi = view.findViewById(R.id.ed_dizhi);
        btnTijiao = view.findViewById(R.id.btn_tijiao);
        xiayiye = view.findViewById(R.id.xiayiye);
    }
}
