package com.example.wenhaowu.diaryproject;

import android.app.Activity;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.EditText;
import android.widget.ImageView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.ref.WeakReference;

/**
 * Created by wenhaowu on 07/05/15.
 * Have been reading the tutorial http://www.survivingwithandroid.com/2013/05/build-weather-app-json-http-android.html
 * Got most of code from there
 */
public class JSONWeatherTask extends AsyncTask<String, Void, String>{

    private WeakReference<Activity> ActivityRef ;

    public JSONWeatherTask(Activity activity){
        ActivityRef = new WeakReference<Activity>(activity);
    }

    @Override
    protected String doInBackground(String... params) {
        try {
            String data = (new RemoteFetch()).getWeatherData(params[0]);
            Log.e("myTag", data);
            return data;
        }catch (Exception e){
            e.printStackTrace();
            throw e;
        }
    }

    @Override
    protected void onPostExecute(String s) {

        super.onPostExecute(s);

        String detail = "No Data";
        try {
            detail = getWeather(s);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        final Activity activity = ActivityRef.get();
        if (activity != null){
            EditText t_weather = (EditText) activity.findViewById(R.id.Edit_weather);
            t_weather.setText(detail);
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
