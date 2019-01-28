package com.anyacht.app.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.anyacht.app.R;
import com.anyacht.app.SplashActivity;
import com.anyacht.app.reponse.User;
import com.anyacht.app.model.UserDetails;
import com.anyacht.app.constants.ApiClient;
import com.anyacht.app.constants.Api_Interface;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends BaseActivity {

    EditText username, password;

    TextView forgotpass;

    String get_forgotpass;

    String get_username, get_pass;

    Api_Interface api_interface;

    Button login_btn;

    String get_id, get_name, get_password, get_email, get_phone;


    Toolbar toolbarTop;

    TextView mTitle;

    ArrayList<UserDetails> userDetailsArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        username = (EditText) findViewById(R.id.input_name);
        password = (EditText) findViewById(R.id.input_pass);
        login_btn = (Button) findViewById(R.id.btn_login);
        forgotpass = (TextView) findViewById(R.id.txt_forgorpass);
        toolbarTop = (Toolbar) findViewById(R.id.toolbar_top);
        mTitle = (TextView) toolbarTop.findViewById(R.id.toolbar_title);

        forgotpass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i1 = new Intent(getApplicationContext(), ForgotPass.class);
                startActivity(i1);
            }
        });


        login_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                get_username = username.getText().toString();
                get_pass = password.getText().toString();

                if (get_username.equals("")) {

                    Toast.makeText(getApplicationContext(), "Please Enter Username", Toast.LENGTH_LONG).show();
                } else if (get_pass.equals("")) {


                    Toast.makeText(getApplicationContext(), "Please Enter Password", Toast.LENGTH_LONG).show();

                } else {


                    LoadLoginActivity();

                }
            }
        });
    }

    private void LoadLoginActivity() {

        loader(true);

        api_interface = ApiClient.getApiClient().create(Api_Interface.class);


        Call<User> call1 = api_interface.Login(get_username, get_pass);
        call1.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {

                User loginResponse = response.body();

                Log.e("keshav", "packageResponse 1 --> " + loginResponse.toString());
                if (loginResponse.getStatus().equalsIgnoreCase("1")) {


                    userDetailsArrayList = loginResponse.Login();


                    Log.d("CheckInvoicess", "" + userDetailsArrayList.size());

                    for (int i = 0; i < userDetailsArrayList.size(); i++) {

                        get_id = userDetailsArrayList.get(i).getId();
                        get_name = userDetailsArrayList.get(i).getName();
                        get_email = userDetailsArrayList.get(i).getEmail();
                        get_phone = userDetailsArrayList.get(i).getPhone();

                    }

                    Intent ii = new Intent(getApplicationContext(), AnyachtDashboard.class);
                    ii.putExtra("UID", get_id);
                    ii.putExtra("UNAME", get_name);
                    ii.putExtra("UEMAIL", get_email);
                    ii.putExtra("UPASS", get_pass);
                    ii.putExtra("UMOBILE", get_phone);
                    startActivity(ii);


                    Log.d("CheckMSG", get_id + get_email);


                } else {

                    Toast.makeText(getApplicationContext(), " " + loginResponse.getMsg(), Toast.LENGTH_SHORT).show();

                }

                loader(false);
            }


            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Log.e("error", " " + t.getLocalizedMessage());
                Toast.makeText(LoginActivity.this, "Please try after some time", Toast.LENGTH_SHORT).show();

                loader(false);
                call.cancel();
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

        Intent i1 = new Intent(getApplicationContext() , SplashActivity.class);
        startActivity(i1);
    }
}
