package com.cristi.countries.utils;

import com.cristi.countries.entities.Country;

import java.util.List;

/**
 * Created by dryflo on 5/27/2016.
 */
public interface AsyncResponse {
    /**
     * this function will handle the request result
     * if there is an exception it will call errorCallback function from MainActivity
     * to update the TexView with a error message
     * if the request succeeded the resulted JSON will be parsed and a list of Countries wil be formed
     * @param output error message,in case of exception or resulted string in case of success
     * @return
     */
    List<Country> processFinish(String output);
}
