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
 * Created by thero on 28-06-2016.
 */
public class CommunityHallTask extends AsyncTask<String, Void, String> {

    AlertDialog alertDialog;
    Context ctx;
    ProgressDialog progressDialog;

    CommunityHallTask(Context ctx) {
        this.ctx = ctx;
    }

    @Override
    protected void onPreExecute() {

        //alertDialog.setTitle("Fill the form....");
        progressDialog = new ProgressDialog(ctx);
        progressDialog.setCancelable(false);
        progressDialog.setTitle("Processing");
        progressDialog.setMessage("please wait ......");
        progressDialog.show();
    }

    @Override
    protected String doInBackground(String... params) {
        String insertDBURL = "http://localhost/CommunityHallBookActivity.PHP";
        String response = "-1";
        String Name = params[0];
        String Address = params[1];
        String Ward = params[2];
        String Mobile = params[3];
        String Caste = params[4];
        String CommName = params[5];
        String DateTo = params[6];
        String DateFrom = params[7];
        String Purpose = params[8];


        try {
            URL url = new URL(insertDBURL);
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setRequestMethod("POST");
            httpURLConnection.setDoOutput(true);
            //httpURLConnection.setDoInput(true);
            OutputStream OS = httpURLConnection.getOutputStream();
            BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(OS, "UTF-8"));

            String data = URLEncoder.encode("Name", "UTF-8") + "=" + URLEncoder.encode(Name, "UTF-8") + "&" +
                    URLEncoder.encode("Address", "UTF-8") + "=" + URLEncoder.encode(Address, "UTF-8") + "&" +
                    URLEncoder.encode("Ward", "UTF-8") + "=" + URLEncoder.encode(Ward, "UTF-8") + "&" +
                    URLEncoder.encode("Mobile", "UTF-8") + "=" + URLEncoder.encode(Mobile, "UTF-8") + "&" +
                    URLEncoder.encode("Caste", "UTF-8") + "=" + URLEncoder.encode(Caste, "UTF-8") + "&" +
                    URLEncoder.encode("CommName", "UTF-8") + "=" + URLEncoder.encode(CommName, "UTF-8") + "&" +
                    URLEncoder.encode("DateTo", "UTF-8") + "=" + URLEncoder.encode(DateTo, "UTF-8") + "&" +
                    URLEncoder.encode("DateFrom", "UTF-8") + "=" + URLEncoder.encode(DateFrom, "UTF-8") + "&" +
                    URLEncoder.encode("Purpose", "UTF-8") + "=" + URLEncoder.encode(Purpose, "UTF-8");

            bufferedWriter.write(data);
            bufferedWriter.flush();
            bufferedWriter.close();
            OS.close();
            //  InputStream IS = httpURLConnection.getInputStream();
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
            alertDialog.setMessage("Your Hall booking Id is " + result);
            alertDialog.show();
        }
    }


}
