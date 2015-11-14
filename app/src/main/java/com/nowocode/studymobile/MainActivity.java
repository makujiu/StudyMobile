package com.nowocode.studymobile;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

public class MainActivity extends AppCompatActivity {
    Button playBtn;
    Button newCourseBtn;
    Button editBtn;
    Spinner courseSpinner;
    String[] courseList;
    QuestionDbHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        dbHelper = new QuestionDbHelper(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        courseSpinner = (Spinner) findViewById(R.id.courseSpinner);
        initCourseList();
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.spinner_item, courseList);
        courseSpinner.setAdapter(adapter);

    }

    private void initCourseList(){
        courseList = dbHelper.getCourses();
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }


    //play Button Listener
    public void playGame(View view){
        Intent intent = new Intent(this, GameActivity.class);
        startActivity(intent);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
