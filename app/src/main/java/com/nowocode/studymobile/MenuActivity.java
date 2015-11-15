package com.nowocode.studymobile;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
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
    private ArrayList<String> tempAnswers;
    private ArrayList<String> tempQuestions;
    private int questions = 0;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu_activity);
        questionSet = new ArrayList<>();
        selectedCourse = (TextView) findViewById(R.id.courseSelectionLabel);
        questionCount = (TextView) findViewById(R.id.questionCountLabel);
        selCourse = getIntent().getStringExtra("course");
        setSelectedCourse(selCourse);
        buildQuestionSet();
    }

    private void setQuestionCount(){
        if(questions == 0)
            questionCount.setText("There are no Questions");
        else
         questionCount.setText("Questions: " + questionSet.size());
    }
    private void buildQuestionSet(){
        tempQuestions = getIntent().getStringArrayListExtra("questions");
        tempAnswers = getIntent().getStringArrayListExtra("answers");
        questionCount.setText("Please Add Questions");
        if(tempQuestions != null && tempAnswers != null) {
            for (int i = 0; i < tempAnswers.size(); i++)
                questionSet.add(new QuestionSet(tempQuestions.get(i),
                        tempAnswers.get(i), selCourse));
            filterQuestionSet();
            questions = questionSet.size();
            setQuestionCount();
        }

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
