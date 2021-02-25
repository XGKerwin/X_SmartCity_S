package com.example.x_smartcity_s.Activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.x_smartcity_s.App;
import com.example.x_smartcity_s.R;
import com.example.x_smartcity_s.bean.GetUsers;
import com.example.x_smartcity_s.net.OKHttpTo;
import com.example.x_smartcity_s.net.OkHttpLo;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class UserActivity extends AppCompatActivity {

    private ImageView titleBack;
    private TextView titleTitle;
    private EditText edUser;
    private EditText edPass;
    private Button btn_login;
    private List<GetUsers> getUsers;
    private String user;
    private String pass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);
        initView();
        titleTitle.setText("登录");

        btn();

    }

    private void btn() {
        titleBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                user = edUser.getText().toString().trim();
                pass = edPass.getText().toString().trim();
                if (user.equals("")){
                    Toast.makeText(UserActivity.this,"请输入用户名",Toast.LENGTH_SHORT).show();
                }else if (pass.equals("")){
                    Toast.makeText(UserActivity.this,"请输入密码",Toast.LENGTH_SHORT).show();
                }else {
                    getOkHttp();
                }

            }
        });

    }

    private void getOkHttp() {
        if (getUsers == null){
            getUsers = new ArrayList<>();
        }else {
            getUsers.clear();
        }
        new OKHttpTo()
                .setUrl("getUsers")
                .setOkHttpLo(new OkHttpLo() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        getUsers.addAll(new Gson().fromJson(jsonObject.optJSONArray("ROWS_DETAIL").toString(),
                                new TypeToken<List<GetUsers>>(){}.getType()));

                        for (int i=0;i<getUsers.size();i++){
                            GetUsers users = getUsers.get(i);
                            String suser = users.getUsername();
                            if (suser.equals(user)){
                                getOKhttppass(users.getUserid());
                            }else {

                            }
                        }

                    }

                    @Override
                    public void onFailure(IOException obj) {

                    }
                }).start();
    }

    private void getOKhttppass(String userid) {
        new OKHttpTo()
                .setUrl("getPwd")
                .setJSONObject("userid",userid)
                .setOkHttpLo(new OkHttpLo() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        String pwd = jsonObject.optString("password");
                        if (pwd.equals(pass)){
                            Toast.makeText(UserActivity.this,"登录成功",Toast.LENGTH_SHORT).show();
                            App.setUserida(userid);
                            finish();
                        }else {
                            Toast.makeText(UserActivity.this,"登录失败",Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(IOException obj) {

                    }
                }).start();
    }

    private void initView() {
        titleBack = findViewById(R.id.title_back);
        titleTitle = findViewById(R.id.title_title);
        edUser = findViewById(R.id.ed_user);
        edPass = findViewById(R.id.ed_pass);
        btn_login = findViewById(R.id.btn_login);
    }
}