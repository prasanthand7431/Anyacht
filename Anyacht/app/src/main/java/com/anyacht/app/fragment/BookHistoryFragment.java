package com.anyacht.app.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.anyacht.app.R;
import com.anyacht.app.activity.OverNightBookingAdapter;
import com.anyacht.app.activity.OverNightBookingList;
import com.anyacht.app.constants.ApiClient;
import com.anyacht.app.constants.Api_Interface;
import com.anyacht.app.reponse.Booking;
import com.anyacht.app.utils.Appconstants;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BookHistoryFragment extends BaseFragment{
    public static String TAG="bookhistory";


    RecyclerView book_list;

    ArrayList<BookingHistoryDetails> bookingDetailsArrayList;
    BookHistoryAdapter bookHistoryAdapter;


    @Override
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.bookhistory_fragment, container, false);


        book_list =(RecyclerView)view.findViewById(R.id.book_history_list);

        book_list.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        book_list.setLayoutManager(layoutManager);


        LoadBookingHistoryList();



        return view;
    }

    private void LoadBookingHistoryList() {

        loader(true);

        api_interface = ApiClient.getApiClient().create(Api_Interface.class);



        Call<BookingHistory> call1 = api_interface.getBookingHistory(Appconstants.mUserid);

        call1.enqueue(new Callback<BookingHistory>() {
            @Override
            public void onResponse(Call<BookingHistory> call, Response<BookingHistory> response) {

                BookingHistory bookingHistory = response.body();


                if (bookingHistory.status.equalsIgnoreCase("1")) {


                    bookingDetailsArrayList = bookingHistory.details();

                    bookHistoryAdapter = new BookHistoryAdapter(getActivity(), bookingHistory.details(),
                            getActivity());


                    book_list.setAdapter(bookHistoryAdapter);

                } else {


                    Toast.makeText(getActivity(), bookingHistory.getMsg(), Toast.LENGTH_SHORT).show();
                }


                Log.e("Responce", "recived " + bookingHistory.getMsg());
                loader(false);
            }


            @Override
            public void onFailure(Call<BookingHistory> call, Throwable t) {
                Log.e("error", " " + t.getLocalizedMessage());
                Toast.makeText(getActivity(), "Please try after some time", Toast.LENGTH_SHORT).show();

                loader(false);
                call.cancel();
            }
        });

    }

}
