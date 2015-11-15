package com.nowocode.studymobile;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

/**
 * Created by Martin on 15.11.2015.
 */
public class AddQuestionActivity extends Activity {
    QuestionDbHelper dbHelper;
    String course;
    EditText questionText;
    EditText answerText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_entry_activity);
        dbHelper = new QuestionDbHelper(this);
        course = getIntent().getStringExtra("course");
        questionText = (EditText) findViewById(R.id.questionEditText);
        answerText = (EditText) findViewById(R.id.answerEditText);
    }

    public void save(View v){
        if(!questionText.getText().toString().equals("") && !answerText.getText().toString().equals("")) {
            dbHelper.makeEntry(questionText.getText().toString(), answerText.getText().toString(), course);
            questionText.setText("");
            answerText.setText("");
        }

    }
}
