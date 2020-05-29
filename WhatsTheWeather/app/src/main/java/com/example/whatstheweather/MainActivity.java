package com.example.whatstheweather;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.ExecutionException;

import static android.provider.ContactsContract.CommonDataKinds.Website.URL;

public class MainActivity extends AppCompatActivity {
    EditText editText;
    TextView resultTextView;


    public  void getWeather(View view)
    {

        DownloadTask task = new DownloadTask();
        try {
            task.execute("https://samples.openweathermap.org/data/2.5/weather?q="+editText.getText().toString()+"&appid=967df61f8c587c7c75be70f06662b276");
            InputMethodManager mgr = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            mgr.hideSoftInputFromWindow(editText.getWindowToken(),0);
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(getApplicationContext(),"Could not find weather!",Toast.LENGTH_SHORT);
        }
    }


    public class DownloadTask extends AsyncTask<String,Void,String>
    {
        @Override
        protected String doInBackground(String... urls) {
            String result = "";
            URL url;
            HttpURLConnection urlConnection;
            try {
                url = new URL(urls[0]);
                urlConnection = (HttpURLConnection) url.openConnection();
                InputStream in = urlConnection.getInputStream();
                InputStreamReader reader = new InputStreamReader(in);
                int data = reader.read();
                while (data != -1) {
                    char current = (char) data;
                    result += current;
                    data = reader.read();
                }
                return result;
            } catch (Exception e) {
                e.printStackTrace();
                Toast.makeText(getApplicationContext(),"Could not find weather!",Toast.LENGTH_SHORT);
                return null;
            }
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            try {
                JSONObject jsonObject = new JSONObject(s);
                String weatherInfo  = jsonObject.getString("weather");
                Log.i("Weather Info",weatherInfo);

                JSONArray arr = new JSONArray(weatherInfo);
                String message="";
                for(int i=0 ; i< arr.length();i++)
                {
                    JSONObject jsonPart = arr.getJSONObject(i);
                    String main = jsonPart.getString("main");
                    String description = jsonPart.getString("description");
                    Log.i("main",jsonPart.getString("main"));
                    Log.i("description",jsonPart.getString("description"));

                    if(!main.equals("") && !description.equals("")){
                        message += main+ ":" + description;
                    }
                }

                if(!message.equals(""))
                {
                    resultTextView.setText(message);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText = findViewById(R.id.editText);
        resultTextView = findViewById(R.id.resulttextView);

    }
}
