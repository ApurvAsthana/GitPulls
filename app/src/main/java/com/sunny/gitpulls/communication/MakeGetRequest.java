package com.sunny.gitpulls.communication;

import android.os.AsyncTask;
import android.util.Log;

import com.sunny.gitpulls.utils.EndpointDetails;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class MakeGetRequest extends AsyncTask{

    private static final String TAG = "MakeGetRequest";
    private String endPoint;
    private OnHttpConnListener onHttpConnListener;
    private static final int CONN_TIMEOUT = 3000;

    public MakeGetRequest(String endPoint,OnHttpConnListener obj){
        this.endPoint = endPoint;
        this.onHttpConnListener = obj;
    }
    public static String makeGetRequest(String request, OnHttpConnListener onHttpConnListener){
        InputStream inputStream = null;
        HttpURLConnection conn = null;
        String result = null;
        try {
            Log.d("TAG","making get request");
            URL url = new URL(EndpointDetails.getRequestEndpoint());
            conn = (HttpURLConnection)url.openConnection();
            conn.setReadTimeout(CONN_TIMEOUT);
            conn.setConnectTimeout(CONN_TIMEOUT);
            conn.setRequestMethod("GET");
            //conn.addRequestProperty("state", "open");
            conn.connect();

            int responseCode = conn.getResponseCode();
            if (responseCode != HttpURLConnection.HTTP_OK) {
                if (onHttpConnListener != null) {
                    onHttpConnListener.onUpdate("connection response not OK",OnHttpConnListener.ERROR_OCC);
                }
            }

            inputStream = conn.getInputStream();
            if (inputStream != null){
                result = readStringStream(inputStream);
                inputStream.close();
            } else {
                if (onHttpConnListener != null) {
                    onHttpConnListener.onUpdate("input stream null",OnHttpConnListener.STREAM_EMPTY);
                }
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
            if (onHttpConnListener != null) {
                onHttpConnListener.onUpdate("malformed URL",OnHttpConnListener.MALFORMED_URL);
            }
        } catch (IOException e) {
            e.printStackTrace();
            if (onHttpConnListener != null) {
                onHttpConnListener.onUpdate("IO exception occurred",OnHttpConnListener.IO_EXC);
            }
        } finally {
            if (conn != null) {
                conn.disconnect();
            }
            onHttpConnListener.onUpdate(result,HttpURLConnection.HTTP_OK);
        }

        if (result == null) result = "";
        return result;
    }

    private static String readStringStream(InputStream stream)
            throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(stream));
        StringBuilder result = new StringBuilder();
        String line;
        while((line = reader.readLine()) != null) {
            result.append(line);
        }
        return result.toString();
    }

    @Override
    protected Object doInBackground(Object[] objects) {
        return makeGetRequest(endPoint,onHttpConnListener);
    }

    @Override
    protected void onPostExecute(Object o) {
        super.onPostExecute(o);
    }

}
