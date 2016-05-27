package com.cristi.countries.utils;

import android.app.Activity;

import com.cristi.countries.activities.MainActivity;
import com.cristi.countries.entities.Country;

import java.util.List;

/**
 * Created by dryflo on 5/27/2016.
 */
public class HandleException implements AsyncResponse {
    private MainActivity mainActivity;

    public HandleException(MainActivity mainActivity){
        this.mainActivity=mainActivity;
    }

    @Override
    public List<Country> processFinish(String output) {
        mainActivity.errorCallback(output);

        return null;
    }
}
