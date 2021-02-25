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

import com.example.x_smartcity_s.Fragment.Service.Fragment_Service_metro_line;
import com.example.x_smartcity_s.R;
import com.example.x_smartcity_s.bean.GetSubwaysByStation;

import java.util.List;

/**
 * author : 关鑫
 * Github : XGKerwin
 * date   : 2021/2/22  14:49
 */
public class Service_metro_adapter extends RecyclerView.Adapter<Service_metro_adapter.ViewHolder> {

    private List<GetSubwaysByStation> getSubwaysByStations;
    private FragmentActivity activity;
    private String didian;

    public Service_metro_adapter(List<GetSubwaysByStation> subwaysByStations, FragmentActivity activity, String s_didian) {
        this.getSubwaysByStations = subwaysByStations;
        this.activity = activity;
        this.didian = s_didian;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_service_metro, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        GetSubwaysByStation byStation = getSubwaysByStations.get(position);
        holder.txtDitie.setText(byStation.getName());
        holder.txtDidian.setText(byStation.getNextname());
        holder.txtTime.setText(byStation.getTime());

        holder.layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragment(new Fragment_Service_metro_line(byStation.getSubwayid(),didian));
            }
        });
    }

    private void getFragment(Fragment fragment) {
        FragmentTransaction fragmentTransaction = activity.getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.fragment_home,fragment).commit();
    }

    @Override
    public int getItemCount() {
        return getSubwaysByStations.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private LinearLayout layout;
        private TextView txtDitie;
        private TextView txtDidian;
        private TextView txtTime;

        public ViewHolder(@NonNull View view) {
            super(view);
            layout = view.findViewById(R.id.btn_registered);
            txtDitie = view.findViewById(R.id.txt_ditie);
            txtDidian = view.findViewById(R.id.txt_didian);
            txtTime = view.findViewById(R.id.txt_time);
        }
    }
}
