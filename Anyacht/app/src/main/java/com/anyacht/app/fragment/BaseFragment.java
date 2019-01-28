package com.anyacht.app.fragment;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

import com.anyacht.app.constants.ApiClient;
import com.anyacht.app.constants.Api_Interface;


public class BaseFragment extends Fragment {

    Api_Interface api_interface;

    ProgressDialog dialog;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        api_interface = ApiClient.getApiClient().create(Api_Interface.class);
        dialog = new ProgressDialog(getActivity());
    }

    public void loader(boolean visibility) {
        dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        dialog.setMessage("Loading. Please wait...");
        dialog.setIndeterminate(true);
        dialog.setCanceledOnTouchOutside(false);
        if (dialog != null && visibility)
            dialog.show();
        else if (dialog != null && dialog.isShowing())
            dialog.dismiss();
    }



}
