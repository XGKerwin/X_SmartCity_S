package com.example.x_smartcity_s.Adapter;

import android.content.Context;
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

import com.example.x_smartcity_s.Fragment.Service.Fragment_Service_Violation_list_msg;
import com.example.x_smartcity_s.R;
import com.example.x_smartcity_s.bean.GetViolationsByCarId;

import java.util.List;

/**
 * author : 关鑫
 * Github : XGKerwin
 * date   : 2021/2/23  9:53
 */
public class Service_Violation_list_adapter extends RecyclerView.Adapter<Service_Violation_list_adapter.ViewHolder> {

    private List<GetViolationsByCarId> byCarIds;
    private int i;
    private Context context;
    private FragmentActivity activity;

    public Service_Violation_list_adapter(List<GetViolationsByCarId> byCarIds, int i, Context context, FragmentActivity activity) {
        this.byCarIds = byCarIds;
        this.i = i;
        this.context = context;
        this.activity = activity;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.adapter_service_violation_list, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        GetViolationsByCarId carId = byCarIds.get(position);
        holder.cph.setText("车牌号："+carId.getCarid());
        holder.time.setText("时间：" + carId.getTime());
        holder.place.setText("违章地点："+carId.getPlace());
        holder.penalty.setText("罚款：" + carId.getCost()+"元");
        holder.violation.setText("违章记分：" + carId.getDeductPoints()+"分");
        switch (carId.getStatus()) {
            case "1":
                holder.status.setText("状态：已处理");
                break;
            case "2":
                holder.status.setText("状态：未处理");
                break;
        }

        holder.layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragment(new Fragment_Service_Violation_list_msg(byCarIds,carId));
            }
        });

    }

    private void getFragment(Fragment fragment) {
        FragmentTransaction fragmentTransaction = activity.getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.fragment_home,fragment).commit();
    }

    @Override
    public int getItemCount() {
        return i;
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private LinearLayout layout;
        private TextView cph;
        private TextView status;
        private TextView time;
        private TextView penalty;
        private TextView violation;
        private TextView place;

        public ViewHolder(@NonNull View view) {
            super(view);
            layout = view.findViewById(R.id.btn_registered);
            cph = view.findViewById(R.id.cph);
            status = view.findViewById(R.id.status);
            time = view.findViewById(R.id.time);
            penalty = view.findViewById(R.id.penalty);
            violation = view.findViewById(R.id.violation);
            place = view.findViewById(R.id.place);
        }
    }
}
