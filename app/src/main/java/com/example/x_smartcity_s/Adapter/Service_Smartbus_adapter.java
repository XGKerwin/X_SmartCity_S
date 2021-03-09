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

import com.example.x_smartcity_s.R;
import com.example.x_smartcity_s.bean.BusList;

import java.util.List;

public class Service_Smartbus_adapter extends RecyclerView.Adapter<Service_Smartbus_adapter.ViewHolder> {

    private List<BusList> busLists;
    private FragmentActivity activity;

    public Service_Smartbus_adapter(List<BusList> busLists, FragmentActivity activity) {
        this.activity = activity;
        this.busLists = busLists;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_smartbus, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        BusList list = busLists.get(position);
        holder.number.setText(list.getPathName()+"");
        holder.piaojia.setText("票价："+list.getPrice()+"元");
        holder.licheng.setText("里程："+list.getMileage()+"KM");
        holder.time1.setText("春夏："+list.getRunTime1());
        holder.time2.setText("秋冬："+list.getRunTime2());
        holder.txtQi.setText(list.getStartSite());
        holder.zhi.setText(list.getEndSite());

        holder.layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragment(new Fragment_Service_smartbus_msg(list));
            }
        });

    }

    private void getFragment(Fragment fragment) {
        FragmentTransaction fragmentTransaction = activity.getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.fragment_home,fragment).commit();
    }

    @Override
    public int getItemCount() {
        return busLists.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        private LinearLayout layout;
        private TextView number;
        private TextView piaojia;
        private TextView licheng;
        private TextView txtQi;
        private TextView zhi;
        private TextView time1;
        private TextView time2;

        public ViewHolder(@NonNull View view) {
            super(view);
            layout = view.findViewById(R.id.layout);
            number = view.findViewById(R.id.number);
            piaojia = view.findViewById(R.id.piaojia);
            licheng = view.findViewById(R.id.licheng);
            txtQi = view.findViewById(R.id.txt_qi);
            zhi = view.findViewById(R.id.zhi);
            time1 = view.findViewById(R.id.time1);
            time2 = view.findViewById(R.id.time2);
        }
    }
}
