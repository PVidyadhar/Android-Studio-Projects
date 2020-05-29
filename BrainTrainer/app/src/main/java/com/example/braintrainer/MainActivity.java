package com.example.braintrainer;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    ConstraintLayout gameLayout;
    Button goButton;
    boolean gameActive=false;
    Button playAgainButton;
    CountDownTimer countDownTimer;
    Button button0,button1,button2,button3;
    TextView questionTextView;
    TextView resultTextView;
    TextView timerTextView;
    TextView scoreTextView;
    int locationOfCorrectAnswer;
    int score=0;
    int numberOfQuestions=0;
    ArrayList<Integer> answers = new ArrayList<Integer>();

    public void start(View view)
    {
        goButton.setVisibility(View.INVISIBLE);
        gameLayout.setVisibility(View.VISIBLE);
        playAgain(findViewById(R.id.timerTextView));


    }
    public void chooseAnswer(View view)
    {
        if(gameActive) {
            Log.i("Tag", view.getTag().toString());
            if (Integer.toString(locationOfCorrectAnswer).equals(view.getTag().toString())) {
                Log.i("Correct!", "You Got It");
                resultTextView.setText("Correct!");
                score++;
            } else {
                Log.i("Wromg :(", "You Didn't Get It");
                resultTextView.setText("Wrong :(");
            }
            numberOfQuestions++;
            scoreTextView.setText(Integer.toString(score) + "/" + Integer.toString(numberOfQuestions));
            newQuestion();
        }

    }
    public void newQuestion()
    {
        Random rand =new Random();

        int a =rand.nextInt(21);
        int b =rand.nextInt(21);

        questionTextView.setText(Integer.toString(a)+"+"+Integer.toString(b));

        locationOfCorrectAnswer = rand.nextInt(4);
        answers.clear();
        for(int i=0;i<4;i++)
        {
            if(i== locationOfCorrectAnswer){
                answers.add(a+b);
            }
            else {

                int wrongAnswer = rand.nextInt(41);
                while(wrongAnswer==a+b)
                {
                    wrongAnswer = rand.nextInt(41);;
                }
                answers.add(wrongAnswer);
            }
        }
        button0.setText(Integer.toString(answers.get(0)));
        button1.setText(Integer.toString(answers.get(1)));
        button2.setText(Integer.toString(answers.get(2)));
        button3.setText(Integer.toString(answers.get(3)));
    }

    public void playAgain(View view)
    {
        gameActive=true;
        score=0;
        numberOfQuestions=0;
        timerTextView.setText("5s");
        scoreTextView.setText(Integer.toString(score)+"/"+Integer.toString(numberOfQuestions));
        playAgainButton.setVisibility(View.INVISIBLE);
        resultTextView.setText("");
        newQuestion();
        countDownTimer =new CountDownTimer(5100,1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                timerTextView.setText(String.valueOf(millisUntilFinished/1000)+"s");


            }

            @Override
            public void onFinish() {
                resultTextView.setText("Done!");
                playAgainButton.setVisibility(View.VISIBLE);
                gameActive=false;
            }
        }.start();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        gameLayout= findViewById(R.id.gameLayout);
        gameLayout.setVisibility(View.INVISIBLE);
        playAgainButton = findViewById(R.id.playAgainButton);
        playAgainButton.setVisibility(View.INVISIBLE);
        questionTextView = findViewById(R.id.questionTextView);
        resultTextView = findViewById(R.id.resultTextView);
        scoreTextView = findViewById(R.id.scoretextView);
        timerTextView = findViewById(R.id.timerTextView);
        goButton = findViewById(R.id.goButton);
        button0 = findViewById(R.id.button0);
        button1 = findViewById(R.id.button1);
        button2 = findViewById(R.id.button2);
        button3 = findViewById(R.id.button3);
        goButton.setVisibility(View.VISIBLE);



    }

}
