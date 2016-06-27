package com.mcshivpuri.shivpurimunicipalcouncil;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Base64;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.Spinner;

import java.io.ByteArrayOutputStream;


public class Complaint extends AppCompatActivity implements View.OnClickListener {

    Spinner spinnerComplaintType,spinnerComplaintCategory,wardComplaint;
    ImageButton imageButton;
    Bitmap imageBitmap;
    EditText editTextLocation, editTextDescribeComplaint, editTextFirstName, editTextLastName, editTextAddress1, editTextAddress2, editTextArea, editTextMobile, editTextEmail;
    RadioButton radioButtonEmailNotify, radioButtonDoNotNotify;
    String base64CamImage = "iVBORw0KGgoAAAANSUhEUgAAABAAAAAQCAYAAAAf8/9hAAAABGdBTUEAAK/INwWK6QAAABl0RVh0U29mdHdhcmUAQWRvYmUgSW1hZ2VSZWFkeXHJZTwAAAFhSURBVDjLpZMxbtZAEIW/N/bP7w4aSiQuQEODEFdIAVI6KFJwgeQenAEBFQVU1CBOkAsgroDSRMof77wUa68tK5GQstqVd1fz3rw345Vt7jN6gM8/zj9k6u3lIYer8ZaoTY5dD8OOj+9fPz/tAdJ6d/TqyeNhGCR1eMIkAMIGez6bMl7z/eefE6ASXF7lfr8f9OX3P0oxY2b9lmQspkznkibTnB0/paQEEACHESI6hKhTTa7mrepegsxNDWhyadAaLIQJCQssiAA3kxuCBpKRRMhkCBlCVW8a1p1rBPYCXjKKTrNRkOvCuougkkTULA4tHRQ4IVb1aQSeCJbMJlZgTdlTqsRwt4LqddUFJms2YWPfpsBugRFTRWffEkojs4CnH6sRaLoNQbImEWlXZV7L3xRx2OmCvH745sUj0Ozd89wMMY4H+k5uBA96ff326+/LQ/Gz/3mcfQe74FNt7T2f8w1Fi68/h3owMgAAAABJRU5ErkJggg==";
    CoordinatorLayout complaintCoordinateLayout;
    static final int REQUEST_IMAGE_CAPTURE = 1;
    private static final String EMPTY = "EMPTY";
    String checkedNotify = "NO";
    String stringComplaintType, stringComplaintWard, stringComplaintCategory;
    private Button submit, reset;
    byte[] byteImage;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_complaint);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        findViewIdAll();
        imageButton.setOnClickListener(this);
        radioButtonDoNotNotify.setChecked(true);
        reset.setOnClickListener(this);
        submit.setOnClickListener(this);


        ArrayAdapter<CharSequence> complaintAdapter = ArrayAdapter.createFromResource(this,R.array.complaint_type,android.R.layout.simple_spinner_item);
        complaintAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerComplaintType.setAdapter(complaintAdapter);
        spinnerComplaintType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                stringComplaintType = parent.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        ArrayAdapter<CharSequence> complaintCategory = ArrayAdapter.createFromResource(this,R.array.complaint_category,android.R.layout.simple_spinner_item);
        complaintCategory.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerComplaintCategory.setAdapter(complaintCategory);
        spinnerComplaintCategory.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                stringComplaintCategory = parent.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        ArrayAdapter<CharSequence> wardAdapter = ArrayAdapter.createFromResource(this,R.array.ward,android.R.layout.simple_spinner_item);
        wardAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        wardComplaint.setAdapter(wardAdapter);
        wardComplaint.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                stringComplaintWard = parent.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }

    @Override
    public void onClick(View v) {
        if (v == imageButton) {
            dispatchTakePictureIntent();
        }
        if (v == submit) {
            submit();
        }
        if (v == reset) {
            reset();
        }
    }

    private void findViewIdAll() {
        spinnerComplaintType = (Spinner) findViewById(R.id.spinner_complaint_registration);
        spinnerComplaintCategory = (Spinner) findViewById(R.id.spinner_complaint_category);
        wardComplaint = (Spinner) findViewById(R.id.spinner_ward);
        imageButton = (ImageButton) findViewById(R.id.complaint_image_button);
        submit = (Button) findViewById(R.id.submit_button_complaint);
        reset = (Button) findViewById(R.id.reset_button_complaint);
        editTextLocation = (EditText) findViewById(R.id.edit_text_location_complaint);
        editTextDescribeComplaint = (EditText) findViewById(R.id.edit_text_describe_complaint);
        editTextFirstName = (EditText) findViewById(R.id.edit_text_first_name_complaint);
        editTextLastName = (EditText) findViewById(R.id.edit_text_last_name_complaint);
        editTextAddress1 = (EditText) findViewById(R.id.edit_text_add1_complaint);
        editTextAddress2 = (EditText) findViewById(R.id.edit_text_add2_complaint);
        editTextArea = (EditText) findViewById(R.id.edit_text_area);
        editTextMobile = (EditText) findViewById(R.id.edit_text_phone);
        editTextEmail = (EditText) findViewById(R.id.edit_text_email_complaint);
        radioButtonEmailNotify = (RadioButton) findViewById(R.id.radioButtonEmail);
        radioButtonDoNotNotify = (RadioButton) findViewById(R.id.radioButtonDoNotNotify);
        complaintCoordinateLayout = (CoordinatorLayout) findViewById(R.id.complaint_Layout);

    }

    private void dispatchTakePictureIntent() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            imageBitmap = (Bitmap) extras.get("data");
            imageButton.setImageBitmap(imageBitmap);
            ByteArrayOutputStream bitmaparray = new ByteArrayOutputStream();
            imageBitmap.compress(Bitmap.CompressFormat.PNG, 100, bitmaparray);
            byteImage = bitmaparray.toByteArray();
            base64CamImage = Base64.encodeToString(byteImage, 0);
        }
    }

    public boolean validate() {
        boolean allOk = true;
        if (TextUtils.isEmpty(editTextLocation.getText())) {
            editTextLocation.setError(getString(R.string.general_error));
            editTextLocation.setFocusable(true);
            allOk = false;
        }
        if (TextUtils.isEmpty(editTextDescribeComplaint.getText())) {
            editTextDescribeComplaint.setError(getString(R.string.general_error));
            allOk = false;
        }

        if (radioButtonEmailNotify.isChecked()) {
            checkedNotify = "YES";
        }
        return allOk;
    }

    public void reset() {
        editTextLocation.setText("");
        editTextLocation.setError(null);
        editTextDescribeComplaint.setText("");
        editTextDescribeComplaint.setError(null);
        editTextFirstName.setText("");
        editTextLastName.setText("");
        editTextAddress1.setText("");
        editTextAddress2.setText("");
        editTextArea.setText("");
        editTextMobile.setText("");
        editTextEmail.setText("");
    }

    public void submit() {
        if (validate()) {
            if (!isNetworkAvailable()) {
                Snackbar.make(complaintCoordinateLayout, getString(R.string.internetConnection), Snackbar.LENGTH_LONG).show();
            } else {
                ComplaintRegistrationTask complaintRegistrationTask = new ComplaintRegistrationTask(this);
                complaintRegistrationTask.execute(stringComplaintType
                        , stringComplaintWard
                        , editTextLocation.getText().toString()
                        , editTextDescribeComplaint.getText().toString()
                        , TextUtils.isEmpty(editTextFirstName.getText()) ? EMPTY : editTextFirstName.getText().toString()
                        , TextUtils.isEmpty(editTextLastName.getText()) ? EMPTY : editTextLastName.getText().toString()
                        , TextUtils.isEmpty(editTextAddress1.getText()) ? EMPTY : editTextAddress1.getText().toString()
                        , TextUtils.isEmpty(editTextAddress2.getText()) ? EMPTY : editTextAddress2.getText().toString()
                        , TextUtils.isEmpty(editTextArea.getText()) ? EMPTY : editTextArea.getText().toString()
                        , TextUtils.isEmpty(editTextMobile.getText()) ? EMPTY : editTextMobile.getText().toString()
                        , TextUtils.isEmpty(editTextEmail.getText()) ? EMPTY : editTextEmail.getText().toString()
                        , checkedNotify
                        , stringComplaintCategory
                        , base64CamImage
                );
            }
        }

    }

    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

    @Override
    public void onSaveInstanceState(Bundle outBound) {
        super.onSaveInstanceState(outBound);
        if (byteImage != null) {
            outBound.putByteArray("IMAGE", byteImage);
        }
    }

    @Override
    public void onRestoreInstanceState(Bundle saveInstanceState) {
        super.onRestoreInstanceState(saveInstanceState);
        byte[] imageData = saveInstanceState.getByteArray("IMAGE");
        if (imageData != null) {
            byteImage = imageData;
            Bitmap image = BitmapFactory.decodeByteArray(imageData, 0, imageData.length);
            imageButton.setImageBitmap(image);
        }
    }



}
