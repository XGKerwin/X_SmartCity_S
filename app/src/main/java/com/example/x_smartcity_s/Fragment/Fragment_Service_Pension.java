package com.example.x_smartcity_s.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.x_smartcity_s.R;

/**
 * author : 关鑫
 * Github : XGKerwin
 * date   : 2021/2/23  15:02
 */
public class Fragment_Service_Pension extends Fragment {

    private ImageView titleBack;
    private TextView titleTitle;
    private LinearLayout btnReservation;
    private LinearLayout btnSurroundings;
    private LinearLayout btnFood;
    private LinearLayout btnOccupancy;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.fragment_service_pension, container, false);
        initView(view);
        titleTitle.setText("智慧养老");

        btn();

        return view;

    }

    private void btn() {
        btnOccupancy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragment(new Fragment_Service_Pension_occupancy());
            }
        });

        btnFood.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragment(new Fragment_Service_Pension_food());
            }
        });

        btnReservation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragment(new Fragment_Service_Pension_reservation());
            }
        });

        btnSurroundings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragment(new Fragment_Service_Pension_surroundings());
            }
        });

        titleBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragment(new Fragment_Service());
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
        btnReservation = view.findViewById(R.id.btn_reservation);
        btnSurroundings = view.findViewById(R.id.btn_surroundings);
        btnFood = view.findViewById(R.id.btn_food);
        btnOccupancy = view.findViewById(R.id.btn_Occupancy);
    }
}
