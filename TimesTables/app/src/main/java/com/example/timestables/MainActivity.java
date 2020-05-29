package com.example.timestables;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SeekBar;

import java.util.ArrayList;
import java.util.List;

import static java.util.Arrays.asList;

public class MainActivity extends AppCompatActivity {
    int initial=10;
    int min=1;
    int max=20;
    int notlessthanthis=5;




    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SeekBar seekBar = (SeekBar) findViewById(R.id.seekBar);

        seekBar.setProgress(initial);
        seekBar.setMax(max);
        seekBar.setMin(min);
        ArrayList<Integer> tablesList = new ArrayList<Integer>();
        for(int i=1;i<=20;i++)
        {
            tablesList.add(initial*i);
        }

        ArrayAdapter<Integer> arrayAdapter = new ArrayAdapter<Integer>(this,android.R.layout.simple_list_item_1,tablesList);

        ListView myListView = (ListView) findViewById(R.id.myListView);

        myListView.setAdapter(arrayAdapter);
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                int value= seekBar.getProgress();
                if (value<notlessthanthis)
                {
                     value=notlessthanthis;
                     seekBar.setProgress(notlessthanthis);
                }


                ArrayList<Integer> tablesList = new ArrayList<Integer>();
                for(int i=1;i<=20;i++)
                {
                    tablesList.add(value*i);
                }

                ArrayAdapter<Integer> arrayAdapter = new ArrayAdapter<Integer>(MainActivity.this,android.R.layout.simple_list_item_1,tablesList);

                ListView myListView = (ListView) findViewById(R.id.myListView);

                myListView.setAdapter(arrayAdapter);

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {


            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });






    }
}
