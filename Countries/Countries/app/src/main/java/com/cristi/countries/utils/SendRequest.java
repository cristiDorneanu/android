package com.cristi.countries.utils;

import android.os.AsyncTask;
import android.widget.TextView;

import com.cristi.countries.R;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by dryflo on 5/27/2016.
 */
public class SendRequest extends AsyncTask<String, Object, String> {
    protected AsyncResponse delegate = null;
    protected AsyncResponse delegateException = null;
    protected String exception = null;

    public SendRequest(AsyncResponse response, AsyncResponse delegateException) {
        this.delegate = response;
        this.delegateException = delegateException;
    }

    @Override
    protected String doInBackground(String... urls) {
        StringBuilder result = new StringBuilder();
        try {
            URL url = new URL(urls[0]);
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.setReadTimeout(15000);

            int responseCode = urlConnection.getResponseCode();

            BufferedReader in = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
            String line;

            while ((line = in.readLine()) != null) {
                result.append(line);
            }

            in.close();
        } catch (IOException e) {
            e.printStackTrace();
            this.exception = "could not connect to server";
            cancel(true);
        }

        return result.toString();
    }

    @Override
    protected void onPostExecute(String result) {
        if (this.exception != null) {
            delegateException.processFinish(this.exception);
        } else {
            delegate.processFinish(result);
        }

    }
}
