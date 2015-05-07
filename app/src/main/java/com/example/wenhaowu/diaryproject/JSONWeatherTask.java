package com.example.wenhaowu.diaryproject;

import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by wenhaowu on 07/05/15.
 * Have been reading the tutorial http://www.survivingwithandroid.com/2013/05/build-weather-app-json-http-android.html
 * Got most of code from there
 */
public class JSONWeatherTask extends AsyncTask<String, Void, String>{
    @Override
    protected String doInBackground(String... params) {
        try {
            String data = (new RemoteFetch()).getWeatherData(params[0]);
            return data;
        }catch (Exception e){
            e.printStackTrace();
            throw e;
        }
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        try {
            getWeather(s);
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    public static String getWeather(String data) throws JSONException{
        JSONObject jObj = new JSONObject(data);
        JSONArray jArr = jObj.getJSONArray("weather");
        JSONObject JSONWeather = jArr.getJSONObject(0);
        String detail = JSONWeather.getString("description");
        Log.e("MyTag", detail);
        return detail;
    }
}
