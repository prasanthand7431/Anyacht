package com.anyacht.app.activity;

import android.app.DatePickerDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import com.anyacht.app.R;

import java.util.Calendar;

public class Search_yacht extends AppCompatActivity {

    Spinner spinner_hours;

    EditText checkin_date;

    Button search_sub;

    AutoCompleteTextView city_edit;

    RadioGroup radioGroup;

    RadioButton radioButton,radioButton1;

    String date;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_yacht);

        spinner_hours =(Spinner)findViewById(R.id.spin);
        checkin_date =(EditText) findViewById(R.id.txt_date);
        search_sub =(Button) findViewById(R.id.btn_search);
        radioGroup =(RadioGroup) findViewById(R.id.radiogroups);

        checkin_date.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {

                final Calendar calendar = Calendar.getInstance();
                int yy = calendar.get(Calendar.YEAR);
                int mm = calendar.get(Calendar.MONTH);
                int dd = calendar.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog datePicker = new DatePickerDialog(Search_yacht.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {

                        date = String.valueOf(dayOfMonth) + "/" + String.valueOf(monthOfYear+1)
                                + "/" + String.valueOf(year);
                        checkin_date.setText(date);
                    }
                }, yy, mm, dd);
                datePicker.show();
            }


        });

        search_sub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // get selected radio button from radioGroup
                int selectedId = radioGroup.getCheckedRadioButtonId();

                // find the radiobutton by returned id
                radioButton = (RadioButton) findViewById(R.id.radioss1);
                radioButton1 = (RadioButton) findViewById(R.id.radioss2);

                Toast.makeText(Search_yacht.this,
                        radioButton.getText(), Toast.LENGTH_SHORT).show();

            }
        });
    }
}
