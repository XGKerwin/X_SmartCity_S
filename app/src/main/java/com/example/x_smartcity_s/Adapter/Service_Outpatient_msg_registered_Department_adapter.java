package com.example.x_smartcity_s.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.example.x_smartcity_s.Fragment.Service.Fragment_Service_Outpatient_msg_registered_Department_Doctors;
import com.example.x_smartcity_s.R;
import com.example.x_smartcity_s.bean.DeptList;
import com.example.x_smartcity_s.bean.HospitalList;

import java.util.List;

/**
 * author : 关鑫
 * Github : XGKerwin
 * date   : 2021/2/23  11:37
 */
public class Service_Outpatient_msg_registered_Department_adapter extends RecyclerView.Adapter<Service_Outpatient_msg_registered_Department_adapter.ViewHolder> {

    private List<DeptList> deptLists;
    private FragmentActivity activity;
    private HospitalList hospitalList;

    public Service_Outpatient_msg_registered_Department_adapter(List<DeptList> deptLists, FragmentActivity activity, HospitalList hospitalList) {
        this.deptLists = deptLists;
        this.activity = activity;
        this.hospitalList = hospitalList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_service_outpatient_department, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        DeptList list = deptLists.get(position);
        holder.title.setText(list.getDeptName());
        holder.msg.setText("介绍："+list.getDesc());
        holder.zhuyao.setText("主要："+list.getTag());
        holder.layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragment(new Fragment_Service_Outpatient_msg_registered_Department_Doctors(hospitalList));
            }
        });
    }

    private void getFragment(Fragment fragment) {
        FragmentTransaction fragmentTransaction = activity.getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.fragment_home,fragment).commit();
    }


    @Override
    public int getItemCount() {
        return deptLists.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private LinearLayout layout;
        private TextView title;
        private TextView msg;
        private TextView zhuyao;

        public ViewHolder(@NonNull View view) {
            super(view);
            layout = view.findViewById(R.id.layout);
            title = view.findViewById(R.id.title);
            msg = view.findViewById(R.id.msg);
            zhuyao = view.findViewById(R.id.zhuyao);
        }
    }
}
