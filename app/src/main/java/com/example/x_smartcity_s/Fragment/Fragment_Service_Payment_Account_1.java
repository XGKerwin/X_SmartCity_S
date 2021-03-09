package com.example.x_smartcity_s.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.x_smartcity_s.App;
import com.example.x_smartcity_s.R;
import com.example.x_smartcity_s.net.OKHttpTo;
import com.example.x_smartcity_s.net.OkHttpLo;

import org.json.JSONObject;

import java.io.IOException;

public class Fragment_Service_Payment_Account_1 extends Fragment {

    private ImageView titleBack;
    private TextView titleTitle;
    private RadioButton rbMy;
    private RadioButton rbFm;
    private RadioButton rbPy;
    private RadioButton rbFd;
    private RadioButton rbQt;
    private EditText edZidingyi;
    private Button btnTijiao;
    private String user;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.fragment_service_payment_account_1, container, false);
        initView(view);
        titleTitle.setText("添加户号");
        user = App.getUserida();

        btnTijiao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getDaTa();

            }
        });

        titleBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragment(new Fragment_Service_Payment_Account());
            }
        });

        return view;
    }

    private void getDaTa() {
        String s = edZidingyi.getText().toString();
        if (rbMy.isChecked()){
            getOkHttp("我家");
        }else if (rbFm.isChecked()){
            getOkHttp("父母");
        }else if (rbPy.isChecked()){
            getOkHttp("朋友");
        }else if (rbFd.isChecked()){
            getOkHttp("房东");
        }else if (!s.equals("")){
            getOkHttp(s);
        }else {
            Toast.makeText(getContext(),"请选择",Toast.LENGTH_SHORT).show();
        }
    }

    private void getOkHttp(String s) {
        new OKHttpTo()
                .setUrl("setAccountGroup")
                .setJSONObject("userid",user)
                .setJSONObject("groupName",s)
                .setOkHttpLo(new OkHttpLo() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        Toast.makeText(getContext(),"提交成功",Toast.LENGTH_SHORT).show();
                        edZidingyi.setText("");
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
        rbMy = view.findViewById(R.id.rb_my);
        rbFm = view.findViewById(R.id.rb_fm);
        rbPy = view.findViewById(R.id.rb_py);
        rbFd = view.findViewById(R.id.rb_fd);
        rbQt = view.findViewById(R.id.rb_qt);
        edZidingyi = view.findViewById(R.id.ed_zidingyi);
        btnTijiao = view.findViewById(R.id.btn_tijiao);
    }
}
