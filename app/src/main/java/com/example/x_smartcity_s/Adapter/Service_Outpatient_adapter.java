package com.example.x_smartcity_s.Adapter;

import android.graphics.Bitmap;
import android.telecom.Call;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.example.x_smartcity_s.Fragment.Service.Fragment_Service_Outpatient_msg;
import com.example.x_smartcity_s.R;
import com.example.x_smartcity_s.bean.GetRankByHospitalId;
import com.example.x_smartcity_s.bean.HospitalList;
import com.example.x_smartcity_s.net.OkHttpLoImage;
import com.example.x_smartcity_s.net.OkHttpToImage;

import java.io.IOException;
import java.util.List;

/**
 * author : 关鑫
 * Github : XGKerwin
 * date   : 2021/2/23  11:01
 */
public class Service_Outpatient_adapter extends RecyclerView.Adapter<Service_Outpatient_adapter.ViewHolder> {
    private List<HospitalList> hospitalLists;
    private List<GetRankByHospitalId> rankByHospitalIds;
    private FragmentActivity activity;

    public Service_Outpatient_adapter(List<HospitalList> hospitalLists, List<GetRankByHospitalId> rankByHospitalIds, FragmentActivity activity) {
        this.activity = activity;
        this.hospitalLists = hospitalLists;
        this.rankByHospitalIds = rankByHospitalIds;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_service_outpatient, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        HospitalList list = hospitalLists.get(position);
        GetRankByHospitalId hospitalId = rankByHospitalIds.get(position);

        holder.txtTitle.setText(list.getHospitalName());
        holder.paiming.setText("排名：NO"+hospitalId.getRank());

        new OkHttpToImage()
                .setUrl(list.getPicture())
                .setOkHttpLoImage(new OkHttpLoImage() {
                    @Override
                    public void onResponse(Call call, Bitmap bitmap) {
                        holder.img.setImageBitmap(bitmap);
                    }

                    @Override
                    public void onFailure(Call call, IOException obj) {

                    }
                }).start();

        holder.layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragment(new Fragment_Service_Outpatient_msg(list));
            }
        });

    }

    private void getFragment(Fragment fragment) {
        FragmentTransaction fragmentTransaction = activity.getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.fragment_home,fragment).commit();
    }

    @Override
    public int getItemCount() {
        return hospitalLists.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private LinearLayout layout;
        private ImageView img;
        private TextView txtTitle;
        private TextView paiming;

        public ViewHolder(@NonNull View view) {
            super(view);
            layout = view.findViewById(R.id.btn_registered);
            img = view.findViewById(R.id.img);
            txtTitle = view.findViewById(R.id.txt_title);
            paiming = view.findViewById(R.id.paiming);
        }
    }
}
