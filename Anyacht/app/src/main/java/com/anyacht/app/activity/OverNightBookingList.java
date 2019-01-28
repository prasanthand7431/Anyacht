package com.anyacht.app.activity;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.anyacht.app.R;
import com.anyacht.app.adapter.BookingAdapter;
import com.anyacht.app.constants.ApiClient;
import com.anyacht.app.constants.Api_Interface;
import com.anyacht.app.model.BookingDetails;
import com.anyacht.app.reponse.Booking;
import com.anyacht.app.utils.Appconstants;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class OverNightBookingList extends BaseActivity {


    String get_desId,get_status,get_checkin,get_checkout,get_hours;

    Api_Interface api_interface;


    OverNightBookingAdapter overNightBookingAdapter;


    ListView list;

    ArrayList<BookingDetails> bookingDetailsArrayList;

    String get_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_over_night_booking_list);

        list = (ListView)findViewById(R.id.list_items);




        Bundle bundle = getIntent().getExtras();
        if (bundle!=null) {

            get_desId = bundle.getString("destID");
            get_status = bundle.getString("typeID");
            get_checkin = bundle.getString("checkIN");
            get_checkout = bundle.getString("checkOUT");

            Log.d("CheckData" ,get_desId+get_status+get_checkin+get_checkout);

            LoadgetBookData();

        }


        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                get_id = bookingDetailsArrayList.get(position).getId();


                bookingAlert();


                Log.d("CheckIDD" ,get_id);
                Log.d("CheckArr" ,""+bookingDetailsArrayList.size());



            }
        });

    }

    private void bookingAlert() {

        AlertDialog.Builder builder1 = new AlertDialog.Builder(OverNightBookingList.this);
        builder1.setMessage("Are You Sure Booking Yacht");
        builder1.setCancelable(true);

        builder1.setPositiveButton(
                "Yes",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {

                        LoadDataBooking();
                    }
                });

        builder1.setNegativeButton(
                "No",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });

        AlertDialog alert11 = builder1.create();
        alert11.show();
    }


    private void LoadgetBookData() {

        loader(true);

        api_interface = ApiClient.getApiClient().create(Api_Interface.class);
        Log.d("CheckData1123", get_desId + get_status + get_checkin + get_checkout);


        Call<Booking> call1 = api_interface.getOvernightBooking(get_desId,get_status,get_checkin,get_checkout);

        call1.enqueue(new Callback<Booking>() {
            @Override
            public void onResponse(Call<Booking> call, Response<Booking> response) {

                Booking booking = response.body();


                if (booking.status.equalsIgnoreCase("1")) {


                    bookingDetailsArrayList = booking.getyacht_details();

                    overNightBookingAdapter = new OverNightBookingAdapter(OverNightBookingList.this, booking.getyacht_details(),
                            OverNightBookingList.this);


                    list.setAdapter(overNightBookingAdapter);


                } else {


                    Toast.makeText(OverNightBookingList.this, booking.getMsg(), Toast.LENGTH_SHORT).show();
                }


                Log.e("Responce", "recived " + booking.getMsg());
                loader(false);
            }


            @Override
            public void onFailure(Call<Booking> call, Throwable t) {
                Log.e("error", " " + t.getLocalizedMessage());
                Toast.makeText(OverNightBookingList.this, "Please try after some time", Toast.LENGTH_SHORT).show();

                loader(false);
                call.cancel();
            }
        });
    }

    private void LoadDataBooking() {

        loader(true);

        api_interface = ApiClient.getApiClient().create(Api_Interface.class);
        Log.d("CheckData11234", get_id+get_desId + get_status + get_checkin + get_checkout);


        Call<Booking> call1 = api_interface.getBookingRegister1(get_id , Appconstants.mUsername,Appconstants.mEmailId,Appconstants.mMobile,get_status,get_checkin,get_checkout);

        call1.enqueue(new Callback<Booking>() {
            @Override
            public void onResponse(Call<Booking> call, Response<Booking> response) {

                Booking booking = response.body();


                if (booking.status.equalsIgnoreCase("1")) {



                    Toast.makeText(getApplicationContext() ,"Booking Successfully" ,Toast.LENGTH_LONG).show();


                } else {


                    Toast.makeText(OverNightBookingList.this, booking.getMsg(), Toast.LENGTH_SHORT).show();
                }


                Log.e("Responce", "recived " + booking.getMsg());
                loader(false);
            }


            @Override
            public void onFailure(Call<Booking> call, Throwable t) {
                Log.e("error", " " + t.getLocalizedMessage());
                Toast.makeText(OverNightBookingList.this, "Please try after some time", Toast.LENGTH_SHORT).show();

                loader(false);
                call.cancel();
            }
        });
    }


}
