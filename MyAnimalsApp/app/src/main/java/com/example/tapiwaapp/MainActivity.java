package com.example.tapiwaapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
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

            Intent intent;

            switch(i) {
                case 0:
                     intent = new Intent(MainActivity.this, CatActivity.class);
                    break;
                case 1:
                    intent = new Intent(MainActivity.this, DogActivity.class);
                    break;
                case 2:
                    intent = new Intent(MainActivity.this, RabbitActivity.class);
                    break;
                case 3:
                    intent = new Intent(MainActivity.this, HorseActivity.class);
                    break;
                case 4:
                    intent = new Intent(MainActivity.this, CamelActivity.class);
                    break;
                    default:
                        intent = null;
            }

            if(intent != null) {
                startActivity(intent);
                }

            }
        });

    }
}
