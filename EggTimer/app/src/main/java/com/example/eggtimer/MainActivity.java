package com.example.eggtimer;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    SeekBar seekbar;
    CountDownTimer countDownTimer;
    TextView textView;
    Button buttonView;
    MediaPlayer mediaPlayer;
    @RequiresApi(api = Build.VERSION_CODES.O)
    public void go(View view) {
        buttonView = (Button) findViewById(R.id.button);

        if ("GO".equals((buttonView.getText().toString()))) {
            //System.out.println("hi");
            //Log.i("test","test");
            //Log.i("value",(buttonView.getText()).toString());

            buttonView.setText("STOP");
            mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.alarm);
            int secValue = seekbar.getProgress();
            long longSec = (long) ((secValue) * 1000);
            seekbar.setEnabled(false);
           countDownTimer =new CountDownTimer(longSec, 1000) {
                @Override
                public void onTick(long millisUntilFinished) {
                    Log.i("sec",String.valueOf(millisUntilFinished));

                    updateTimer((int)(millisUntilFinished)/1000);
                    if ((millisUntilFinished / 1000) <= 10) {
                        mediaPlayer.start();
                    }
                }

                @Override
                public void onFinish() {
                    mediaPlayer.stop();
                    resetTimer();
                }
            }.start();
        }
        else
            {
                mediaPlayer.stop();
                resetTimer();
            }
    }
    public void updateTimer(int secsLeft)
    {
        int minutes = (secsLeft)/60;
        int seconds= secsLeft-(minutes*60);
        String newseconds = Integer.toString(seconds);
        if (seconds<10)
        {
            newseconds = "0"+newseconds;
        }
        String newminutes = Integer.toString(minutes);
        if (minutes<10)
        {
            newminutes = "0"+newminutes;
        }

        textView.setText(newminutes+":"+newseconds);
    }
    @RequiresApi(api = Build.VERSION_CODES.O)
    public void resetTimer()
    {
        countDownTimer.cancel();
        textView.setText("00:30");
        seekbar.setEnabled(true);
        seekbar.setProgress(30);
        buttonView.setText("GO");
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = (TextView) findViewById(R.id.textView);
        textView.setText("00:30");
        buttonView = (Button) findViewById(R.id.button);
        buttonView.setText("GO");
        seekbar = (SeekBar) findViewById(R.id.seekBar);
        seekbar.setMax(180);
        seekbar.setMin(0);
        seekbar.setProgress(30);

        seekbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                updateTimer(progress);
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
