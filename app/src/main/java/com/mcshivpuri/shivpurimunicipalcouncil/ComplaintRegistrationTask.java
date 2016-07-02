package com.mcshivpuri.shivpurimunicipalcouncil;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

/**
 * Created by Rohit shukla on 30-06-2016.
 */
public class ComplaintRegistrationTask extends AsyncTask<String, Void, String> {

    ProgressDialog progressDialog;
    AlertDialog alertDialog;
    Context ctx;
    String file;

    public ComplaintRegistrationTask(Context context) {

        this.ctx = context;
    }

    @Override
    protected void onPreExecute() {

        progressDialog = new ProgressDialog(ctx);
        progressDialog.setCancelable(false);
        progressDialog.setTitle("Processing");
        progressDialog.setMessage("please wait ......");
        progressDialog.show();

    }

    @Override
    protected String doInBackground(String... params) {
        String response = "-1";

        String insertDBURL = "http:///ComplaintRegAct.PHP";


        String ComplaintType = params[0];
        String Ward = params[1];
        String Location = params[2];
        String Complaint = params[3];
        String Name = params[4];
        String LastName = params[5];
        String Address1 = params[6];
        String Address2 = params[7];
        String Area = params[8];
        String PhoneNo = params[9];
        String Email = params[10];
        String Notify = params[11];
        String com_id = params[12];
        file = params[13];


        try {
            URL url = new URL(insertDBURL);
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setRequestMethod("POST");
            httpURLConnection.setDoOutput(true);
            OutputStream OS = httpURLConnection.getOutputStream();
            BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(OS, "UTF-8"));

            String data = URLEncoder.encode("ComplaintType", "UTF-8") + "=" + URLEncoder.encode(ComplaintType, "UTF-8") + "&" +
                    URLEncoder.encode("Ward", "UTF-8") + "=" + URLEncoder.encode(Ward, "UTF-8") + "&" +
                    URLEncoder.encode("Location", "UTF-8") + "=" + URLEncoder.encode(Location, "UTF-8") + "&" +
                    URLEncoder.encode("Complaint", "UTF-8") + "=" + URLEncoder.encode(Complaint, "UTF-8") + "&" +
                    URLEncoder.encode("Name", "UTF-8") + "=" + URLEncoder.encode(Name, "UTF-8") + "&" +
                    URLEncoder.encode("LastName", "UTF-8") + "=" + URLEncoder.encode(LastName, "UTF-8") + "&" +
                    URLEncoder.encode("Address1", "UTF-8") + "=" + URLEncoder.encode(Address1, "UTF-8") + "&" +
                    URLEncoder.encode("Address2", "UTF-8") + "=" + URLEncoder.encode(Address2, "UTF-8") + "&" +
                    URLEncoder.encode("Area", "UTF-8") + "=" + URLEncoder.encode(Area, "UTF-8") + "&" +
                    URLEncoder.encode("PhoneNo", "UTF-8") + "=" + URLEncoder.encode(PhoneNo, "UTF-8") + "&" +
                    URLEncoder.encode("Email", "UTF-8") + "=" + URLEncoder.encode(Email, "UTF-8") + "&" +
                    URLEncoder.encode("Notify", "UTF-8") + "=" + URLEncoder.encode(Notify, "UTF-8") + "&" +
                    URLEncoder.encode("com_id", "UTF-8") + "=" + URLEncoder.encode(com_id, "UTF-8") + "&" +
                    URLEncoder.encode("Image", "UTF-8") + "=" + URLEncoder.encode(file, "UTF-8");

            bufferedWriter.write(data);
            bufferedWriter.flush();
            bufferedWriter.close();
            OS.close();
            InputStream IS = new BufferedInputStream(httpURLConnection.getInputStream());
            BufferedReader streamReader = new BufferedReader(new InputStreamReader(IS, "UTF-8"));
            response = streamReader.readLine();
            IS.close();
            //httpURLConnection.connect();
            httpURLConnection.disconnect();
        } catch (MalformedURLException e) {
            e.printStackTrace();
            return response;
        } catch (IOException e) {
            e.printStackTrace();
            return response;
        }

        return response;
    }

    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }

    /*@Override
    protected void onPostExecute(String result) {
        if(result.equals("form submitted successfully"))
        {
            Toast.makeText(ctx, result, Toast.LENGTH_LONG).show();
        }

    }*/

    @Override
    protected void onPostExecute(String result) {
        progressDialog.dismiss();
        alertDialog = new AlertDialog.Builder(ctx).create();
        if (result.equals("-1")) {
            alertDialog.setMessage("Your Request cannot be processed Try again later " + result);
            alertDialog.show();
        } else {

            alertDialog.setMessage("Your Complaint Id is " + result);
            alertDialog.show();
        }
    }
}
