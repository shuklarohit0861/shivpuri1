package com.mcshivpuri.shivpurimunicipalcouncil;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class CommunityHall extends AppCompatActivity {

    Spinner spinnerWard,spinnerCategory,spinnerHall;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_community_hall);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        spinnerWard = (Spinner) findViewById(R.id.spinner_ward_community_hall);
        spinnerCategory = (Spinner) findViewById(R.id.spinner_category_hall);
        spinnerHall = (Spinner) findViewById(R.id.spinner_community_hall);

        ArrayAdapter<CharSequence> ward = ArrayAdapter.createFromResource(this,R.array.ward,android.R.layout.simple_spinner_item);
        ward.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerWard.setAdapter(ward);

        ArrayAdapter<CharSequence> category = ArrayAdapter.createFromResource(this,R.array.category,android.R.layout.simple_spinner_item);
        category.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerCategory.setAdapter(category);





    }

}
