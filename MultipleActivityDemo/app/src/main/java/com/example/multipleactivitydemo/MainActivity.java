package com.example.multipleactivitydemo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {

    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView= findViewById(R.id.listView);

        final ArrayList<String> familyList = new ArrayList<String>();
        
        familyList.add("Venkatesh");
        familyList.add("Sunita");
        familyList.add("Shruti");
        familyList.add("Suman");
        familyList.add("Vidyadhar");

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,familyList);
        listView.setAdapter(arrayAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getApplicationContext(),SecondActivity.class);

                intent.putExtra("Name",familyList.get(position));

                startActivity(intent);
            }
        });
        
    }
}
