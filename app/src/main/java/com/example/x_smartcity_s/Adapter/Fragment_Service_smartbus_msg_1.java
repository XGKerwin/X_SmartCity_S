package com.example.x_smartcity_s.Adapter;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.x_smartcity_s.R;
import com.example.x_smartcity_s.bean.BusList;

public class Fragment_Service_smartbus_msg_1 extends Fragment {

    private BusList busList;
    private ImageView titleBack;
    private TextView titleTitle;
    private DatePicker datePicker;
    private TextView txtTime;
    private Button btnQueding;
    private Button btnXiayibu;
    private String desc = "";

    public Fragment_Service_smartbus_msg_1(BusList busList) {
        this.busList = busList;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.fragment_service_smartbus_msg_1, container, false);
        initView(view);
        titleTitle.setText("定制班车");

        btn();


        return view;
    }

    private void btn() {
        btnXiayibu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (desc.equals("")){
                    Toast.makeText(getContext(),"请选择出行日期",Toast.LENGTH_SHORT).show();
                }else {
                    getFragment(new Fragment_Service_smartbus_msg_2(busList,desc));
                }
            }
        });


        btnQueding.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                desc = String.format("%d年%d月%d日", datePicker.getYear(), datePicker.getMonth() + 1, datePicker.getDayOfMonth());
                txtTime.setText("乘车日期："+desc);
            }
        });


        titleBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragment(new Fragment_Service_smartbus_msg(busList));
            }
        });
    }

    private void getFragment(Fragment fragment) {
        FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.fragment_home,fragment).commit();
    }

    private void initView(View view) {
        titleBack = view.findViewById(R.id.title_back);
        titleTitle = view.findViewById(R.id.title_title);
        datePicker = view.findViewById(R.id.dp_date);
        txtTime = view.findViewById(R.id.txt_time);
        btnQueding = view.findViewById(R.id.btn_queding);
        btnXiayibu = view.findViewById(R.id.btn_xiayibu);
    }
}
