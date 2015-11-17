package com.nowocode.studymobile;


import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;


/**
 * Created by Martin on 13.11.2015.
 */
public class EditActivity extends Activity {
    private RecyclerView mRecyclerView;
    private QuestionSetAdapter adapter;
    private RecyclerView.LayoutManager mLayoutManager;
    QuestionDbHelper dbHelper;
    private String course;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        course = getIntent().getStringExtra("course");
        dbHelper = new QuestionDbHelper(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_entry_activity);
        mRecyclerView = (RecyclerView) findViewById(R.id.question_recycler_view);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        adapter = new QuestionSetAdapter(dbHelper.getQuestionSet(course));
        mRecyclerView.setAdapter(adapter);

    }
}
