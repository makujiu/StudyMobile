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
    private TextView answerLabel;
    private TextView courseLabel;
    private TextView questionLabel;
    private TextView countLabel;
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
        prevButton.setClickable(false);
        makeCustomEntry("question one", "answer one", "test class");
        makeCustomEntry("question two", "answer two", "test class");
        makeCustomEntry("question three", "answer three", "test class");
        makeCustomEntry("question four", "answer four", "course one");
        makeCustomEntry("question four", "answer five", "another class");
        getQuestionSet("another class");
        setTextLabels();
    }

    private void setTextLabels(){
        answerLabel.setText(questionSet.get(index).getAnswer());
        questionLabel.setText(questionSet.get(index).getQuestion());
        courseLabel.setText(questionSet.get(index).getCourse());
    }
    private void makeCustomEntry(String q, String a, String c){
        dbHelper.makeEntry(q, a, c);
    }
    private void getQuestionSet(String c){
        questionSet = dbHelper.getQuestionSet(c);
    }

    //Method called when clicking prev Button
    public void prevClick(View view){
        if(index > 0 && index < questionSet.size()) {
            index--;
            setTextLabels();
        }
    }

    //Method called when clicking Next Button
    public void nextClick(View view){
        if(index >= 0 && index < questionSet.size()) {
            index++;
            setTextLabels();
            System.out.println(questionSet.size());
            }
        }
}

