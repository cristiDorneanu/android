package com.cristi.countries.activities;

import android.app.Activity;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

import com.cristi.countries.R;
import com.cristi.countries.entities.Country;
import com.cristi.countries.utils.HandleResponse;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dryflo on 5/26/2016.
 */
public class CountriesListActivity extends Activity {
    private List<String> countriesListFromRequest = new ArrayList<>();
    private ListView countriesList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_countries_list);
        final ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, countriesListFromRequest);

        try {
            for (Country country : HandleResponse.getCountries()) {
                adapter.add(country.getName());
            }
        } catch (NullPointerException e) {
            adapter.add("no countries were retrieved from server");
        }

        countriesList = (ListView) findViewById(R.id.countriesList);
        countriesList.setAdapter(adapter);

        countriesList.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getApplicationContext(), HandleResponse.getCountries().get(position).getName() + " was selected", Toast.LENGTH_SHORT).show();

                buildAlert(HandleResponse.getCountries().get(position).getCapital(), HandleResponse.getCountries().get(position).getPopulation());
            }
        });
    }

    /**
     * this function will generate a new alert for each element in country list when selected
     * @param capital the capital of the selected country
     * @param population the population number of the selected country
     */
    public void buildAlert(String capital, String population) {
        String message = "This country has the capital in " + capital + " and a population of " + population + " people";
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);

        alertDialog.setTitle("Country information");
        alertDialog.setMessage(message);

        alertDialog.setPositiveButton("close",
                new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(getApplicationContext(), "You clicked on close", Toast.LENGTH_SHORT).show();
                    }
                });

        alertDialog.show();
    }
}
