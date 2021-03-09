package com.example.x_smartcity_s.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import com.example.x_smartcity_s.R;
import com.example.x_smartcity_s.bean.AccountByUserId;
import com.example.x_smartcity_s.bean.AccountGroup;

import java.util.List;
import java.util.Map;

public class Service_payment_Account_adapter extends BaseExpandableListAdapter {

    private List<AccountGroup> accountGroups;

    private Map<AccountGroup, List<AccountByUserId>> map;

    public Service_payment_Account_adapter(List<AccountGroup> accountGroups, Map<AccountGroup, List<AccountByUserId>> map) {
        this.map = map;
        this.accountGroups = accountGroups;
    }

    @Override
    public int getGroupCount() {
        return accountGroups.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return map.get(accountGroups.get(groupPosition)).size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return null;
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return null;
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_service_payment_account, parent, false);
        TextView itemType;
        itemType = convertView.findViewById(R.id.item_type);
        itemType.setText(accountGroups.get(groupPosition).getGroupName());
        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_service_payment_account, parent, false);
        TextView itemType;
        itemType = convertView.findViewById(R.id.item_type);
        itemType.setText(accountGroups.get(groupPosition).getGroupName());
        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return false;
    }
}
