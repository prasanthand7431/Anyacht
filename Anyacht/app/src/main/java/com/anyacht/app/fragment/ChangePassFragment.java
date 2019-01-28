package com.anyacht.app.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.anyacht.app.activity.AnyachtDashboard;
import com.anyacht.app.utils.Appconstants;
import com.anyacht.app.reponse.BasicResponse;
import com.anyacht.app.R;
import com.anyacht.app.constants.ApiClient;
import com.anyacht.app.constants.Api_Interface;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ChangePassFragment extends BaseFragment {
    public static String TAG="changepass";

    EditText pass1,pass2;

    String get_pass1,get_pass2;

    Button change_btn;


    @Override
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.changepass_fragment, container, false);

        pass1 =(EditText)view.findViewById(R.id.change_pass);
        pass2 =(EditText)view.findViewById(R.id.comfirm_pass);
        change_btn =(Button)view.findViewById(R.id.comfirm_pass_btn);
        pass1.setText(Appconstants.mPass);


        change_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                get_pass1 = pass1.getText().toString();
                get_pass2 = pass2.getText().toString();



                if (get_pass1.equals("")){

                    Toast.makeText(getActivity() ,"Please Enter Password" ,Toast.LENGTH_LONG).show();

                }else if (get_pass2.equals("")){

                    Toast.makeText(getActivity() ,"Please Confirm Password" ,Toast.LENGTH_LONG).show();
                }else{

                    LoadConfirmPass();
                }
            }
        });


        return view;
    }

    private void LoadConfirmPass() {

        loader(true);

        api_interface = ApiClient.getApiClient().create(Api_Interface.class);


        Call<BasicResponse> call1 = api_interface.ChangePassWord(Appconstants.mUserid,Appconstants.mPass,get_pass2);
        call1.enqueue(new Callback<BasicResponse>() {
            @Override
            public void onResponse(Call<BasicResponse> call, Response<BasicResponse> response) {
                BasicResponse changepassResponse = response.body();

                if (changepassResponse.status.equalsIgnoreCase("1")) {

                    Log.e("requst login",""+ changepassResponse.msg);

                    Toast.makeText(getActivity(),"Password Changed Successfully" ,Toast.LENGTH_LONG).show();

                    Intent ii = new Intent(getActivity() ,AnyachtDashboard.class);
                    startActivity(ii);


                }else{


                    Toast.makeText(getActivity(), changepassResponse.getMsg(), Toast.LENGTH_SHORT).show();
                }


                Log.e("Responce", "recived " + changepassResponse.getMsg());
                loader(false);
            }


            @Override
            public void onFailure(Call<BasicResponse> call, Throwable t) {
                Log.e("error", " " + t.getLocalizedMessage());
                Toast.makeText(getActivity(), "Please try after some time", Toast.LENGTH_SHORT).show();

                loader(false);
                call.cancel();
            }
        });


    }
}
