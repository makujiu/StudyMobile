package com.nowocode.studymobile;

import android.provider.BaseColumns;

/**
 * Created by Martin on 09.11.2015.
 */
public class QuestionDbContract {
    public QuestionDbContract() {};

    public static abstract class QuestionEntry implements BaseColumns{
        public static String TABLE_NAME="testingTable";
        public static final String COLUMN_NAME_QUESTION ="question";
        public static final String COLUMN_NAME_ANSWER ="answer";
        public static final String COLUMN_NAME_COURSE ="course";

        public static void setTable(String s){
            TABLE_NAME=s;
        }



    }

}
