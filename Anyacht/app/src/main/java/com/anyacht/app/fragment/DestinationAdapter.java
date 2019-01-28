package com.anyacht.app.fragment;

import android.content.Context;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import com.anyacht.app.R;
import com.anyacht.app.model.DestinationDetails;

import java.util.ArrayList;

public class DestinationAdapter extends BaseAdapter {

    LayoutInflater inflater;

    Context context;

    String get_name;


    ArrayList<DestinationDetails> destinationDetails;

    public DestinationAdapter(FragmentActivity activity, ArrayList<DestinationDetails> getdetails, FragmentActivity activity1) {

        this.context = activity;
        this.destinationDetails = getdetails;
        inflater = LayoutInflater.from(this.context);
    }

    public DestinationAdapter(FragmentActivity activity, int select_dialog_item, ArrayList<DestinationDetails> destinationDetailsArrayList) {

        this.context = activity;
        this.destinationDetails = destinationDetailsArrayList;
        inflater = LayoutInflater.from(this.context);
    }

    @Override
    public int getCount() {
        return destinationDetails.size();
    }

    @Override
    public Object getItem(int position) {
        return destinationDetails.get(position).getId();
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        MyViewHolder mViewHolder;

        if (convertView == null) {
            convertView = inflater.inflate(R.layout.adapter_destination, parent, false);
            mViewHolder = new MyViewHolder(convertView);
            convertView.setTag(mViewHolder);
        } else {
            mViewHolder = (MyViewHolder) convertView.getTag();
        }

        get_name = destinationDetails.get(position).getName().toString();

        mViewHolder.desn_name.setText(get_name);


        return convertView;
    }

    private class MyViewHolder {

        TextView desn_name;


        public MyViewHolder(View item) {
            desn_name = (TextView) item.findViewById(R.id._des_name);

        }
    }

}