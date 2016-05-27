package com.cristi.countries.utils;

import com.cristi.countries.activities.MainActivity;
import com.cristi.countries.entities.Country;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dryflo on 5/27/2016.
 */
public class HandleResponse implements AsyncResponse {
    private static List<Country> countries = new ArrayList<>();
    private MainActivity mainActivity = null;

    public HandleResponse(MainActivity activity) {
        this.mainActivity = activity;
    }

    @Override
    public List<Country> processFinish(String output) {
        countries.clear();

        try {
            JSONArray finalResult = new JSONArray(output);

            for (int currentJsonObject = 0; currentJsonObject < finalResult.length(); currentJsonObject++) {
                JSONObject jsonObject = finalResult.getJSONObject(currentJsonObject);
                if (jsonObject.getString("region").equals("Europe")) {
                    this.countries.add(new Country(jsonObject.getString("name"), jsonObject.getString("capital"), jsonObject.getString("population")));
                }
            }

        } catch (JSONException | NullPointerException e) {
            mainActivity.errorCallback("invalid file format");
        }

        return this.countries;
    }

    public static List<Country> getCountries() {
        return countries;
    }

    public static void setCountries(List<Country> countries) {
        HandleResponse.countries = countries;
    }
}
