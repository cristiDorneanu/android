package com.cristi.countries.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.cristi.countries.R;
import com.cristi.countries.utils.HandleException;
import com.cristi.countries.utils.HandleResponse;
import com.cristi.countries.utils.SendRequest;

public class MainActivity extends Activity {
    private final static String URL = "https://restcountries.eu/rest/v1/all";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * this function will switch to countries list activity when list countries button is pressed
     * and will make a request (GET) for all countries from resource
     * @param v
     */
    public void onListCountriesClick(View v) {
        HandleResponse handleResponse = new HandleResponse(this);
        HandleException handleError = new HandleException(this);
        SendRequest asyncTask = new SendRequest(handleResponse,handleError);

        asyncTask.execute(URL);

        Intent intent = new Intent(getApplicationContext(), CountriesListActivity.class);
        startActivity(intent);
    }

    /**
     * is called in case of exception when making a request
     * @param error the error message that will be printed on screen
     */
    public void errorCallback(String error){
        TextView errorOutput=(TextView) findViewById(R.id.errorHandlingLabel);
        errorOutput.setText(error);
    }
}
