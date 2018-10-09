package com.example.tapiwaapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final String[] animals = {"Cat", "Dog", "Rabbit",
        "Horse", "Camel"};

        ArrayAdapter<String> adapter = new ArrayAdapter<>(getApplicationContext(),
                R.layout.item_view, animals);

        ListView listView = findViewById(R.id.listview);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

            Intent intent = new Intent(MainActivity.this, MyCatActivity.class);
            startActivity(intent);
            //Create a switch statement that will open a new empty activity based on the value int i
                //i.e if int i = 0, that corresponds to cat thus open a new activity called CatActivity.
                //Make sure the title in the toolbar changes to CatActivity (research how to do this on your own)
                //This means you need to firstly create empty activities for all your animals
                //We will add stuff to these activities in next class
            }
        });

    }
}
