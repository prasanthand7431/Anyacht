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

public class ForgotPass extends BaseActivity {

    EditText emailID;

    Button submit;

    String get_emailID;

    Api_Interface api_interface;

    Toolbar toolbarTop;

    TextView mTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_pass);

        emailID = (EditText) findViewById(R.id.forgotpass_emailid);
        submit = (Button) findViewById(R.id.btn_sub);
        toolbarTop = (Toolbar) findViewById(R.id.toolbar_top);
        mTitle = (TextView) toolbarTop.findViewById(R.id.toolbar_title);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                get_emailID = emailID.getText().toString();

                if (get_emailID.equals("")) {

                    Toast.makeText(getApplicationContext(), "Please enter email id", Toast.LENGTH_LONG).show();
                } else {


                    LoadForgotpass();
                }


            }
        });
    }

    private void LoadForgotpass() {

        loader(true);

        api_interface = ApiClient.getApiClient().create(Api_Interface.class);


        Call<BasicResponse> call1 = api_interface.ForgotPass(get_emailID);
        call1.enqueue(new Callback<BasicResponse>() {
            @Override
            public void onResponse(Call<BasicResponse> call, Response<BasicResponse> response) {
                BasicResponse forgotpassResponse = response.body();

                if (forgotpassResponse.status.equalsIgnoreCase("1")) {

                    Log.e("requst login", "" + forgotpassResponse.msg);

                    Toast.makeText(getApplicationContext(), "Sent Successfully", Toast.LENGTH_LONG).show();

                    Intent ii = new Intent(getApplicationContext(), LoginActivity.class);
                    startActivity(ii);


                } else {


                    Toast.makeText(getApplicationContext(), forgotpassResponse.getMsg(), Toast.LENGTH_SHORT).show();
                }


                Log.e("Responce", "recived " + forgotpassResponse.getMsg());
                loader(false);
            }


            @Override
            public void onFailure(Call<BasicResponse> call, Throwable t) {
                Log.e("error", " " + t.getLocalizedMessage());
                Toast.makeText(ForgotPass.this, "Please try after some time", Toast.LENGTH_SHORT).show();

                loader(false);
                call.cancel();
            }
        });
    }
}
