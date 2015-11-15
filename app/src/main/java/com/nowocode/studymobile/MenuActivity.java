package com.nowocode.studymobile;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Martin on 15.11.2015.
 */
public class MenuActivity extends Activity {
    private TextView selectedCourse;
    private TextView questionCount;
    private ArrayList<QuestionSet> questionSet;
    private String selCourse;
    private Button playBtn;
    private int questions = 0;
    QuestionDbHelper dbHelper;

    @Override
    protected void onResume() {
        super.onResume();
        buildQuestionSetArray();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu_activity);
        questionSet = new ArrayList<>();
        playBtn = (Button) findViewById(R.id.playButton);
        selectedCourse = (TextView) findViewById(R.id.courseSelectionLabel);
        questionCount = (TextView) findViewById(R.id.questionCountLabel);
        selCourse = getIntent().getStringExtra("course");
        dbHelper = new QuestionDbHelper(this);
        setSelectedCourse(selCourse);
        buildQuestionSetArray();
    }

    private void setQuestionCount(){
        if(questions == 0) {
            questionCount.setText("There are no Questions");
            playBtn.setEnabled(false);
            playBtn.setAlpha(0.3f);
        }
        else {
            questionCount.setText("Questions: " + (questionSet.size()-1));
            playBtn.setEnabled(true);
            playBtn.setAlpha(1);
        }
    }
    private void buildQuestionSetArray(){
        questionSet =  dbHelper.getQuestionSet(selCourse);
        questions = questionSet.size()-1;
        setQuestionCount();
    }
    //User clicsk on Play Button
    public void play(View v){
        Intent intent = new Intent(this, GameActivity.class);
        intent.putExtra("course",selCourse);
        startActivity(intent);
    }
    //User clicks on Add Button
    public void addQuestions(View v){
        Intent intent = new Intent(this, AddQuestionActivity.class);
        intent.putExtra("course",selCourse);
        startActivity(intent);
    }
    //filters out first QuestionSet  [question="default"; answer="default"]
    private void filterQuestionSet(){
        for(QuestionSet q : questionSet) {
            if (q.getAnswer().equals("default"))
                questionSet.remove(q);
        }
    }
    private void setSelectedCourse(String s){
        selectedCourse.setText("Course: " + s);
    }
}
