package com.example.x_smartcity_s.Adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class Service_Smartbus_msg_2_adapter extends ArrayAdapter<String> {

    public Service_Smartbus_msg_2_adapter(List<String> strings, Context context) {
        super(context,0,strings);
    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = View.inflate(getContext(),android.R.layout.simple_list_item_1,null);
        TextView textView = view.findViewById(android.R.id.text1);
        textView.setText(getItem(position));
        textView.setTextSize(17);
        textView.setTextColor(Color.BLACK);
        textView.setGravity(Gravity.LEFT);
        return view;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = View.inflate(getContext(),android.R.layout.simple_list_item_1,null);
        TextView textView = view.findViewById(android.R.id.text1);
        textView.setText(getItem(position));
        textView.setTextSize(17);
        textView.setTextColor(Color.BLACK);
        textView.setGravity(Gravity.LEFT);
        return view;
    }

}
