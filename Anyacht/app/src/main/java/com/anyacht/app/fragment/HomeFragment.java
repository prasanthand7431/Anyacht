package com.anyacht.app.fragment;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TimePicker;
import android.widget.Toast;

import com.anyacht.app.adapter.BookingAdapter;
import com.anyacht.app.utils.Appconstants;
import com.anyacht.app.activity.BookingList;
import com.anyacht.app.reponse.Destination;
import com.anyacht.app.model.DestinationDetails;
import com.anyacht.app.activity.OverNightBookingList;
import com.anyacht.app.R;
import com.anyacht.app.constants.ApiClient;
import com.anyacht.app.constants.Api_Interface;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeFragment extends BaseFragment {
    public static String TAG = "home";

    Spinner destination;

    String date_time1, get_hour_item, dates, get_dest_id, get_dest_name, get_item;

    Api_Interface api_interface;


    RadioGroup radioGroup;

    RadioButton radioButton, radioButton1;

    Button search;

    EditText date_picker, date_picker1;

    Spinner spinner_hours;


    String get_statuss = "0";
    String get_status = "1";


    LinearLayout linearLayout, linearLayout1;


    ArrayAdapter<String> adapter;


    String date_time;

    int mYear;
    int mMonth;
    int mDay;

    int mHour;
    int second = 0;
    int mMinute;

    String get_dest_ids = "2";
    String get_datetimer;


    String get_datetimer1;
    List list;


    @Override
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.activity_search_yacht, container, false);

        destination = (Spinner) view.findViewById(R.id.spin_destination);
        radioGroup = (RadioGroup) view.findViewById(R.id.radiogroups);
        radioButton = (RadioButton) view.findViewById(R.id.radioss1);
        radioButton1 = (RadioButton) view.findViewById(R.id.radioss2);
        search = (Button) view.findViewById(R.id.btn_search);
        date_picker = (EditText) view.findViewById(R.id.txt_date);
        date_picker1 = (EditText) view.findViewById(R.id.txt_date_out);
        spinner_hours = (Spinner) view.findViewById(R.id.spin);

        linearLayout = (LinearLayout) view.findViewById(R.id.linear_spiner);
        linearLayout1 = (LinearLayout) view.findViewById(R.id.linear_checkout);


        date_picker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                get_date_picker();

            }
        });

        date_picker1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                get_date_picker1();

            }
        });


        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {


                radioButton = (RadioButton) group.findViewById(checkedId);

                Log.d("checknameList", radioButton.getText().toString());


                get_item = radioButton.getText().toString();


                Log.d("checkItem", get_item);

                if (get_item.equals("Day Sailling")) {

                    linearLayout.setVisibility(View.VISIBLE);
                    linearLayout1.setVisibility(View.GONE);
                    Log.d("Checkstatuss", get_statuss);


                } else if (get_item.equals("Overnight Sailing")) {


                    linearLayout.setVisibility(View.GONE);

                    linearLayout1.setVisibility(View.VISIBLE);

                    Log.d("Checkstatus", get_status);

                } else {


                }


            }
        });

        spinner_hours.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                get_hour_item = parent.getItemAtPosition(position).toString();

                Log.d("CheckHours", get_hour_item);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                String get_datein = date_picker.getText().toString();
                String get_dateout = date_picker1.getText().toString();


                if ((get_item.equals("Day Sailling"))) {

                    get_status = "0";

                    get_datetimer = date_picker.getText().toString();

                    if (get_datein.equals("") || get_hour_item.equals("Select Hours")) {

                        Toast.makeText(getActivity(), "Please Enter Fields", Toast.LENGTH_LONG).show();

                    } else {

                        Log.d("Checkstatusss", get_status);
                        Log.d("Checkhours", get_hour_item);

                        Intent ii = new Intent(getActivity(), BookingList.class);
                        ii.putExtra("destID", get_dest_id);
                        ii.putExtra("typeID", get_status);
                        ii.putExtra("HourS", get_hour_item);
                        ii.putExtra("checkIN", get_datetimer);
                        startActivity(ii);


                    }


                } else if (get_item.equals("Overnight Sailing")) {


                    if (get_datein.equals("") || get_dateout.equals("")) {

                        Toast.makeText(getActivity(), "Please Enter Fields", Toast.LENGTH_LONG).show();

                    } else {


                        get_status = "1";

                        get_datetimer = date_picker.getText().toString();

                        get_datetimer1 = date_picker1.getText().toString();

                        Intent ii = new Intent(getActivity(), OverNightBookingList.class);
                        ii.putExtra("destID", get_dest_id);
                        ii.putExtra("typeID", get_status);
                        ii.putExtra("checkIN", get_datetimer);
                        ii.putExtra("checkOUT", get_datetimer1);
                        startActivity(ii);


                    }


                } else {


                }


            }
        });



        LoadCityName();


        return view;
    }


    private void get_date_picker1() {

        // Get Current Date
        final Calendar c = Calendar.getInstance();
        mYear = c.get(Calendar.YEAR);
        mMonth = c.get(Calendar.MONTH);
        mDay = c.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(getActivity(),
                new DatePickerDialog.OnDateSetListener() {

                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {

                        date_time1 = year + "-" + (monthOfYear + 1) + "-" + dayOfMonth;

                        date_picker1.setText(date_time1);

                        Log.d("CheckDate1", date_time1);
                        //*************Call Time Picker Here ********************
                        tiemPicker1();
                    }
                }, mYear, mMonth, mDay);
        datePickerDialog.show();

    }

    private void tiemPicker1() {


        // Get Current Time
        final Calendar c = Calendar.getInstance();
        mHour = c.get(Calendar.HOUR_OF_DAY);
        mMinute = c.get(Calendar.MINUTE);

        // Launch Time Picker Dialog
        TimePickerDialog timePickerDialog = new TimePickerDialog(getActivity(),
                new TimePickerDialog.OnTimeSetListener() {

                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {


                        mHour = hourOfDay;
                        mMinute = minute;


                        date_picker1.setText(date_time1 + " " + hourOfDay + ":" + minute + ":" + second + second);
                        Log.d("checktime1", date_time1 + " " + hourOfDay + ":" + minute + ":" + second + second);


                    }
                }, mHour, mMinute, false);
        timePickerDialog.show();
    }

    private void get_date_picker() {


        // Get Current Date
        final Calendar c = Calendar.getInstance();
        mYear = c.get(Calendar.YEAR);
        mMonth = c.get(Calendar.MONTH);
        mDay = c.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(getActivity(),
                new DatePickerDialog.OnDateSetListener() {

                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {

                        date_time = year + "-" + (monthOfYear + 1) + "-" + dayOfMonth;

                        date_picker.setText(date_time);

                        Log.d("CheckDate1", date_time);
                        //*************Call Time Picker Here ********************
                        tiemPicker();
                    }
                }, mYear, mMonth, mDay);
        datePickerDialog.show();
    }

    private void tiemPicker() {

        // Get Current Time
        final Calendar c = Calendar.getInstance();
        mHour = c.get(Calendar.HOUR_OF_DAY);
        mMinute = c.get(Calendar.MINUTE);

        // Launch Time Picker Dialog
        TimePickerDialog timePickerDialog = new TimePickerDialog(getActivity(),
                new TimePickerDialog.OnTimeSetListener() {

                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {


                        mHour = hourOfDay;
                        mMinute = minute;


                        date_picker.setText(date_time + " " + hourOfDay + ":" + minute + ":" + second + second);
                        Log.d("checktime1", date_time + " " + hourOfDay + ":" + minute + ":" + second + second);


                    }
                }, mHour, mMinute, false);
        timePickerDialog.show();


    }


    private void LoadCityName() {

        loader(true);

        api_interface = ApiClient.getApiClient().create(Api_Interface.class);


        Call<Destination> call1 = api_interface.getDestination(Appconstants.mUserid);

        call1.enqueue(new Callback<Destination>() {
            @Override
            public void onResponse(Call<Destination> call, Response<Destination> response) {

                Destination destinationresponse = response.body();

               // destinationDetailsArrayList = destinationresponse.getdetails();

                if (destinationresponse.status.equalsIgnoreCase("1")) {

                    Log.e("requst login", "" + destinationresponse.msg);

                    //destinationDetailsArrayList = destinationresponse.getdetails();


                    final String[] items = new String[destinationresponse.getdetails().size()];
                    final String[] destination_id = new String[destinationresponse.getdetails().size()];
                    //Traversing through the whole list to get all the names
                    for(int i=0; i<destinationresponse.getdetails().size(); i++){
                        //Storing names to string array
                        items[i] =destinationresponse.getdetails().get(i).getName();
                        destination_id[i] =destinationresponse.getdetails().get(i).getId();

                        Log.d("CheckDesId1",destination_id[i]);

                    }




                    adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, items);
                    //setting adapter to spinner
                    destination.setAdapter(adapter);
                    //Creating an array adapter for list view


                    destination.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                            get_dest_name = items[position];
                            get_dest_id = destination_id[position];
                            Log.e("CheckDESNAME",""+get_dest_name);
                            Log.e("CheckDESIDD",""+get_dest_id);


                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> parent) {

                        }
                    });

                } else {


                    Toast.makeText(getActivity(), destinationresponse.getMsg(), Toast.LENGTH_SHORT).show();
                }


                Log.e("Responce", "recived " + destinationresponse.getMsg());
                loader(false);
            }


            @Override
            public void onFailure(Call<Destination> call, Throwable t) {
                Log.e("error", " " + t.getLocalizedMessage());
                Toast.makeText(getActivity(), "Please try after some time", Toast.LENGTH_SHORT).show();

                loader(false);
                call.cancel();
            }
        });

    }


}
