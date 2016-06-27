package com.mcshivpuri.shivpurimunicipalcouncil;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class TankerBooking extends AppCompatActivity implements View.OnClickListener {

    Spinner spinnerWardTanker,spinnerTypeTanker;
    private EditText editTextNameTanker, editTextAdd1Tanker, editTextAdd2Tanker, editTextMobileTanker, editTextDateTanker, editTextTimeTanker, editTextNumberTanker, editTextReasonTanker, editTextEmail;
    private String stringWardTanker, stringTypeTanker, stringEmailTanker, stingReason;
    private TextView textViewTotalAmount, textViewTankerTypePrice;
    SimpleDateFormat dateFormat;
    int tankerTypePos = 0;
    int totalAmount = 0;
    Button submit, reset;
    private CheckBox checkBoxCondition;
    private CoordinatorLayout coordinatorLayout;


    private DatePickerDialog datePickerDialog;
    private TimePickerDialog timePickerDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tanker_booking);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        findViewById();
        setDateTime();
        reset.setOnClickListener(this);
        submit.setOnClickListener(this);

        dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.US);
        ArrayAdapter<CharSequence> ward = ArrayAdapter.createFromResource(this,R.array.ward,android.R.layout.simple_spinner_item);
        ward.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerWardTanker.setAdapter(ward);
        spinnerWardTanker.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                stringWardTanker = parent.getSelectedItem().toString();


            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        final ArrayAdapter<CharSequence> tankerType = ArrayAdapter.createFromResource(this, R.array.tanker_type, android.R.layout.simple_spinner_item);
        tankerType.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerTypeTanker.setAdapter(tankerType);
        spinnerTypeTanker.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                stringTypeTanker = parent.getSelectedItem().toString();
                tankerTypePos = position;
                if (position == 0) {
                    textViewTankerTypePrice.setText("* 250");
                } else if (position == 1) {
                    textViewTankerTypePrice.setText("* 350");
                }
                String total = String.valueOf(totalPrice());
                if (!total.equals("0")) {
                    textViewTotalAmount.setText(total);
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        editTextNumberTanker.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (editTextNumberTanker.getText().toString() == "0") {
                    editTextNumberTanker.setError("please put the valid number");
                } else {
                    String total = String.valueOf(totalPrice());
                    textViewTotalAmount.setText(total);
                }

            }
        });
    }

    public void findViewById() {
        spinnerWardTanker = (Spinner) findViewById(R.id.spinner_ward_tanker);
        spinnerTypeTanker = (Spinner) findViewById(R.id.spinner_type_of_tanker);
        editTextNameTanker = (EditText) findViewById(R.id.edit_text_name_tanker);
        editTextAdd1Tanker = (EditText) findViewById(R.id.edit_text_add1_tankerbooking);
        editTextAdd2Tanker = (EditText) findViewById(R.id.edit_text_add2_tanker);
        editTextMobileTanker = (EditText) findViewById(R.id.edit_text_mobile_tanker);
        editTextDateTanker = (EditText) findViewById(R.id.edit_text_date_tanker);
        editTextTimeTanker = (EditText) findViewById(R.id.edit_text_time_tanker);
        editTextNumberTanker = (EditText) findViewById(R.id.no_of_tanker);
        editTextReasonTanker = (EditText) findViewById(R.id.edit_text_reason_tanker);
        editTextEmail = (EditText) findViewById(R.id.edit_text_email_tanker);
        textViewTankerTypePrice = (TextView) findViewById(R.id.text_view_tankerType);
        textViewTotalAmount = (TextView) findViewById(R.id.text_view_totalAmount);
        submit = (Button) findViewById(R.id.submit_button_tanker);
        reset = (Button) findViewById(R.id.reset_button_tanker);
        checkBoxCondition = (CheckBox) findViewById(R.id.checkbox_tanker_condition);
        coordinatorLayout = (CoordinatorLayout) findViewById(R.id.coordinatorLayout);
    }


    @Override
    public void onClick(View v) {
        if (editTextTimeTanker == v) {
            timePickerDialog.show();
        }
        if (editTextDateTanker == v) {
            datePickerDialog.show();
        }
        if (v == reset) {
            reset();
        }
        if (v == submit) {
            submit();
        }

    }

    private void setDateTime() {
        editTextDateTanker.setOnClickListener(this);
        editTextTimeTanker.setOnClickListener(this);
        Calendar calendar = Calendar.getInstance();
        datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                Calendar calendar = Calendar.getInstance();
                calendar.set(year, monthOfYear, dayOfMonth);
                editTextDateTanker.setText(dateFormat.format(calendar.getTime()));
            }
        }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));

        timePickerDialog = new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                editTextTimeTanker.setText(hourOfDay + ":" + minute);
            }
        }, calendar.get(Calendar.HOUR_OF_DAY), calendar.get(Calendar.MINUTE), false);
    }

    public int totalPrice() {
        int no_of_Tanker = 0;
        if (!TextUtils.isEmpty(editTextNumberTanker.getText())) {
            no_of_Tanker = Integer.parseInt(editTextNumberTanker.getText().toString());
            textViewTotalAmount.setVisibility(View.VISIBLE);
            if (tankerTypePos == 0) {
                return totalAmount = no_of_Tanker * 250;
            } else if (tankerTypePos == 1) {
                return totalAmount = no_of_Tanker * 350;
            }

        }
        return 0;
    }

    private void reset() {
        editTextNameTanker.setText("");
        editTextAdd1Tanker.setText("");
        editTextAdd2Tanker.setText("");
        editTextMobileTanker.setText("");
        editTextDateTanker.setText("");
        editTextTimeTanker.setText("");
        editTextNumberTanker.setText("");
        editTextReasonTanker.setText("");

        editTextNameTanker.setError(null);
        editTextMobileTanker.setError(null);
        editTextAdd1Tanker.setError(null);
        editTextAdd2Tanker.setError(null);
        editTextDateTanker.setError(null);
        editTextTimeTanker.setError(null);
        editTextNumberTanker.setText(null);
        editTextReasonTanker.setError(null);
    }

    private void submit() {
        if (validate()) {
            boolean check = checkBoxCondition.isChecked();
            if (checkBoxCondition.isChecked()) {
                if (!isNetworkAvailable()) {
                    Toast.makeText(TankerBooking.this, getString(R.string.internetConnection), Toast.LENGTH_LONG).show();
                } else {
                    TankerbookingTask tankerbookingTask = new TankerbookingTask(TankerBooking.this);
                    tankerbookingTask.execute(editTextNameTanker.getText().toString(), editTextAdd1Tanker.getText().toString()
                            , editTextAdd2Tanker.getText().toString()
                            , stringWardTanker
                            , editTextMobileTanker.getText().toString()
                            , stringEmailTanker
                            , editTextDateTanker.getText().toString() + editTextTimeTanker.getText().toString()
                            , stringTypeTanker
                            , editTextNumberTanker.getText().toString()
                            , editTextReasonTanker.getText().toString()
                            , textViewTankerTypePrice.getText().toString()
                            , textViewTotalAmount.getText().toString());
                }
            } else {

                Snackbar.make(coordinatorLayout, getString(R.string.checkbox), Snackbar.LENGTH_LONG).show();
            }
        }
    }

    private boolean validate() {
        boolean allOk = true;
        // @var allOk false when ever any of the fields are empty or not valid

        // ValidateAll validate = new ValidateAll();

        if (TextUtils.isEmpty(editTextNameTanker.getText())) {
            editTextNameTanker.setError(getString(R.string.name_error));
            allOk = false;
        }
        if (TextUtils.isEmpty(editTextAdd1Tanker.getText())) {
            editTextAdd1Tanker.setError(getString(R.string.address_error));
            allOk = false;
        }
        if (TextUtils.isEmpty(editTextAdd2Tanker.getText())) {
            editTextAdd2Tanker.setError(getString(R.string.address_error));
            allOk = false;
        }
        if (TextUtils.isEmpty(editTextMobileTanker.getText())) {
            editTextMobileTanker.setError(getString(R.string.moblie_error));
            allOk = false;
        }
        if (TextUtils.isEmpty(editTextEmail.getText())) {
            stringEmailTanker = "EMPTY";
        } else if (!TextUtils.isEmpty(editTextEmail.getText())) {
            stringEmailTanker = editTextEmail.getText().toString();
        }

        if (TextUtils.isEmpty(editTextDateTanker.getText())) {
            editTextDateTanker.setError(getString(R.string.general_error));
            allOk = false;
        }
        if (TextUtils.isEmpty(editTextTimeTanker.getText())) {
            editTextTimeTanker.setError(getString(R.string.general_error));
            allOk = false;
        }
        if (TextUtils.isEmpty(editTextNumberTanker.getText())) {
            editTextNumberTanker.setError(getString(R.string.general_error));
            allOk = false;
        }


        if (TextUtils.isEmpty(editTextReasonTanker.getText())) {
            stingReason = "EMPTY";
        } else if (!TextUtils.isEmpty(editTextReasonTanker.getText())) {
            stingReason = editTextReasonTanker.getText().toString();
        }
        return allOk;
    }

    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }
}
