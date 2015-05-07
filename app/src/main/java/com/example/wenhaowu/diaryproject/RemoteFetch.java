package com.example.wenhaowu.diaryproject;

import android.content.Context;
import android.util.Log;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by wenhaowu on 07/05/15.
 * this code if mainly from the turtorial from http://code.tutsplus.com/tutorials/create-a-weather-app-on-android--cms-21587
 */
public class RemoteFetch {
    private static final String OPEN_WEATHER_MAP_API =
            "http://api.openweathermap.org/data/2.5/weather?q=";
    /*
    public static JSONObject getJSON(String city){
        try {
            String temp = OPEN_WEATHER_MAP_API + city;
            URL url = new URL(String.format(temp));

            HttpURLConnection con =
                    (HttpURLConnection)url.openConnection();
            con.setRequestMethod("GET");
            con.setDoInput(true);
            con.setDoOutput(true);
            //- See more at: http://www.survivingwithandroid.com/2013/05/build-weather-app-json-http-android.html#sthash.cTeZzSKY.dpuf
            con.connect();

            Log.e("MyTag", "test");
            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(con.getInputStream()));

            StringBuffer json = new StringBuffer(1024);

            String tmp="";
            while((tmp=reader.readLine())!=null)
                json.append(tmp).append("\n");
            reader.close();

            JSONObject data = new JSONObject(json.toString());

            // This value will be 404 if the request was not
            // successful
            if(data.getInt("cod") != 200){
                return null;
            }

            return data;
        }catch(Exception e){

            Log.e("MyTag", "test22");

            return null;
        }
    }
    */

    public static String getWeatherData(String location) {
        HttpURLConnection con = null ;
        InputStream is = null;

        try {
            String temp = OPEN_WEATHER_MAP_API + location;
            Log.e("MyTag", temp);
            con = (HttpURLConnection) ( new URL(temp)).openConnection();
            con.setRequestMethod("GET");
            con.setDoInput(true);
            con.setDoOutput(true);
            con.connect();

            // Let's read the response
            StringBuffer buffer = new StringBuffer();
            is = con.getInputStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            String line = null;
            while (  (line = br.readLine()) != null )
                buffer.append(line + "\r\n");

            is.close();
            con.disconnect();
            return buffer.toString();
        }
        catch(Throwable t) {
            t.printStackTrace();
        }
        finally {
            try { is.close(); } catch(Throwable t) {}
            try { con.disconnect(); } catch(Throwable t) {}
        }

        return null;

    }
    //- See more at: http://www.survivingwithandroid.com/2013/05/build-weather-app-json-http-android.html#sthash.cTeZzSKY.dpuf
}



