package com.example.connect3;

import androidx.appcompat.app.AppCompatActivity;
//import androidx.*;
import android.media.Image;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.widget.Button;
//import android.widget.GridLayout;
import androidx.gridlayout.widget.GridLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {
    //0:yellow,1:red,2:empty
    int activePlayer=0;
    int c=0;
    boolean gameActive = true;
    int[] gameState={2,2,2,2,2,2,2,2,2};
    int[][] winningPositions = {{0,1,2},{3,4,5},{6,7,8},{0,3,6},{1,4,7},{2,5,8},{0,4,8},{2,4,6}};

    public void dropIn(View view)
    {

        ImageView counter = (ImageView) view;
        Log.i("Tag",counter.getTag().toString());
        int taggedCounter = Integer.parseInt(counter.getTag().toString());
        if (gameState[taggedCounter]==2 && gameActive) {
            gameState[taggedCounter] = activePlayer;
            c+=1;

            counter.setTranslationY(-1500);
            if (activePlayer == 0) {
                counter.setImageResource(R.drawable.yellow);
                activePlayer = 1;
            } else {
                counter.setImageResource(R.drawable.red);
                activePlayer = 0;
            }

            counter.animate().translationYBy(1500).setDuration(100);
            for (int[] winningPosition : winningPositions) {
                if ((gameState[winningPosition[0]] != 2) && (gameState[winningPosition[0]] == gameState[winningPosition[1]]) && (gameState[winningPosition[1]] == gameState[winningPosition[2]]) && (gameState[winningPosition[1]] == gameState[winningPosition[0]])) {
                    gameActive=false;
                    String winner = "";
                    if (activePlayer == 1) {
                        winner = "Yellow";
                    } else {
                        winner = "Red";
                    }
                    //Toast.makeText(this, winner + " has Won!", Toast.LENGTH_LONG).show();
                    Button playAgainButton = (Button) findViewById(R.id.playAgainButton);
                    TextView winnerTextView = (TextView) findViewById(R.id.textView);
                    winnerTextView.setText(winner+" has won!");
                    playAgainButton.setVisibility(View.VISIBLE);
                    winnerTextView.setVisibility(View.VISIBLE);
                }
            }



            if(c==9 && gameActive)
            {
                Log.i("Info",Integer.toString(c));
                gameActive=false;
                Button playAgainButton = (Button) findViewById(R.id.playAgainButton);
                TextView winnerTextView = (TextView) findViewById(R.id.textView);
                winnerTextView.setText("Its a draw!");
                playAgainButton.setVisibility(View.VISIBLE);
                winnerTextView.setVisibility(View.VISIBLE);

            }
        }


    }

    public void playAgain(View view)
    {
        Button playAgainButton = (Button) findViewById(R.id.playAgainButton);
        TextView winnerTextView = (TextView) findViewById(R.id.textView);
        playAgainButton.setVisibility(View.INVISIBLE);
        winnerTextView.setVisibility(View.INVISIBLE);

        GridLayout gridLayout = (GridLayout) findViewById(R.id.gridLayout);
        for(int i=0;i<gridLayout.getChildCount();i++)
        {
            ImageView counter= (ImageView) gridLayout.getChildAt(i);
            counter.setImageDrawable(null);
        }
        activePlayer=0;
        c=0;
        gameActive = true;
        for(int i=0;i<9;i++)
        {
            gameState[i]=2;
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
