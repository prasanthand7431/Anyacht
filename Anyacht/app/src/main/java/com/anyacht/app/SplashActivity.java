package com.anyacht.app;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.anyacht.app.activity.AnyachtDashboard;
import com.anyacht.app.activity.LoginActivity;
import com.anyacht.app.activity.SignupActivity;
import com.anyacht.app.utils.Appconstants;
import com.anyacht.app.utils.SaveSharedPreference;

public class SplashActivity extends AppCompatActivity implements View.OnClickListener{


        Button login,register;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        login = (Button)findViewById(R.id.btnn_login);
        register =(Button)findViewById(R.id.btnn_signup);

        login.setOnClickListener(this);
        register.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){

            case R.id.btnn_login:


                if(SaveSharedPreference.getEMailID(SplashActivity.this).length() == 0)
                {
                    // call Login Activity
                    Intent ii = new Intent(getApplicationContext() ,LoginActivity.class);
                    startActivity(ii);

                }
                else
                {

                    Appconstants.mUserid = SaveSharedPreference.getID(getApplicationContext());
                    Appconstants.mUsername = SaveSharedPreference.getUsername(getApplicationContext());
                    Appconstants.mEmailId= SaveSharedPreference.getEMailID(getApplicationContext());
                    Appconstants.mMobile= SaveSharedPreference.getPhone(getApplicationContext());


                    // Stay at the current activity.
                    Intent i2 = new Intent(SplashActivity.this, AnyachtDashboard.class);
                    startActivity(i2);
                }


             /*   Intent ii = new Intent(getApplicationContext() ,LoginActivity.class);
                startActivity(ii);*/
                break;

            case R.id.btnn_signup:

                Intent i1 = new Intent(getApplicationContext() ,SignupActivity.class);
                startActivity(i1);
                break;

                default:
                break;
        }
    }
}
