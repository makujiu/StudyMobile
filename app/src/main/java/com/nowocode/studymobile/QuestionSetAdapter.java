package com.nowocode.studymobile;


import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import java.util.ArrayList;

/**
 * Created by Martin on 13.11.2015.
 */
public class QuestionSetAdapter extends RecyclerView.Adapter<QuestionSetAdapter.QuestionSetViewHolder>{
    private ArrayList<QuestionSet> questionSet;

    public QuestionSetAdapter(ArrayList<QuestionSet> qs){
        questionSet = qs;
    }
    @Override
    public QuestionSetViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.
                from(parent.getContext()).inflate(R.layout.questioncard, parent, false);
        return new QuestionSetViewHolder(itemView);
    }



    @Override
    public void onBindViewHolder(QuestionSetViewHolder holder, int i) {
        QuestionSet question = questionSet.get(i);
        holder.questionTitle.setText(question.getQuestion());
        holder.answer.setText(question.getAnswer());
    }

    @Override
    public int getItemCount() {
        return questionSet.size();
    }




    public static class QuestionSetViewHolder extends RecyclerView.ViewHolder{
        protected TextView questionTitle;
        protected TextView answer;

        public QuestionSetViewHolder(View v){
            super(v);
            questionTitle = (TextView) v.findViewById(R.id.questionTitle);
            answer = (TextView) v.findViewById(R.id.answerCard);
        }
    }
}
