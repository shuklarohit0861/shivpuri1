package com.mcshivpuri.shivpurimunicipalcouncil;

import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.DisplayMetrics;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Locale;

/*
    @author rohit shukla
    this is main Activity of the shivpuri app

 */

public class MainActivity extends AppCompatActivity {

    Button complaintButton,tankerButton,communityButton ;
    Locale myLocale;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        TextView webCall = (TextView)findViewById(R.id.webcall);
        complaintButton = (Button)findViewById(R.id.button_complaint);
        tankerButton = (Button)findViewById(R.id.button_tanker);
        communityButton = (Button)findViewById(R.id.button_hall);





        complaintButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent complaint = new Intent(MainActivity.this,Complaint.class);
                startActivity(complaint);
            }
        });

        tankerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent tanker = new Intent (MainActivity.this,TankerBooking.class);
                startActivity(tanker);
            }
        });

        communityButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent hall = new Intent(MainActivity.this,CommunityHall.class);
                startActivity(hall);
            }
        });


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        if (fab != null) {
            fab.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(MainActivity.this,ContactUs.class);
                    startActivity(intent);

                }
            });
        }

        if (webCall != null) {
            webCall.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(Intent.ACTION_VIEW);
                    String url = "http://www.mcshivpuri.com/index.php";
                    intent.setData(Uri.parse(url));
                    startActivity(intent);
                }
            });
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_main,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem menuItem)
    {
        if(menuItem.getItemId() == R.id.language)
        {
           Intent intent = new Intent(MainActivity.this,Settings.class);
            startActivity(intent);
        }

        return super.onOptionsItemSelected(menuItem);
    }

    public void setLocale(String lang) {

        myLocale = new Locale(lang);
        Resources res = getResources();
        DisplayMetrics dm = res.getDisplayMetrics();
        Configuration conf = res.getConfiguration();
        conf.locale = myLocale;
        res.updateConfiguration(conf, dm);
        Intent refresh = new Intent(this, MainActivity.class);
         startActivity(refresh);
    }




}
