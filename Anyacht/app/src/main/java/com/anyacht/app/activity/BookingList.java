package com.anyacht.app.activity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.anyacht.app.model.BookingDetails;
import com.anyacht.app.R;
import com.anyacht.app.adapter.BookingAdapter;
import com.anyacht.app.constants.ApiClient;
import com.anyacht.app.constants.Api_Interface;
import com.anyacht.app.reponse.Booking;
import com.anyacht.app.utils.Appconstants;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BookingList extends BaseActivity {

    ListView listView;

    Api_Interface api_interface;

    String get_desId, get_status, get_checkin, get_hours;

    BookingAdapter bookingAdapter;

    String get_id;
    ArrayList<BookingDetails> bookingDetailsArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking_list);

        listView = (ListView) findViewById(R.id.booking_list);



        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {

            get_desId = bundle.getString("destID");
            get_status = bundle.getString("typeID");
            get_checkin = bundle.getString("checkIN");
            get_hours = bundle.getString("HourS");


            Log.d("CheckData1", get_desId + get_status + get_checkin + get_hours);

        }

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                 get_id = bookingDetailsArrayList.get(position).getId();


                 bookingAlert();


                 Log.d("CheckIDD" ,get_id);



            }
        });



        LoadgetBookData();



    }

    private void bookingAlert() {

        AlertDialog.Builder builder1 = new AlertDialog.Builder(BookingList.this);
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
        Log.d("CheckData1123", get_desId + get_status + get_checkin + get_hours);


        Call<Booking> call1 = api_interface.getBooking(get_desId,get_status,get_checkin,get_hours);

        call1.enqueue(new Callback<Booking>() {
            @Override
            public void onResponse(Call<Booking> call, Response<Booking> response) {

                Booking booking = response.body();


                if (booking.status.equalsIgnoreCase("1")) {


                    bookingDetailsArrayList = booking.getyacht_details();

                    bookingAdapter = new BookingAdapter(BookingList.this, booking.getyacht_details(),
                            BookingList.this);


                    listView.setAdapter(bookingAdapter);


                } else {


                    Toast.makeText(BookingList.this, booking.getMsg(), Toast.LENGTH_SHORT).show();
                }


                Log.e("Responce", "recived " + booking.getMsg());
                loader(false);
            }


            @Override
            public void onFailure(Call<Booking> call, Throwable t) {
                Log.e("error", " " + t.getLocalizedMessage());
                Toast.makeText(BookingList.this, "Please try after some time", Toast.LENGTH_SHORT).show();

                loader(false);
                call.cancel();
            }
        });

    }


    private void LoadDataBooking() {

        loader(true);

        api_interface = ApiClient.getApiClient().create(Api_Interface.class);
        Log.d("CheckData1123", get_desId + get_status + get_checkin + get_hours);


        Call<Booking> call1 = api_interface.getBookingRegister(get_id ,Appconstants.mUsername,Appconstants.mEmailId,Appconstants.mMobile,get_status,get_checkin,get_hours);

        call1.enqueue(new Callback<Booking>() {
            @Override
            public void onResponse(Call<Booking> call, Response<Booking> response) {

                Booking booking = response.body();


                if (booking.status.equalsIgnoreCase("1")) {



                    Toast.makeText(getApplicationContext() ,"Booking Successfully" ,Toast.LENGTH_LONG).show();


                } else {


                    Toast.makeText(BookingList.this, booking.getMsg(), Toast.LENGTH_SHORT).show();
                }


                Log.e("Responce", "recived " + booking.getMsg());
                loader(false);
            }


            @Override
            public void onFailure(Call<Booking> call, Throwable t) {
                Log.e("error", " " + t.getLocalizedMessage());
                Toast.makeText(BookingList.this, "Please try after some time", Toast.LENGTH_SHORT).show();

                loader(false);
                call.cancel();
            }
        });

    }

}
