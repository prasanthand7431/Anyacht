package com.anyacht.app.activity;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.anyacht.app.R;


/**
 * Created by Vignesh on 17/07/18.
 */

@SuppressLint("Registered")
public class BaseActivity extends AppCompatActivity {


    private ProgressDialog progress;
    private ProgressDialog dialog;



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dialog = new ProgressDialog(this); // this = YourActivity

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;

        }
        return super.onOptionsItemSelected(item);
    }

    public void setToolBarAndTitle(String toolBarAndTitle) {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        //toolbar.setTitleTextColor(android.graphics.Color.BLACK);
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setTitle(toolBarAndTitle);
    }

    public void startActivityApp(Intent intent) {
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
        startActivity(intent);
        finish();
    }

    public void showLoadingDialog(Context context) {

        if (progress == null) {
            progress = new ProgressDialog(context);
            progress.setTitle("Loading...");
            progress.setMessage(getString(R.string.loading_message));
            progress.setCancelable(false);
        }
        progress.show();
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

    public void dismissLoadingDialog() {

        if (progress != null && progress.isShowing()) {
            progress.dismiss();
        }
    }


    public void showErrorToast(String msg) {
        Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_SHORT).show();
    }

    public void startActivity(Intent intent, boolean finish) {
        overridePendingTransition(0, 0);
        super.startActivity(intent);
        if (finish)
            finish();
    }


}
