package com.example.x_smartcity_s.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.x_smartcity_s.Fragment.Page.Fragment_5;
import com.example.x_smartcity_s.R;

/**
 * author : 关鑫
 * Github : XGKerwin
 * date   : 2021/2/10  16:37
 */
public class Fragment_Settings extends Fragment {


    private EditText edIp;
    private EditText edDuankou;
    private Button btnAlter;
    private Button btnSave;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.fragment_settings, container, false);
        initView(view);

        edIp.setFocusable(false);
        edDuankou.setFocusable(false);
        edIp.setFocusableInTouchMode(false);
        edDuankou.setFocusableInTouchMode(false);

        btnAlter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edIp.setFocusableInTouchMode(true);
                edDuankou.setFocusableInTouchMode(true);
                edIp.setFocusable(true);
                edDuankou.setFocusable(true);
                edIp.requestFocus();
            }
        });


        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String ip = edIp.getText().toString();
                String duankou = edDuankou.getText().toString();
                if (ip.equals("")){
                    Toast.makeText(getContext(),"ip地址不能为空",Toast.LENGTH_SHORT).show();
                }else if (duankou.equals("")){
                    Toast.makeText(getContext(),"端口号不能为空",Toast.LENGTH_SHORT).show();
                }else {
                    edIp.setFocusableInTouchMode(false);
                    edDuankou.setFocusableInTouchMode(false);
                    edIp.setFocusable(false);
                    edDuankou.setFocusable(false);
                    Toast.makeText(getContext(),"保存成功",Toast.LENGTH_SHORT).show();
                    getFragment(new Fragment_5());
                }
            }
        });

        return view;
    }

    private void getFragment(Fragment fragment) {
        FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.fragment,fragment).commit();
    }

    private void initView(View view) {
        edIp = view.findViewById(R.id.ed_ip);
        edDuankou = view.findViewById(R.id.ed_duankou);
        btnAlter = view.findViewById(R.id.btn_alter);
        btnSave = view.findViewById(R.id.btn_save);
    }
}
