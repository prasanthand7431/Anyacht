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
import com.anyacht.app.constants.ApiClient;
import com.anyacht.app.constants.Api_Interface;
import com.anyacht.app.reponse.BasicResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignupActivity extends BaseActivity {

    String get_userName,get_Userpass,get_mobile,get_emailId;

    EditText UserName,Password,Mobile,EmailId;

    Button SignUp;

    Api_Interface api_interface;

    String get_user_type ="registered";
    Toolbar toolbarTop;
    TextView mTitle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        UserName =(EditText)findViewById(R.id.input_name1);
        Password =(EditText)findViewById(R.id.input_pass1);
        Mobile =(EditText)findViewById(R.id.input_mobile1);
        EmailId =(EditText)findViewById(R.id.input_email1);
        SignUp =(Button) findViewById(R.id.btn_signup);

         toolbarTop = (Toolbar) findViewById(R.id.toolbar_top);
         mTitle = (TextView) toolbarTop.findViewById(R.id.toolbar_title);

        SignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                get_userName = UserName.getText().toString();
                get_Userpass = Password.getText().toString();
                get_mobile = Mobile.getText().toString();
                get_emailId = EmailId.getText().toString();


                if (get_userName.equals("")){

                    Toast.makeText(getApplicationContext() ,"Please enter UserName" ,Toast.LENGTH_LONG).show();
                }else if (get_emailId.equals("")){

                    Toast.makeText(getApplicationContext() ,"Please enter Email Id" ,Toast.LENGTH_LONG).show();


                }else if (get_mobile.equals("")){

                    Toast.makeText(getApplicationContext() ,"Please enter Mobie" ,Toast.LENGTH_LONG).show();

                }else if (get_Userpass.equals("")){

                    Toast.makeText(getApplicationContext() ,"Please enter Password" ,Toast.LENGTH_LONG).show();

                }else{

                    LoadSign();

                }
            }
        });
    }

    private void LoadSign() {

        loader(true);

        api_interface = ApiClient.getApiClient().create(Api_Interface.class);


        Call<BasicResponse> call1 = api_interface.SignUp(get_userName ,get_emailId,get_mobile,get_Userpass,get_user_type);
        call1.enqueue(new Callback<BasicResponse>() {
            @Override
            public void onResponse(Call<BasicResponse> call, Response<BasicResponse> response) {
                BasicResponse signupResponse = response.body();

                if (signupResponse.status.equalsIgnoreCase("1")) {

                    Log.e("requst login",""+ signupResponse.msg);

                    Toast.makeText(getApplicationContext() ,"Register Successfully" ,Toast.LENGTH_LONG).show();

                    Intent ii = new Intent(getApplicationContext() ,LoginActivity.class);
                    startActivity(ii);


                }else{


                    Toast.makeText(getApplicationContext(), signupResponse.getMsg(), Toast.LENGTH_SHORT).show();
                }


                Log.e("Responce", "recived " + signupResponse.getMsg());
                loader(false);
            }


            @Override
            public void onFailure(Call<BasicResponse> call, Throwable t) {
                Log.e("error", " " + t.getLocalizedMessage());
                Toast.makeText(SignupActivity.this, "Please try after some time", Toast.LENGTH_SHORT).show();

                loader(false);
                call.cancel();
            }
        });
    }
}
