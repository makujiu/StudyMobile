package com.nowocode.studymobile;

/**
 * Created by Martin on 13.11.2015.
 */
public class QuestionSet {
    private String course;
    private String question;
    private String Answer;

    public QuestionSet(String q, String a, String c){
        setAnswer(a);
        setQuestion(q);
        setCourse(c);
    }

    public String getAnswer() {
        return Answer;
    }

    public void setAnswer(String answer) {
        Answer = answer;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }
}
