package com.example.x_smartcity_s.Fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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

import com.example.x_smartcity_s.Adapter.Spinner_adapter;
import com.example.x_smartcity_s.R;
import com.example.x_smartcity_s.bean.GetViolationsByCarId;
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
 * date   : 2021/2/23  9:21
 */
public class Fragment_Service_Violation extends Fragment {

    private ImageView titleBack;
    private TextView titleTitle;
    private Spinner cp1;
    private EditText cp2;
    private Button btnSousuo;
    private List<GetViolationsByCarId> byCarIds;
    private Spinner_adapter adapter;
    private String cp[] = {"鲁"};

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.fragment_service_violation, container, false);
        initView(view);

        titleTitle.setText("违章查询");

        adapter = new Spinner_adapter(getContext(),cp);
        cp1.setAdapter(adapter);


        btnSousuo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s1 = "鲁";
                String s2 = cp2.getText().toString().trim();
                if (s1.equals("")){
                    Toast.makeText(getContext(),"车牌不能为空",Toast.LENGTH_SHORT).show();
                }else if (s2.equals("")){
                    Toast.makeText(getContext(),"车牌不能为空",Toast.LENGTH_SHORT).show();
                }else {
                    String s = s1+s2;
                    getOkHttp(s);
                }
            }
        });

        titleBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragment(new Fragment_Service());
            }
        });


        return view;
    }

    private void getOkHttp(String s) {

        if (byCarIds == null){
            byCarIds = new ArrayList<>();
        }else {
            byCarIds.clear();
        }
        /**
         * {carid:"鲁A10001"}
         */
        new OKHttpTo()
                .setUrl("getViolationsByCarId")
                .setJSONObject("carid",s)
                .setOkHttpLo(new OkHttpLo() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        byCarIds.addAll(new Gson().fromJson(jsonObject.optJSONArray("ROWS_DETAIL").toString(),
                                new TypeToken<List<GetViolationsByCarId>>(){}.getType()));
                        if (byCarIds.size()==0){
                            Toast.makeText(getContext(),"此车牌没有违章记录",Toast.LENGTH_SHORT).show();
                        }else {
                            getFragment(new Fragment_Service_Violation_list(byCarIds));
                        }
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
        cp1 = view.findViewById(R.id.cp_1);
        cp2 = view.findViewById(R.id.cp_2);
        btnSousuo = view.findViewById(R.id.btn_sousuo);
    }
}
