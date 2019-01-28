package com.anyacht.app.activity;

import android.annotation.SuppressLint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;

import com.anyacht.app.R;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

public class YatchDetailsActivity extends AppCompatActivity {

    String get_image, get_descr, get_price, get_peoplemax;


    TextView price, people;

    ImageView imageView;
    WebView webView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_yatch_details);

        imageView = (ImageView) findViewById(R.id.yacht_img);
        webView = (WebView) findViewById(R.id.web_view);

        price = (TextView) findViewById(R.id.txt_max_price);

        people = (TextView) findViewById(R.id.txt_max_people);


        Bundle bundle = getIntent().getExtras();

        if (bundle != null) {

            get_image = bundle.getString("yatchimage");
            get_descr = bundle.getString("yatchdetails");
            get_price = bundle.getString("yatchprice");
            get_peoplemax = bundle.getString("yatchpeoplemax");


            Log.d("imag", get_image);
            Log.d("desri", get_descr);
        }

        price.setText(get_price+" "+"Per Hour");

        people.setText(get_peoplemax +" "+"Persons");


        webView.loadData(get_descr, "text/html", "UTF-8");

        Glide.with(YatchDetailsActivity.this).load(get_image)
                .thumbnail(0.5f)
                .crossFade()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(imageView);


    }
}
