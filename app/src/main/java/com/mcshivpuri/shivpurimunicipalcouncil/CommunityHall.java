package com.mcshivpuri.shivpurimunicipalcouncil;

import android.app.DatePickerDialog;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class CommunityHall extends AppCompatActivity implements View.OnClickListener {

    Spinner spinnerWard,spinnerCategory,spinnerHall;
    DatePickerDialog datePickerDialogTO, datePickerDialogFrom;
    private EditText nameHall, addressHall, mobileHall, dateToHall, dateFromHall, reasonHall;
    SimpleDateFormat dateFormat;
    private Button submit, reset;
    String stringWard, stringCategory, stringHall;
    String stringReason = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_community_hall);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        FindViewById();
        setDate();
        reset.setOnClickListener(this);
        submit.setOnClickListener(this);

        dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.US);
        ArrayAdapter<CharSequence> ward = ArrayAdapter.createFromResource(this,R.array.ward,android.R.layout.simple_spinner_item);
        ward.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerWard.setAdapter(ward);
        spinnerWard.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                stringWard = parent.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        ArrayAdapter<CharSequence> category = ArrayAdapter.createFromResource(this,R.array.category,android.R.layout.simple_spinner_item);
        category.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerCategory.setAdapter(category);
        spinnerCategory.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                stringCategory = parent.getSelectedItem().toString();
                // Log.v("THE CATEGORY",stringCategory);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        ArrayAdapter<CharSequence> communityHall = ArrayAdapter.createFromResource(this, R.array.community_hall, android.R.layout.simple_spinner_item);
        communityHall.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerHall.setAdapter(communityHall);
        spinnerHall.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                stringHall = parent.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }

    private void setDate() {
        dateToHall.setOnClickListener(this);
        dateFromHall.setOnClickListener(this);

        Calendar calendar = Calendar.getInstance();

        datePickerDialogTO = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                Calendar calendar = Calendar.getInstance();
                calendar.set(year, monthOfYear, dayOfMonth);
                dateToHall.setText(dateFormat.format(calendar.getTime()));
                if (dateToHall.getError() != null) {
                    dateToHall.setError(null);
                }
            }
        }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));

        datePickerDialogFrom = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                Calendar calendar = Calendar.getInstance();
                calendar.set(year, monthOfYear, dayOfMonth);
                dateFromHall.setText(dateFormat.format(calendar.getTime()));
                if (dateFromHall.getError() != null) {
                    dateFromHall.setError(null);
                }
            }
        }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
    }

    private void FindViewById() {
        spinnerWard = (Spinner) findViewById(R.id.spinner_ward_community_hall);
        spinnerCategory = (Spinner) findViewById(R.id.spinner_category_hall);
        spinnerHall = (Spinner) findViewById(R.id.spinner_community_hall);
        nameHall = (EditText) findViewById(R.id.edit_text_name_hall);
        addressHall = (EditText) findViewById(R.id.edit_text_add_hall);
        mobileHall = (EditText) findViewById(R.id.edit_text_phone_hall);
        dateFromHall = (EditText) findViewById(R.id.edit_text_date_from_hall);
        dateToHall = (EditText) findViewById(R.id.edit_date_to_hall);
        reasonHall = (EditText) findViewById(R.id.edit_text_reason_hall);
        submit = (Button) findViewById(R.id.submit_button_hall);
        reset = (Button) findViewById(R.id.reset_button_hall);
    }


    @Override
    public void onClick(View v) {

        if (v == dateToHall) {
            datePickerDialogTO.show();
        } else if (v == dateFromHall) {
            datePickerDialogFrom.show();
        } else if (v == reset) {
            resetHall();
        } else if (v == submit) {
            submitData();
        }
    }

    public void resetHall() {
        nameHall.setText("");
        nameHall.setError(null);
        addressHall.setText("");
        addressHall.setError(null);
        mobileHall.setText("");
        mobileHall.setError(null);
        dateFromHall.setText("");
        dateFromHall.setError(null);
        dateToHall.setText("");
        dateToHall.setError(null);
        reasonHall.setText("");
        reasonHall.setError(null);
    }

    public boolean validate() {
        boolean allOk = true;
        if (TextUtils.isEmpty(nameHall.getText())) {
            nameHall.setError(getString(R.string.name_error));
            allOk = false;
        }
        if (TextUtils.isEmpty(addressHall.getText())) {
            addressHall.setError(getString(R.string.address_error));
            allOk = false;
        }
        if (TextUtils.isEmpty(mobileHall.getText())) {
            mobileHall.setError(getString(R.string.moblie_error));
            allOk = false;
        }
        if (TextUtils.isEmpty(dateFromHall.getText())) {
            dateFromHall.setError("Date From can't be empty");
            allOk = false;
        }
        if (TextUtils.isEmpty(dateToHall.getText())) {
            dateToHall.setError("Date to can't be empty");
            allOk = false;
        }
        return allOk;
    }

    public void submitData() {
        if (reasonHall.getText().equals(null)) {
            stringReason = " ";
        } else {
            stringReason = reasonHall.getText().toString();
        }

        if (!isNetworkAvailable()) {
            Toast.makeText(CommunityHall.this, getString(R.string.internetConnection), Toast.LENGTH_LONG).show();
        }
        if (isNetworkAvailable() && validate()) {
            CommunityHallTask communityHallTask = new CommunityHallTask(this);
            communityHallTask.execute(nameHall.getText().toString(), addressHall.getText().toString(), stringWard
                    , mobileHall.getText().toString(), stringCategory, stringHall, dateToHall.getText().toString()
                    , dateFromHall.getText().toString(), stringReason);
        }


    }

    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }



}
