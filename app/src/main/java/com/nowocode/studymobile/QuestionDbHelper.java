package com.nowocode.studymobile;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Martin on 09.11.2015.
 * class used to create the DB
 */
public class QuestionDbHelper extends SQLiteOpenHelper {
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "Quiz.db";


    public static final String CREATE_COMMAND = "CREATE TABLE " + QuestionDbContract.QuestionEntry.TABLE_NAME
            + "(" + QuestionDbContract.QuestionEntry.COLUMN_NAME_QUESTION + " varchar(255)," +
            QuestionDbContract.QuestionEntry.COLUMN_NAME_ANSWER + " varchar(255) ," +
            QuestionDbContract.QuestionEntry.COLUMN_NAME_COURSE + " varchar(255));";

    public QuestionDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);

    }

    public static final String UPDATE_COMMAND = "";

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_COMMAND);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }


    //make Entry into SQLLite Database
    public void makeEntry(String question, String answer, String course) {
        String query = "INSERT INTO " + QuestionDbContract.QuestionEntry.TABLE_NAME + " values('" + question + "','" + answer
                + "','" + course + "');";
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL(query);
        db.close();
    }

    public void customQuery(String query){
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL(query);
        db.close();
    }

    // QuestionSet gets referenced by Course and Answer
    public void updateEntry(String course, String answer,
                            String newQuestion, String newAnswer){
        String query = "UPDATE " + QuestionDbContract.QuestionEntry.TABLE_NAME + " SET "+
                QuestionDbContract.QuestionEntry.COLUMN_NAME_QUESTION+"='"+newQuestion+"'," +
                QuestionDbContract.QuestionEntry.COLUMN_NAME_ANSWER+" ='"+newAnswer+
                "' where " + QuestionDbContract.QuestionEntry.COLUMN_NAME_ANSWER + " = '" + answer +
                "' and " + QuestionDbContract.QuestionEntry.COLUMN_NAME_COURSE + "='" + course + "';";
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL(query);
        db.close();

    }
    public void deleteColumn(String question, String course){
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("DELETE FROM " + QuestionDbContract.QuestionEntry.TABLE_NAME + " where question='" + question +"' and course='"
        +course + "';");
        db.close();
    }

    public String[] getCourses(){
        String[] courses = null;
        ArrayList<String> tempResults = new ArrayList<>();
        String query = "SELECT distinct course from " + QuestionDbContract.QuestionEntry.TABLE_NAME;
        SQLiteDatabase db = getWritableDatabase();
        Cursor cursor = db.rawQuery(query,null);
        cursor.moveToFirst();
        while(!cursor.isAfterLast()){
            tempResults.add(cursor.getString(0));
            cursor.moveToNext();
        }
        courses = new String[tempResults.size()];

        //converting the ArrayList into a static String Array
        for(int i = 0; i < tempResults.size(); i++)
            courses[i] = tempResults.get(i);
        db.close();
        return courses;
    }
    public ArrayList<QuestionSet> getQuestionSet(String course) {
        String query = " Select question,answer from " + QuestionDbContract.QuestionEntry.TABLE_NAME +" where course='" + course + "'";
        ArrayList<QuestionSet> results = new ArrayList<QuestionSet>();
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        cursor.moveToFirst();
        while(!cursor.isAfterLast()) {
            results.add(new QuestionSet(cursor.getString(0), cursor.getString(1), course));
            cursor.moveToNext();
        }
        db.close();
        return results;
    }
}