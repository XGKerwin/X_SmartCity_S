package com.example.x_smartcity_s.Fragment.My;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.x_smartcity_s.App;
import com.example.x_smartcity_s.R;
import com.example.x_smartcity_s.bean.GetUserInfo;

import java.util.List;

/**
 * author : 关鑫
 * Github : XGKerwin
 * date   : 2021/2/21  9:46
 */
public class Fragment_My_UC extends Fragment {

    private TextView txtFanhui;
    private TextView txtXiugai;
    private TextView txtName;
    private TextView txtSex;
    private TextView txtTel;
    private TextView txtIdcard;
    private String userid;
    private List<GetUserInfo> getUserInfos;

    public Fragment_My_UC(List<GetUserInfo> getUserInfoList) {
        this.getUserInfos = getUserInfoList;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.fragment_my_uc, container, false);
        initView(view);
        btn();
        userid = App.getUserida();
        getOkHttp();
        return view;
    }

    private void getOkHttp() {
        txtName.setText(getUserInfos.get(0).getName());
        txtSex.setText(getUserInfos.get(0).getSex());
        txtIdcard.setText(getUserInfos.get(0).getId());
        txtTel.setText(getUserInfos.get(0).getPhone());
    }

    private void btn() {
        txtXiugai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragment(new Fragment_My_UC_alter(getUserInfos));
            }
        });

        txtFanhui.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragment(new Fragment_My());
            }
        });


    }

    private void getFragment(Fragment fragment) {
        FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.fragment_home,fragment).commit();
    }

    private void initView(View view) {
        txtFanhui = view.findViewById(R.id.txt_fanhui);
        txtXiugai = view.findViewById(R.id.txt_xiugai);
        txtName = view.findViewById(R.id.txt_name);
        txtSex = view.findViewById(R.id.txt_sex);
        txtTel = view.findViewById(R.id.txt_tel);
        txtIdcard = view.findViewById(R.id.txt_idcard);
    }
}
