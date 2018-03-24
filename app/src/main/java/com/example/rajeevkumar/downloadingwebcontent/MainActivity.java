package com.example.rajeevkumar.downloadingwebcontent;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.concurrent.ExecutionException;

public class MainActivity extends AppCompatActivity {

    // AsyncTask way of running my code in different thread than the theard i use too.
    // Runs code in the background
    // Async task takes three variables
    //String First variable will send speicifc location,Void middle variable will put name of particular methods
    // String third will return by DownlaodTask bar

    public class DownLoadTask extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... urls) {

            String result = "";
            URL url;
            HttpURLConnection urlConnection = null;

            try {

                url = new URL(urls[0]);

                urlConnection = (HttpURLConnection) url.openConnection();

                InputStream in = urlConnection.getInputStream();

                InputStreamReader reader = new InputStreamReader(in);

                int data = reader.read();

                while (data != -1) {

                    char current = (char) data;

                    result += current;

                    data = reader.read();
                }

                return result;

            } catch (Exception e)

            {
                //will give details of the exception
                e.printStackTrace();
            }

            return "Done";
        }

    }
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);

            DownLoadTask task = new DownLoadTask();

            String result = null;

            try {

                result = task.execute("https://www.ecowebhosting.co.uk/").get();

            } catch (InterruptedException e) {

                e.printStackTrace();

            } catch (ExecutionException e) {

                e.printStackTrace();
            }

            Log.i("contents of URL", result);
        }

    }
