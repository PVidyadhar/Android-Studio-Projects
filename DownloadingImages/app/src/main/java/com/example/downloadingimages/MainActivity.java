package com.example.downloadingimages;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.ExecutionException;


import static android.provider.ContactsContract.CommonDataKinds.Website.URL;

public class MainActivity extends AppCompatActivity {
    ImageView imageView;
    public void downloadImage(View view)
    {
        System.out.println("tapped");
        Log.i("Button Tapped","ok");
        ImageDownloader task = new ImageDownloader();
        Bitmap myImage;
        try {
            myImage = task.execute("https://upload.wikimedia.org/wikipedia/sco/a/aa/Bart_Simpson_200px.png").get();
            imageView.setImageBitmap(myImage);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imageView = findViewById(R.id.imageView);
    }

    public class ImageDownloader extends AsyncTask<String,Void, Bitmap>
    {

        @Override
        protected Bitmap doInBackground(String... urls) {
            try {
                URL url = new URL(urls[0]);
                HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
                urlConnection.connect();
                InputStream in = urlConnection.getInputStream();
                Bitmap myBitmap = BitmapFactory.decodeStream(in);
                return myBitmap;
                }
            catch (Exception e) {
                e.printStackTrace();
                return null;
            }


        }
    }
}
