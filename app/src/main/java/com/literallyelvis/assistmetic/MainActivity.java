package com.literallyelvis.assistmetic;

import android.app.Activity;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends Activity
{
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //function that gets called on startup to initiate all text values

        Log.d("MainActivity", "Application started.");
        populateText();
    }

    private void populateText()
    {
        Log.d("MainActivity.populateText()", "populateText() called.");
        //set up text variables for later manipulation
        TextView operand1 = (TextView) findViewById(R.id.operand1);
        TextView operate = (TextView) findViewById(R.id.operator);
        TextView operand2 = (TextView) findViewById(R.id.operand2);
        TextView trueanswer = (TextView) findViewById(R.id.trueAnswer);

        //set up button variables for later manipulation
        Button one = (Button) findViewById(R.id.button1);
        Button two = (Button) findViewById(R.id.button2);
        Button three = (Button) findViewById(R.id.button3);
        Button four = (Button) findViewById(R.id.button4);

        // This function gets called whenever a user submits an answer, and once at startup.
        Problem problem = new Problem();

        operand1.setText(Integer.toString(problem.operand1));
        operate.setText(Character.toString(problem.operate));
        operand2.setText(Integer.toString(problem.operand2));
        trueanswer.setText(Integer.toString(problem.trueAnswer));

        one.setText(Integer.toString(problem.possibleAnswers[0]));
        Log.d("MainActivity.populateText()", "possibleAnswers[0] = " + problem.possibleAnswers[0]);
        two.setText(Integer.toString(problem.possibleAnswers[1]));
        Log.d("MainActivity.populateText()", "possibleAnswers[1] = " + problem.possibleAnswers[1]);
        three.setText(Integer.toString(problem.possibleAnswers[2]));
        Log.d("MainActivity.populateText()", "possibleAnswers[2] = " + problem.possibleAnswers[2]);
        four.setText(Integer.toString(problem.possibleAnswers[3]));
        Log.d("MainActivity.populateText()", "possibleAnswers[3] = " + problem.possibleAnswers[3]);
    }

    public void buttonClicked(View submission)
    {
        Log.d("MainActivity.buttonClicked", "buttonClicked called.");
        Button one = (Button) findViewById(R.id.button1);
        Button two = (Button) findViewById(R.id.button2);
        Button three = (Button) findViewById(R.id.button3);
        Button four = (Button) findViewById(R.id.button4);

        TextView truth = (TextView) findViewById(R.id.trueAnswer);
        int answer = Integer.parseInt((String) truth.getText());
        int guess = 0;

        switch(submission.getId())
        {
            case R.id.button1:
            {
                Log.d("MainActivity.answerSubmission", "Button1 pressed.");
                guess = Integer.parseInt((String) one.getText());
                break;
            }
            case R.id.button2:
            {
                Log.d("MainActivity.answerSubmission", "Button1 pressed.");
                guess = Integer.parseInt((String) two.getText());
                break;
            }
            case R.id.button3:
            {
                Log.d("MainActivity.answerSubmission", "Button1 pressed.");
                guess = Integer.parseInt((String) three.getText());
                break;
            }
            case R.id.button4:
            {
                Log.d("MainActivity.answerSubmission", "Button1 pressed.");
                guess = Integer.parseInt((String) four.getText());
                break;
            }
        }

        if ( answer == guess )
        {
            answerCorrect();
        }
        else
        {
            answerIncorrect();
        }
        populateText();
    }

    public void answerCorrect()
    {
        Log.d("MainActivity.answerCorrect", "answerCorrect called, congrats!");
        MediaPlayer play = MediaPlayer.create(this, R.raw.good);
        play.start();
        play.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            public void onCompletion(MediaPlayer mp) {
                mp.release();

            };
        });
    }

    public void answerIncorrect()
    {
        Log.d("MainActivity.answerCorrect", "answerIncorrect called, better luck next time!");
        MediaPlayer play = MediaPlayer.create(this, R.raw.bad);
        play.start();
        play.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            public void onCompletion(MediaPlayer mp) {
                mp.release();

            };
        });
    }
/*
    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
*/
}
