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
    int showAnswerFlag = 0;
    QuestionDbHelper dbHelper = new QuestionDbHelper(this);
    // index = 1 because the 0th element of the array is the default entry
    private int index = 1;
    private String course;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.quiz_main_activity);
        answerLabel = (TextView) findViewById(R.id.answerLabel);
        questionLabel = (TextView) findViewById(R.id.questionLabel);
        courseLabel = (TextView) findViewById(R.id.courseLabel);
        countLabel = (TextView) findViewById(R.id.gameCountLabel);
        displayBtn = (Button) findViewById(R.id.displayAnswerBtn);
        course= getIntent().getStringExtra("course");
        buildQuestionSetArray();
        setTextLabels();
        setCountLabel();
        showAnswer(false);
    }

    //user Clicks on Show/Hide Button
    public void displayAnswer(View view){
        showAnswerFlag++;
        if(showAnswerFlag%2 == 1)
            showAnswer(true);
        else
            showAnswer(false);
    }
    private void buildQuestionSetArray(){
        questionSet =  dbHelper.getQuestionSet(course);
    }

    private void showAnswer(boolean b){
        if(b == false) {
            answerLabel.setVisibility(View.INVISIBLE);
            displayBtn.setText("Show");
            showAnswerFlag = 0;
        }
        else {
            answerLabel.setVisibility(View.VISIBLE);
            displayBtn.setText("Hide");
        }
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
        if(index > 1 && index < questionSet.size()) {
            showAnswer(false);
            index--;
            setTextLabels();
            setCountLabel();
        }
    }

    //Method called when clicking Next Button
    public void nextClick(View view){
        if(index >= 1 && index < questionSet.size()-1) {
            index++;
            showAnswer(false);
            setTextLabels();
            System.out.println(questionSet.size());
            setCountLabel();
            }
        }
}

