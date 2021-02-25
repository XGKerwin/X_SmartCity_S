package com.example.x_smartcity_s.Fragment.My;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.x_smartcity_s.App;
import com.example.x_smartcity_s.R;
import com.example.x_smartcity_s.bean.GetUserInfo;
import com.example.x_smartcity_s.net.OKHttpTo;
import com.example.x_smartcity_s.net.OkHttpLo;

import org.json.JSONObject;

import java.io.IOException;
import java.util.List;

/**
 * author : 关鑫
 * Github : XGKerwin
 * date   : 2021/2/21  9:57
 */
public class Fragment_My_UC_alter extends Fragment {
    private List<GetUserInfo> getUserInfos;
    private TextView txtFanhui;
    private TextView txtBaocun;
    private EditText edName;
    private EditText edSex;
    private EditText edTel;
    private EditText edId;
    private String userid;

    public Fragment_My_UC_alter(List<GetUserInfo> getUserInfos) {
        this.getUserInfos = getUserInfos;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.fragment_my_uc_dlter, container, false);
        initView(view);
        userid = App.getUserida();

        edName.setHint(getUserInfos.get(0).getName());
        edId.setHint(getUserInfos.get(0).getId());
        edSex.setHint(getUserInfos.get(0).getSex());
        edTel.setHint(getUserInfos.get(0).getPhone());
        edName.setText(getUserInfos.get(0).getName());
        edId.setText(getUserInfos.get(0).getId());
        edSex.setText(getUserInfos.get(0).getSex());
        edTel.setText(getUserInfos.get(0).getPhone());

        btn();

        return view;
    }

    private void btn() {
        txtFanhui.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragment(new Fragment_My_UC(getUserInfos));
            }
        });

        txtBaocun.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = edName.getText().toString().trim();
                String sex = edSex.getText().toString().trim();
                String tel = edTel.getText().toString().trim();
                String id = edId.getText().toString().trim();
                if (name.equals("")){
                    Toast.makeText(getContext(),"姓名不能为空",Toast.LENGTH_SHORT).show();
                }else if (sex.equals("")){
                    Toast.makeText(getContext(),"性别不能为空",Toast.LENGTH_SHORT).show();
                }else if (tel.equals("")){
                    Toast.makeText(getContext(),"手机号不能为空",Toast.LENGTH_SHORT).show();
                }else if (id.equals("")){
                    Toast.makeText(getContext(),"身份证号不能为空",Toast.LENGTH_SHORT).show();
                }else {
                    setUserInfo(name,sex,tel,id);
                }
            }
        });
    }

    private void setUserInfo(String name, String sex, String tel, String id) {
        new OKHttpTo()
                .setUrl("setUserInfo")
                .setJSONObject("userid",userid)
                .setJSONObject("name",name)
                .setJSONObject("avatar","")
                .setJSONObject("gender",sex)
                .setJSONObject("phone",tel)
                .setJSONObject("id",id)
                .setOkHttpLo(new OkHttpLo() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        Toast.makeText(getContext(),"修改成功",Toast.LENGTH_SHORT).show();
                        getFragment(new Fragment_My_UC(getUserInfos));
                    }

                    @Override
                    public void onFailure(IOException obj) {
                        Toast.makeText(getContext(),"修改失败",Toast.LENGTH_SHORT).show();
                    }
                }).start();

    }

    private void getFragment(Fragment fragment) {
        FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.fragment_home,fragment).commit();
    }

    private void initView(View view) {
        txtFanhui = view.findViewById(R.id.txt_fanhui);
        txtBaocun = view.findViewById(R.id.txt_baocun);
        edName = view.findViewById(R.id.ed_name);
        edSex = view.findViewById(R.id.ed_sex);
        edTel = view.findViewById(R.id.ed_tel);
        edId = view.findViewById(R.id.ed_id);
    }

}
