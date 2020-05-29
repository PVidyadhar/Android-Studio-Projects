package com.example.login;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    /*public void loginFunction(View view){
        EditText username = (EditText) findViewById(R.id.usernameEditText);
        EditText password = (EditText) findViewById(R.id.passwordEditText);

        Log.i("Info","Button is clicked");
        Log.i("Username",username.getText().toString());
        Log.i("Password",password.getText().toString());

        Toast.makeText(this, "Hi"+username.getText().toString()+"!", Toast.LENGTH_SHORT).show();

        ImageView imageView = (ImageView) findViewById(R.id.imageView);
        imageView.setImageResource(R.drawable.android2);
    }*/
    //boolean showingAndroid1 = true;
    public void fade(View view)
    {

        //ImageView imageView2 = (ImageView) findViewById(R.id.imageView2);
        //Log.i("Info","Image is tapped");


       /* if (showingAndroid1)
        {
            showingAndroid1 = false;
            imageView.animate().alpha(0).setDuration(2000);
            imageView2.animate().alpha(1).setDuration(2000);
        }
        else
        {
            showingAndroid1 = true;
            imageView.animate().alpha(1).setDuration(2000);
            imageView2.animate().alpha(0).setDuration(2000);
        }*/



    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ImageView imageView = (ImageView) findViewById(R.id.imageView);
        //imageView.animate().scaleX(0.5f).setDuration(2000);
        //imageView.animate().scaleY(0.5f).setDuration(2000);
        imageView.setX(-2000);
        imageView.animate().translationXBy(2000).rotation(3600).setDuration(5000);
    }
}
