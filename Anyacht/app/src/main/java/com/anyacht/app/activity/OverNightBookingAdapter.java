package com.anyacht.app.activity;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.anyacht.app.R;
import com.anyacht.app.adapter.BookingAdapter;
import com.anyacht.app.model.BookingDetails;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.util.ArrayList;

public class OverNightBookingAdapter extends BaseAdapter{

    ArrayList<BookingDetails> bookingDetailsArrayList;

    Context context;

    LayoutInflater inflater;

    String imag_url;


    public OverNightBookingAdapter(OverNightBookingList overNightBookingList, ArrayList<BookingDetails> bookingDetails, OverNightBookingList overNightBookingList1) {

        this.bookingDetailsArrayList = bookingDetails;
        this.context = overNightBookingList;
        this.inflater = LayoutInflater.from(overNightBookingList);

    }

    @Override
    public int getCount() {
        return bookingDetailsArrayList.size();
    }



    @Override
    public Object getItem(int position) {
        return bookingDetailsArrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        OverNightBookingAdapter.ViewHolder holder;
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.adapter_overbooking_list, null);
            holder = new OverNightBookingAdapter.ViewHolder();

            holder.yachyname = (TextView) convertView.findViewById(R.id.yachy_name);
            holder.yachyprice = (TextView) convertView.findViewById(R.id.yatch_price);
            holder.yatchpeople = (TextView) convertView.findViewById(R.id.yatch_people);
            holder.yatch_details = (TextView) convertView.findViewById(R.id.yatch11);
            holder.booking = (TextView) convertView.findViewById(R.id.yatch_btn_book);
            holder.imageView = (ImageView) convertView.findViewById(R.id.yacht_img);



            convertView.setTag(holder);

        } else {

            holder = (OverNightBookingAdapter.ViewHolder) convertView.getTag();
        }


        holder.yachyname.setText(bookingDetailsArrayList.get(position).getTitle() +" "+bookingDetailsArrayList.get(position).getDest());
        holder.yachyprice.setText("$"+" "+bookingDetailsArrayList.get(position).getPrice()+" "+"Per Hour");
        holder.yatchpeople.setText(bookingDetailsArrayList.get(position).getMax_people() +" "+"Persons");



        imag_url = bookingDetailsArrayList.get(position).getFile();

        Glide.with(context).load(imag_url)
                .thumbnail(0.5f)
                .crossFade()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(holder.imageView);


        holder.yatch_details.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i2 = new Intent(context ,YatchDetailsActivity.class);
                i2.putExtra("yatchimage" ,bookingDetailsArrayList.get(position).getFile());
                i2.putExtra("yatchdetails" ,bookingDetailsArrayList.get(position).getDescr());
                i2.putExtra("yatchprice" ,bookingDetailsArrayList.get(position).getPrice());
                i2.putExtra("yatchpeoplemax" ,bookingDetailsArrayList.get(position).getMax_people());
                context.startActivity(i2);

            }
        });





        return convertView;
    }

    class ViewHolder
    {
        TextView yachyname;
        TextView yachyprice;
        TextView yatchpeople;
        TextView yatch_details;
        ImageView imageView;
        TextView booking;


    }


}
