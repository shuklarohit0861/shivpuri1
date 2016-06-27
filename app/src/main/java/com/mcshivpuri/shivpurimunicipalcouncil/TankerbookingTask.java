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
 * Created by thero on 29-06-2016.
 */
public class TankerbookingTask extends AsyncTask<String, Void, String> {
    AlertDialog alertDialog;
    ProgressDialog progressDialog;
    Context ctx;

    TankerbookingTask(Context ctx) {
        this.ctx = ctx;
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

        String insertDBURL = "";


        String Name = params[0];
        String Address1 = params[1];
        String Address2 = params[2];
        String Ward = params[3];
        String Mobile = params[4];
        String Email = params[5];
        String DateTime = params[6];
        String TankerType = params[7];
        String NumberOfTankers = params[8];
        String Reason = params[9];
        String Amount = params[10];
        String TotalAmount = params[11];

        try {
            URL url = new URL(insertDBURL);
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setRequestMethod("POST");
            httpURLConnection.setDoOutput(true);
            //httpURLConnection.setDoInput(true);
            OutputStream OS = httpURLConnection.getOutputStream();
            BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(OS, "UTF-8"));

            String data = URLEncoder.encode("Name", "UTF-8") + "=" + URLEncoder.encode(Name, "UTF-8") + "&" +
                    URLEncoder.encode("Address1", "UTF-8") + "=" + URLEncoder.encode(Address1, "UTF-8") + "&" +
                    URLEncoder.encode("Address2", "UTF-8") + "=" + URLEncoder.encode(Address2, "UTF-8") + "&" +
                    URLEncoder.encode("Ward", "UTF-8") + "=" + URLEncoder.encode(Ward, "UTF-8") + "&" +
                    URLEncoder.encode("Mobile", "UTF-8") + "=" + URLEncoder.encode(Mobile, "UTF-8") + "&" +
                    URLEncoder.encode("Email", "UTF-8") + "=" + URLEncoder.encode(Email, "UTF-8") + "&" +
                    URLEncoder.encode("DateTime", "UTF-8") + "=" + URLEncoder.encode(DateTime, "UTF-8") + "&" +
                    URLEncoder.encode("TankerType", "UTF-8") + "=" + URLEncoder.encode(TankerType, "UTF-8") + "&" +
                    URLEncoder.encode("NumberOfTankers", "UTF-8") + "=" + URLEncoder.encode(NumberOfTankers, "UTF-8") + "&" +
                    URLEncoder.encode("Reason", "UTF-8") + "=" + URLEncoder.encode(Reason, "UTF-8") + "&" +
                    URLEncoder.encode("Amount", "UTF-8") + "=" + URLEncoder.encode(Amount, "UTF-8") + "&" +
                    URLEncoder.encode("TotalAmount", "UTF-8") + "=" + URLEncoder.encode(TotalAmount, "UTF-8");

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


    @Override
    protected void onPostExecute(String result) {
        progressDialog.dismiss();
        alertDialog = new AlertDialog.Builder(ctx).create();
        if (result.equals("-1")) {
            alertDialog.setMessage("Your Request cannot be processed Try again later " + result);
            alertDialog.show();
        } else {
            alertDialog.setMessage("Your Tanker booking Id is " + result);
            alertDialog.show();
        }
    }

}
