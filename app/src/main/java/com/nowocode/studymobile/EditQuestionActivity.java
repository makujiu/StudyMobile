package com.nowocode.studymobile;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

/**
 * Created by Martin on 30.11.2015.
 */
public class EditQuestionActivity extends Activity {
    private TextView questionText;
    private String question;
    private String answer;
    private String course;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.editquestionmain);
        questionText = (TextView) findViewById(R.id.editQuestionTextMain);
        question = getIntent().getStringExtra("question");
        course = getIntent().getStringExtra("course");
        answer = getIntent().getStringExtra("answer");
        questionText.setText(question);
    }

    public void editAnswer(View v){
        if(!questionText.getText().toString().isEmpty()) {
            question = questionText.getText().toString();
            Intent intent = new Intent(this, EditAnswerActivity.class);
            intent.putExtra("course", course);
            intent.putExtra("newQuestion", question);
            intent.putExtra("answer", answer);
            startActivity(intent);
        }
    }
}
