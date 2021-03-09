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
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.x_smartcity_s.Adapter.Service_Violation_list_adapter;
import com.example.x_smartcity_s.R;
import com.example.x_smartcity_s.bean.GetViolationsByCarId;

import java.util.List;

/**
 * author : 关鑫
 * Github : XGKerwin
 * date   : 2021/2/23  9:50
 */
public class Fragment_Service_Violation_list extends Fragment {

    private List<GetViolationsByCarId> byCarIds;
    private ImageView titleBack;
    private TextView titleTitle;
    private RecyclerView Recyclerview;
    private TextView gengduo;
    private Service_Violation_list_adapter adapter;

    public Fragment_Service_Violation_list(List<GetViolationsByCarId> byCarIds) {
        this.byCarIds = byCarIds;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.fragment_service_violation_list, container, false);
        initView(view);
        titleTitle.setText("违章详情");

        getShow();
        
        btn();


        return view;
    }

    private void btn() {
        titleBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragment(new Fragment_Service_Violation());
            }
        });

        gengduo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gengduo.setVisibility(View.GONE);
                getShow();
            }
        });
    }

    private void getFragment(Fragment fragment) {
        FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.fragment_home,fragment).commit();
    }

    private void getShow() {

        if (adapter == null) {
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
            Recyclerview.setLayoutManager(linearLayoutManager);
            adapter = new Service_Violation_list_adapter(byCarIds, 6,getContext(),getActivity());
        } else {
            adapter = new Service_Violation_list_adapter(byCarIds, byCarIds.size(),getContext(),getActivity());
        }

        Recyclerview.setAdapter(adapter);

    }

    private void initView(View view) {
        titleBack = view.findViewById(R.id.title_back);
        titleTitle = view.findViewById(R.id.title_title);
        Recyclerview = view.findViewById(R.id.Recyclerview);
        gengduo = view.findViewById(R.id.gengduo);
    }
}
