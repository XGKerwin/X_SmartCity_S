package com.example.x_smartcity_s.Fragment.Service;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.x_smartcity_s.Fragment.Fragment_Service_Violation_list;
import com.example.x_smartcity_s.R;
import com.example.x_smartcity_s.bean.GetViolationsByCarId;

import java.util.List;

/**
 * author : 关鑫
 * Github : XGKerwin
 * date   : 2021/2/23  10:27
 */
public class Fragment_Service_Violation_list_msg extends Fragment {

    private List<GetViolationsByCarId> violationsByCarIds;
    private GetViolationsByCarId carId;
    private ImageView titleBack;
    private TextView titleTitle;
    private TextView txtTime;
    private TextView txtPlace;
    private TextView txtBehavior;
    private TextView txtNumber;
    private TextView txtDeduction;
    private TextView txtFine;

    public Fragment_Service_Violation_list_msg(List<GetViolationsByCarId> byCarIds, GetViolationsByCarId carId) {
        this.violationsByCarIds = byCarIds;
        this.carId = carId;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.fragment_service_violation_list_msg, container, false);
        initView(view);
        titleTitle.setText("违章详情");

        txtTime.setText("违法时间："+carId.getTime());
        txtPlace.setText("违法地点："+carId.getPlace());
        txtBehavior.setText("违法行为："+carId.getDescription());
        txtNumber.setText("通知书号："+carId.getNotification());
        txtDeduction.setText("违章计分："+carId.getDeductPoints()+"分");
        txtFine.setText("罚款金额："+carId.getCost()+"元");


        titleBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragment(new Fragment_Service_Violation_list(violationsByCarIds));
            }
        });
        return view;
    }

    private void getFragment(Fragment fragment) {
        FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.fragment_home,fragment).commit();
    }

    private void initView(View view) {
        titleBack = view.findViewById(R.id.title_back);
        titleTitle = view.findViewById(R.id.title_title);
        txtTime = view.findViewById(R.id.txt_time);
        txtPlace = view.findViewById(R.id.txt_place);
        txtBehavior = view.findViewById(R.id.txt_behavior);
        txtNumber = view.findViewById(R.id.txt_number);
        txtDeduction = view.findViewById(R.id.txt_deduction);
        txtFine = view.findViewById(R.id.txt_fine);
    }
}
