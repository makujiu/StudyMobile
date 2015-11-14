package com.nowocode.studymobile;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

/**
 * Created by Martin on 14.11.2015.
 */
public class CreateCourseActivity extends Activity {
    QuestionDbHelper dbHelper;
    Button saveBtn;
    EditText courseText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_course_activity);
        saveBtn = (Button) findViewById(R.id.saveButton);
        courseText = (EditText) findViewById(R.id.courseEditText);
        dbHelper = new QuestionDbHelper(this);
    }

    //Method invoked when user clicks Save Button
    public void saveCourse(View view){
        if(courseText.getText().toString() != null ||courseText.getText().toString() != "" )
            dbHelper.makeEntry("default","default",courseText.getText().toString());
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
