package com.anyacht.app.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.anyacht.app.R;
import com.anyacht.app.utils.SaveSharedPreference;
import com.anyacht.app.fragment.BookHistoryFragment;
import com.anyacht.app.fragment.ChangePassFragment;
import com.anyacht.app.fragment.HomeFragment;
import com.anyacht.app.utils.Appconstants;

public class AnyachtDashboard extends BaseActivity
        implements NavigationView.OnNavigationItemSelectedListener {


    TextView super_name,super_emailId;

    Toolbar toolbar;

    String get_id,get_username,get_email_id,get_pass,get_phone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_anyacht_dashboard);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);



       Bundle bundle = getIntent().getExtras();

        if (bundle!=null){

            get_id = bundle.getString("UID");
            get_username = bundle.getString("UNAME");
            get_email_id = bundle.getString("UEMAIL");
            get_pass= bundle.getString("UPASS");
            get_phone= bundle.getString("UMOBILE");

            if (!get_id.equals("") && !get_id.equals(null) ||
                    !get_username.equals("") && !get_username.equals(null)
                    || !get_email_id.equals("") && !get_email_id.equals(null)
                    || !get_pass.equals("") && !get_pass.equals(null)
                    || !get_phone.equals("") && !get_phone.equals(null)){


                Appconstants.mUserid = get_id;
                Appconstants.mUsername = get_username;
                Appconstants.mEmailId = get_email_id;
                Appconstants.mMobile = get_phone;
                Appconstants.mPass = get_pass;
            }

            SaveSharedPreference.setID(getApplicationContext() , Appconstants.mUserid );
            SaveSharedPreference.setUsername(getApplicationContext() ,Appconstants.mUsername);
            SaveSharedPreference.setEMailID(getApplicationContext() ,Appconstants.mEmailId);
            SaveSharedPreference.setPass(getApplicationContext() ,Appconstants.mPass);
            SaveSharedPreference.setPhone(getApplicationContext() ,Appconstants.mMobile);


        }


        Fragment fragment;
        fragment = getSupportFragmentManager().findFragmentByTag(HomeFragment.TAG);

        if (fragment == null) {
            fragment = new HomeFragment();
        }
        getSupportFragmentManager().beginTransaction().replace(R.id.container_navigation, fragment, HomeFragment.TAG).commit();

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        View nav_header = navigationView.inflateHeaderView(R.layout.nav_header_anyacht_dashboard);
        nav_header.findViewById(R.id.lin_head1);

        super_name = (TextView)nav_header.findViewById(R.id.superviors_name);
        super_emailId = (TextView)nav_header.findViewById(R.id.superviors_email);


        super_name.setText(Appconstants.mUsername);
        super_emailId.setText(Appconstants.mEmailId);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            //super.onBackPressed();

        }
    }

    private void LogOut() {


        AlertDialog.Builder builder = new AlertDialog.Builder(AnyachtDashboard.this);
        builder.setMessage("Do you want to close this application ?")
                .setCancelable(false)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {


                        Toast.makeText(AnyachtDashboard.this, "Successfully logged out", Toast.LENGTH_LONG).show();

                        SaveSharedPreference.removePrefs(getApplicationContext());


                        Intent intent = new Intent(AnyachtDashboard.this, LoginActivity.class);
                        startActivity(intent);

                        finish();
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        //  Action for 'NO' Button
                        dialog.cancel();
                    }
                });
        AlertDialog alert = builder.create();
        alert.setTitle("Log out");
        alert.show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.anyacht_dashboard, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {

            LogOut();

            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        String title = item.getTitle().toString();
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        switch (item.getItemId()) {

            case R.id.nav_home:

                reloadActivity();


                break;

            case R.id.nav_change_pass:

                ChangePassFragment changePassFragment = new ChangePassFragment();
                fragmentTransaction.replace(R.id.container_navigation, changePassFragment, ChangePassFragment.TAG);
                fragmentTransaction.addToBackStack(ChangePassFragment.TAG);
                fragmentTransaction.commit();


                break;
            case R.id.nav_booklist:

                BookHistoryFragment bookHistoryFragment = new BookHistoryFragment();
                fragmentTransaction.replace(R.id.container_navigation, bookHistoryFragment, BookHistoryFragment.TAG);
                fragmentTransaction.addToBackStack(BookHistoryFragment.TAG);
                fragmentTransaction.commit();


                break;




            case R.id.nav_logout:

                LogOut();
                break;
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        setTitle(title);

        return true;
    }

    public void reloadActivity() {
        Intent objIntent = new Intent(AnyachtDashboard.this, AnyachtDashboard.class);
        startActivity(objIntent);
    }

}
