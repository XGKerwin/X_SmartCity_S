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
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.x_smartcity_s.Adapter.Service_Outpatient_msg_inquire_adapter;
import com.example.x_smartcity_s.R;
import com.example.x_smartcity_s.bean.GetSeeADoctor;
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
 * date   : 2021/2/23  14:29
 */
public class Fragment_Service_Outpatient_msg_inquire extends Fragment {

    private ImageView titleBack;
    private TextView titleTitle;
    private EditText edShenfenzheng;
    private Button btn;
    private RecyclerView recyclerview;
    private HospitalList list;
    private List<GetSeeADoctor> getSeeADoctors;
    private Service_Outpatient_msg_inquire_adapter adapter;

    public Fragment_Service_Outpatient_msg_inquire(HospitalList list) {
        this.list = list;

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.fragment_service_outpatient_msg_inquire, container, false);
        initView(view);
        titleTitle.setText("预约查询");

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s = edShenfenzheng.getText().toString();
                if (s.equals("")){
                    Toast.makeText(getContext(),"请输入身份证号",Toast.LENGTH_SHORT).show();
                }else {
                    getOkHttp(s);
                }
            }
        });

        titleBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragment(new Fragment_Service_Outpatient_msg(list));
            }
        });

        return view;
    }

    private void getOkHttp(String s) {
        if (getSeeADoctors==null){
            getSeeADoctors = new ArrayList<>();
        }else {
            getSeeADoctors.clear();
        }
        new OKHttpTo()
                .setUrl("getSeeADoctor")
                .setJSONObject("pid", s)
                .setOkHttpLo(new OkHttpLo() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        getSeeADoctors.addAll(new Gson().fromJson(jsonObject.optJSONArray("ROWS_DETAIL").toString(),
                                new TypeToken<List<GetSeeADoctor>>(){}.getType()));

                        if (getSeeADoctors.size()==0){
                            Toast.makeText(getContext(),"未查到预约信息",Toast.LENGTH_SHORT).show();
                            adapter.notifyDataSetChanged();
                        }else {
                            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
                            recyclerview.setLayoutManager(linearLayoutManager);

                            linearLayoutManager.setStackFromEnd(true);//列表再底部开始展示，反转后由上面开始展示

                            linearLayoutManager.setReverseLayout(true);//列表翻转

                            if (adapter == null){
                                adapter = new Service_Outpatient_msg_inquire_adapter(getSeeADoctors);
                            }else {
                                adapter.notifyDataSetChanged();
                            }
                            recyclerview.setAdapter(adapter);
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
        edShenfenzheng = view.findViewById(R.id.ed_shenfenzheng);
        btn = view.findViewById(R.id.btn);
        recyclerview = view.findViewById(R.id.recyclerview);
    }
}
