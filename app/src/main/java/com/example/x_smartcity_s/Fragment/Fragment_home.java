package com.example.x_smartcity_s.Fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.x_smartcity_s.Fragment.My.Fragment_My;
import com.example.x_smartcity_s.Fragment.Party.Fragment_Party;
import com.example.x_smartcity_s.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

/**
 * author : 关鑫
 * Github : XGKerwin
 * date   : 2021/2/10  16:56
 */
public class Fragment_home extends Fragment {

    private BottomNavigationView bottomNav;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.fragment_home, container, false);
        initView(view);

        getFragment(new Fragment_Page());

        btn_Navigation();


        return view;
    }

    @SuppressLint("WrongConstant")
    private void btn_Navigation() {
        bottomNav.setLabelVisibilityMode(1);
        bottomNav.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.bom_home:
                        getFragment(new Fragment_Page());
                        break;
                    case R.id.bom_fuwu:
                        getFragment(new Fragment_Service());
                        break;
                    case R.id.bom_dangjian:
                        getFragment(new Fragment_Party());
                        break;
                    case R.id.bom_xinwen:
                        getFragment(new Fragment_news());
                        break;
                    case R.id.bom_wode:
                        getFragment(new Fragment_My());
                        break;
                }

                return true;
            }
        });

    }

    private void getFragment(Fragment fragment) {
        FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.fragment_home,fragment).commit();
    }

    private void initView(View view) {
        bottomNav = view.findViewById(R.id.bottom_nav);
    }
}
