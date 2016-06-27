package com.mcshivpuri.shivpurimunicipalcouncil;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class Complaint extends AppCompatActivity {

    Spinner spinnerComplaintType,spinnerComplaintCategory,wardComplaint;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_complaint);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

       spinnerComplaintType = (Spinner) findViewById(R.id.spinner_complaint_registration);
        ArrayAdapter<CharSequence> complaintAdapter = ArrayAdapter.createFromResource(this,R.array.complaint_type,android.R.layout.simple_spinner_item);
        complaintAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerComplaintType.setAdapter(complaintAdapter);

        spinnerComplaintCategory = (Spinner) findViewById(R.id.spinner_complaint_category);
        ArrayAdapter<CharSequence> complaintCategory = ArrayAdapter.createFromResource(this,R.array.complaint_category,android.R.layout.simple_spinner_item);
        complaintCategory.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerComplaintCategory.setAdapter(complaintCategory);

        wardComplaint = (Spinner) findViewById(R.id.spinner_ward);
        ArrayAdapter<CharSequence> wardAdapter = ArrayAdapter.createFromResource(this,R.array.ward,android.R.layout.simple_spinner_item);
        wardAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        wardComplaint.setAdapter(wardAdapter);





    }

}
