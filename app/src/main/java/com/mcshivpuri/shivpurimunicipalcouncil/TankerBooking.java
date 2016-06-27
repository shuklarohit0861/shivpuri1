package com.mcshivpuri.shivpurimunicipalcouncil;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class TankerBooking extends AppCompatActivity {

    Spinner spinnerWardTanker,spinnerTypeTanker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tanker_booking);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        spinnerWardTanker = (Spinner) findViewById(R.id.spinner_ward_tanker);
        spinnerTypeTanker = (Spinner)findViewById(R.id.spinner_type_of_tanker);

        ArrayAdapter<CharSequence> ward = ArrayAdapter.createFromResource(this,R.array.ward,android.R.layout.simple_spinner_item);
        ward.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerWardTanker.setAdapter(ward);

        ArrayAdapter<CharSequence> tankerType = ArrayAdapter.createFromResource(this,R.array.tanker_type,android.R.layout.simple_spinner_item);
        tankerType.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerTypeTanker.setAdapter(tankerType);




    }

}
