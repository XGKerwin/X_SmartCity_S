package com.example.x_smartcity_s.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.x_smartcity_s.OnClickItem;
import com.example.x_smartcity_s.R;
import com.example.x_smartcity_s.bean.DoctorList;

import java.util.List;

/**
 * author : 关鑫
 * Github : XGKerwin
 * date   : 2021/2/23  13:55
 */
public class Service_Outpatient_msg_registered_Department_Doctors_adapter extends RecyclerView.Adapter<Service_Outpatient_msg_registered_Department_Doctors_adapter.ViewHolder> {

    private List<DoctorList> lists;
    private String time1;
    private FragmentActivity activity;
    public OnClickItem onClickItem;

    public void setOnClickItem(OnClickItem onClickItem) {
        this.onClickItem = onClickItem;
    }

    public Service_Outpatient_msg_registered_Department_Doctors_adapter(List<DoctorList> doctors, String time, FragmentActivity activity, Context context) {
        this.activity = activity;
        this.time1 = time;
        this.lists = doctors;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_service_outpatient_department_doctors, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        DoctorList list = lists.get(position);
        holder.name.setText("姓名：" + list.getDoctorName());
        holder.msg.setText("简介：" + list.getDesc());
        holder.jianjie.setText("擅长：" + list.getTag());
        holder.time.setText(time1);

        holder.btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickItem.onClick(position,"1");
            }
        });


    }

    @Override
    public int getItemCount() {
        return lists.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private LinearLayout layout;
        private TextView name;
        private TextView time;
        private TextView btn;
        private TextView msg;
        private TextView jianjie;

        public ViewHolder(@NonNull View view) {
            super(view);
            layout = view.findViewById(R.id.layout);
            name = view.findViewById(R.id.name);
            btn = view.findViewById(R.id.btn);
            msg = view.findViewById(R.id.msg);
            jianjie = view.findViewById(R.id.jianjie);
            time = view.findViewById(R.id.time);
        }
    }


}
