package com.nowocode.studymobile;


import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;


/**
 * Created by Martin on 13.11.2015.
 */
public class EditActivity extends FragmentActivity implements DeleteQuestionDialog.DialogListener{
    private RecyclerView mRecyclerView;
    private QuestionSetAdapter adapter;
    private RecyclerView.LayoutManager mLayoutManager;
    QuestionDbHelper dbHelper;
    ArrayList<QuestionSet> questionSet;
    private String course;
    private boolean deleteQuestion;
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
        questionSet = dbHelper.getQuestionSet(course);
        adapter = new QuestionSetAdapter(questionSet);
        mRecyclerView.setAdapter(adapter);
        SwipeableRecyclerViewTouchListener swipeTouchListener =
                new SwipeableRecyclerViewTouchListener(mRecyclerView,
                        new SwipeableRecyclerViewTouchListener.SwipeListener() {

                            @Override
                            public boolean canSwipe(int position) {
                                return true;
                            }

                            @Override
                            public void onDismissedBySwipeLeft(RecyclerView recyclerView, int[] reverseSortedPositions) {
                                //dialog.show(getSupportFragmentManager(),"remove");

                                    for (int position : reverseSortedPositions) {
                                        dbHelper.deleteColumn(questionSet.get(position).getQuestion(), course);
                                        questionSet.remove(position);
                                        adapter.notifyItemRemoved(position);
                                    }
                                    adapter.notifyDataSetChanged();
                            }

                            @Override
                            public void onDismissedBySwipeRight(RecyclerView recyclerView, int[] reverseSortedPositions) {
                                    for (int position : reverseSortedPositions) {
                                        dbHelper.deleteColumn(questionSet.get(position).getQuestion(), course);
                                        questionSet.remove(position);
                                        adapter.notifyItemRemoved(position);
                                    }
                                    adapter.notifyDataSetChanged();
                                }
                        });

        mRecyclerView.addOnItemTouchListener(swipeTouchListener);

    }

    @Override
    public void onDialogPositiveClick(DialogFragment dialog) {
        deleteQuestion = true;

    }

    @Override
    public void onDialogNegativeClick(DialogFragment dialog) {
        deleteQuestion = false;
    }
}
