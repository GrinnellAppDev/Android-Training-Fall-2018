package com.example.tapiwaapp;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class CatActivity extends AppCompatActivity {

    ImageView imageView;
    TextView pageVisitsCount, responseTxt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cat);
        setTitle("CatActivity");

        imageView = findViewById(R.id.catView);
        pageVisitsCount = findViewById(R.id.catPageVisitsCount);
        responseTxt = findViewById(R.id.network_response);

        loadCatImage();
        incrementPageVisit();
    }

    private void loadCatImage() {
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
        int currentPageVisits = sharedPref.getInt("catPageVisits", 0);

        //Increment pagevisits by 1
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putInt("catPageVisits", ++currentPageVisits);
        editor.commit();

        //show updated currentPageVisits
        pageVisitsCount.setText("Page visited " + currentPageVisits + " times");
    }

    //    private void showToast() {
//        //Todo remove textview and show toast with pagevisits instead
//    }

    public void fetchData(View view) throws IOException {

        OkHttpClient client = new OkHttpClient();

        String url = "https://reqres.in/api/users/2";
        Request request = new Request.Builder()
                .url(url)
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                call.cancel();
            }

            @Override
            public void onResponse(Call call, final Response response) throws IOException {

                final String myResponse = response.body().string();

                CatActivity.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        responseTxt.setText(myResponse);
                    }
                });

            }
        });

    }
}
