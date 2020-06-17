package com.example.braintrainer;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    Button start;
    TextView sumTextView;
    ArrayList<Integer> answer = new ArrayList<Integer>();
    int locationOfCorrectAnswer;
    Button button0 = (Button) findViewById(R.id.button0);
    Button button1 = (Button) findViewById(R.id.button1);
    Button button2 = (Button) findViewById(R.id.button2);
    Button button3 = (Button) findViewById(R.id.button3);
    TextView timerTextView;
    int score=0;
    int numberOfQuestions=0;
    TextView resultTextView;
    TextView scoreTextView;
    Button playAgainButton;
    public void generateQuestion(){
        Random random = new Random();
        int a = random.nextInt(21);
        int b = random.nextInt(21);
        sumTextView.setText(Integer.toString(a) + "+" + Integer.toString(b));
        locationOfCorrectAnswer = random.nextInt(4);
        answer.clear();
        int inCorrectAnswer;
        for (int i = 0; i < 4; i++) {
            if (i == locationOfCorrectAnswer) {
                answer.add(a + b);
            } else {
                inCorrectAnswer = random.nextInt(41);
                while (inCorrectAnswer == a + b) {
                    inCorrectAnswer = random.nextInt(41);
                }
                answer.add(inCorrectAnswer);
            }

        }
        button0.setText(Integer.toString(answer.get(0)));
        button1.setText(Integer.toString(answer.get(1)));
        button2.setText(Integer.toString(answer.get(2)));
        button3.setText(Integer.toString(answer.get(3)));

    }


    public void StartMethod(View view) {
        start.setVisibility(View.INVISIBLE);
    }

    public void chooseAnswer(View view) {
        if (view.getTag().toString().equals(Integer.toString(locationOfCorrectAnswer))) {
            score++;

            resultTextView.setText("Correct!");}
        else{
            resultTextView.setText("Wrong!");

        }
        numberOfQuestions++;
        scoreTextView.setText(Integer.toString(score)+"/"+ Integer.toString(numberOfQuestions));
        generateQuestion();



    }
    public void playAgain(View view){
        score=0;
        numberOfQuestions=0;
        timerTextView.setText("30s");
        scoreTextView.setText("0/0");
        resultTextView.setText("");
        playAgainButton.setVisibility(View.INVISIBLE);
        generateQuestion();
        new CountDownTimer(30100,1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                timerTextView.setText(String.valueOf(millisUntilFinished/1000)+"S");


            }

            @Override
            public void onFinish() {
                playAgainButton.setVisibility(View.VISIBLE);
                timerTextView.setText("0S");
                resultTextView.setText("Your score:"+Integer.toString(score)+"/"+ Integer.toString(numberOfQuestions));

            }
        }.start();


    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        start = (Button) findViewById(R.id.start);
        sumTextView = (TextView) findViewById(R.id.sumTextView);
        resultTextView=(TextView)findViewById(R.id.resultTextView) ;
        scoreTextView=(TextView) findViewById(R.id.scoreTextView);
        timerTextView=(TextView) findViewById(R.id.timerTextView);
        playAgainButton=(Button)findViewById(R.id.playAgainButton);

        playAgain(findViewById(R.id.playAgainButton));



    }
}
