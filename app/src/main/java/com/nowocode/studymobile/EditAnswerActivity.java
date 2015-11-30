package com.nowocode.studymobile;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

/**
 * Created by Martin on 30.11.2015.
 */
public class EditAnswerActivity extends Activity {
    private String answer;
    private String editedAnswer;
    private String editedQuestion;
    private String course;
    private TextView answerLabel;
    QuestionDbHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.editanswermain);
        answer = getIntent().getStringExtra("answer");
        course = getIntent().getStringExtra("course");
        editedQuestion = getIntent().getStringExtra("newQuestion");
        answerLabel = (TextView) findViewById(R.id.editAnswerTextMain);
        answerLabel.setText(answer);
        dbHelper = new QuestionDbHelper(this);
    }

        public void saveEdit(View v){
            if(!answerLabel.getText().toString().isEmpty()) {
                editedAnswer = answerLabel.getText().toString();
                dbHelper.updateEntry(course, answer, editedQuestion, editedAnswer);
                Intent intent = new Intent(this, EditActivity.class);
                intent.putExtra("course", course);
                startActivity(intent);
            }
    }
}
