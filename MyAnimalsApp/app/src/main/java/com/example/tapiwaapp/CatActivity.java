package com.example.tapiwaapp;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class CatActivity extends AppCompatActivity {

    ImageView imageView;
    TextView pageVisitsCount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cat);
        setTitle("CatActivity");

        imageView = findViewById(R.id.catView);
        pageVisitsCount = findViewById(R.id.catPageVisitsCount);

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
}