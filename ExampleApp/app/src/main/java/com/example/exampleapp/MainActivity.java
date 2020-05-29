package com.example.exampleapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;


public class MainActivity extends AppCompatActivity {

    public void clickButton(View view)
    {
        EditText nameEditText = (EditText) findViewById(R.id.nameEditText);
        Log.i("Info","Button is clicked");
        Log.i("Values", nameEditText.getText().toString());
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
