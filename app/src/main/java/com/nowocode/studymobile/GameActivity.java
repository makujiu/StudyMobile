package com.nowocode.studymobile;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Martin on 09.11.2015.
 */
public class GameActivity extends Activity {
    ArrayList<QuestionSet> questionSet;
    private Button displayBtn;
    private TextView answerLabel;
    private TextView courseLabel;
    private TextView questionLabel;
    private TextView countLabel;
    private boolean showAnswer = false;
    int showAnswerFlag = 0;
    Button prevButton;
    Button nextButton;
    QuestionDbHelper dbHelper = new QuestionDbHelper(this);
    private int index = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.quiz_main_activity);
        answerLabel = (TextView) findViewById(R.id.answerLabel);
        questionLabel = (TextView) findViewById(R.id.questionLabel);
        courseLabel = (TextView) findViewById(R.id.courseLabel);
        countLabel = (TextView) findViewById(R.id.countLabel);
        prevButton = (Button) findViewById(R.id.prevButton);
        displayBtn = (Button) findViewById(R.id.displayAnswerBtn);
        setTextLabels();
        setCountLabel();
        showAnswer(false);
    }

    public void displayAnswer(View view){
        showAnswerFlag++;
        if(showAnswerFlag%2 == 1) {
            showAnswer(true);
            displayBtn.setText("Hide");
        }else {
            showAnswer(false);
            displayBtn.setText("Show");
        }

    }

    private void showAnswer(boolean b){
        if(b == false)
         answerLabel.setVisibility(View.INVISIBLE);
        else
            answerLabel.setVisibility(View.VISIBLE);
    }

    private void setTextLabels(){
        answerLabel.setText(questionSet.get(index).getAnswer());
        questionLabel.setText(questionSet.get(index).getQuestion());
        courseLabel.setText(questionSet.get(index).getCourse());
    }

    private void setCountLabel(){
        countLabel.setText(index + "/" + (questionSet.size()-1));
    }

    //Method called when clicking prev Button
    public void prevClick(View view){
        if(index > 0 && index < questionSet.size()) {
            index--;
            setTextLabels();
            setCountLabel();
        }
    }

    //Method called when clicking Next Button
    public void nextClick(View view){
        if(index >= 0 && index < questionSet.size()-1) {
            index++;
            setTextLabels();
            System.out.println(questionSet.size());
            setCountLabel();
            }
        }
}

