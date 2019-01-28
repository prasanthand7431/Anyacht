package com.anyacht.app.fragment;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.anyacht.app.R;
import com.anyacht.app.activity.OverNightBookingAdapter;

import java.util.ArrayList;

public class BookHistoryAdapter extends RecyclerView.Adapter<BookHistoryAdapter.ViewHolder>{


    ArrayList<BookingHistoryDetails> bookingHistoryDetailsArrayList;


    Context context;
    String get_status;

    public BookHistoryAdapter(FragmentActivity activity, ArrayList<BookingHistoryDetails> details, FragmentActivity activity1) {


        this.bookingHistoryDetailsArrayList = details;

        this.context = activity;
    }

    @NonNull
    @Override
    public BookHistoryAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_bookinghistory_list, parent, false);

        return new BookHistoryAdapter.ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull BookHistoryAdapter.ViewHolder holder, int position) {

        holder.name.setText(bookingHistoryDetailsArrayList.get(position).getTitle());
        holder.people.setText(bookingHistoryDetailsArrayList.get(position).getMax_people()+""+" "+"Persons");
        holder.price.setText(bookingHistoryDetailsArrayList.get(position).getPrice()+""+" "+"Per Hour");
        holder.checkin.setText(bookingHistoryDetailsArrayList.get(position).getCheckin());
        holder.checkout.setText(bookingHistoryDetailsArrayList.get(position).getCheckout());

         get_status = bookingHistoryDetailsArrayList.get(position).getPaymentstatus();

        if (get_status.equals("0")){

            holder.pay_statuss.setText("Un Paid");

            holder.pay_statuss.setTextColor(Color.RED);

        }else if (get_status.equals("1")){

            holder.pay_statuss.setText("Paid");

            holder.pay_statuss.setTextColor(Color.GREEN);

        }



    }

    @Override
    public int getItemCount() {
        return bookingHistoryDetailsArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView name;
        TextView people;
        TextView price;
        TextView checkin;
        TextView checkout;
        TextView pay_statuss;

        public ViewHolder(View itemView) {
            super(itemView);

            name =(TextView)itemView.findViewById(R.id.txt_yacht_name);
            people =(TextView)itemView.findViewById(R.id.txt_yachtpeople);
            price =(TextView)itemView.findViewById(R.id.txt_yachtprice);
            checkin =(TextView)itemView.findViewById(R.id.txt_checkin);
            checkout =(TextView)itemView.findViewById(R.id.txt_checkout);
            pay_statuss =(TextView)itemView.findViewById(R.id.txt_paystatus);
        }
    }
}
