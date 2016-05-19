package hu.ait.android.happy;

import android.os.AsyncTask;

import com.google.gson.Gson;

import org.greenrobot.eventbus.EventBus;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import hu.ait.android.happy.data.WeatherResult;


public class HttpGetTask extends AsyncTask<String, Void, String> {


    @Override
    protected String doInBackground(String... params) {
        String result = "";

        HttpURLConnection conn = null;
        InputStream is = null;
        try {
            URL url = new URL(params[0]);
            conn = (HttpURLConnection) url.openConnection();
            is = conn.getInputStream();

            StringBuffer sb = new StringBuffer();

            int b;
            while ((b = is.read())!=-1) {
                sb.append((char)b);
            }

            result = sb.toString();
        } catch (Exception e) {
            result = e.getMessage();
        } finally {
            if (is != null) {
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (conn != null) {
                conn.disconnect();
            }
        }

        return result;
    }

    @Override
    protected void onPostExecute(String getResult) {
        try{
            WeatherResult result = new Gson().fromJson(getResult, WeatherResult.class);
            EventBus.getDefault().post(result);
        }
        catch(Exception e){
            System.out.println("Warning: Need to be connected to WIFI");
        }


    }
}
