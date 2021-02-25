package com.example.x_smartcity_s.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.x_smartcity_s.R;
import com.example.x_smartcity_s.bean.GetSeeADoctor;

import java.util.List;

/**
 * author : 关鑫
 * Github : XGKerwin
 * date   : 2021/2/23  14:35
 */
public class Service_Outpatient_msg_inquire_adapter extends RecyclerView.Adapter<Service_Outpatient_msg_inquire_adapter.ViewHolder> {

    private List<GetSeeADoctor> getSeeADoctors;

    public Service_Outpatient_msg_inquire_adapter(List<GetSeeADoctor> getSeeADoctors) {
        this.getSeeADoctors = getSeeADoctors;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_service_outpatient_msg_inquire, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        GetSeeADoctor doctor = getSeeADoctors.get(position);
        holder.name.setText("姓名："+doctor.getName()+"        手机号："+doctor.getPhone());
        holder.time.setText("时间："+doctor.getAppTime());
        holder.yisheng.setText("主治医生："+doctor.getDoctorname());
    }

    @Override
    public int getItemCount() {
        return getSeeADoctors.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        private TextView name;
        private TextView time;
        private TextView yisheng;

        public ViewHolder(@NonNull View view) {
            super(view);
            name = view.findViewById(R.id.name);
            time = view.findViewById(R.id.time);
            yisheng = view.findViewById(R.id.yisheng);
        }
    }

}
