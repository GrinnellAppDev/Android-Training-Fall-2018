package com.example.tapiwaapp;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class DogActivity extends AppCompatActivity {

    ImageView imageView;
    TextView pageVisitsCount, dogslept;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dog);
        setTitle("DogActivity");

        imageView = findViewById(R.id.dogView);
        pageVisitsCount = findViewById(R.id.dogPageVisitsCount);
        dogslept = findViewById(R.id.dogSleepingTime);

        loadDogImage();
        incrementPageVisit();
    }

    private void loadDogImage() {

        Picasso.get()
                .load("http://i.imgur.com/DvpvklR.png")
                .placeholder(R.drawable.ic_launcher_background)
                .into(imageView);

        //Instead of Picasso you can also use Glide as shown here:

//        Glide.with(this)
//                .load("http://goo.gl/gEgYUd")
//                .into(imageView);

    }

    private void incrementPageVisit() {
        //Get the current page visits
        SharedPreferences sharedPref = getPreferences(Context.MODE_PRIVATE);
        int currentPageVisits = sharedPref.getInt("dogPageVisits", 0);

        //Increment pagevisits by 1
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putInt("dogPageVisits", ++currentPageVisits);
        editor.commit();

        //show updated currentPageVisits
        pageVisitsCount.setText("Page visited " + currentPageVisits + " times");
    }

    public void runSleep(View view) {
        AsyncTaskRunner runner = new AsyncTaskRunner();
        String sleepTime = "5";
        runner.execute(sleepTime);
    }

    //    private void showToast() {
//        //Todo remove textview and show toast with pagevisits instead
//    }


    //obtained from:  https://www.journaldev.com/9708/android-asynctask-example-tutorial

    private class AsyncTaskRunner extends AsyncTask<String, String, String> {

        private String resp;
        ProgressDialog progressDialog;

        @Override
        protected String doInBackground(String... params) {
            publishProgress("Dog is sleeping for..."); // Calls onProgressUpdate()
            try {
                int time = Integer.parseInt(params[0]) * 1000;
                Thread.sleep(time);
                resp = "Slept for " + params[0] + " seconds";
            } catch (InterruptedException e) {
                e.printStackTrace();
                resp = e.getMessage();
            } catch (Exception e) {
                e.printStackTrace();
                resp = e.getMessage();
            }
            return resp;
        }


        @Override
        protected void onPostExecute(String result) {
            // execution of result of Long time consuming operation
            progressDialog.dismiss();
            dogslept.setText(result);
        }


        @Override
        protected void onPreExecute() {
            progressDialog = ProgressDialog.show(DogActivity.this,
                    "ProgressDialog",
                    "Dog is sleeping, wait a few seconds");
        }


        @Override
        protected void onProgressUpdate(String... text) {
            dogslept.setText(text[0]);
        }
    }
}
