package CSCI5308.GroupFormationTool.QuestionManager;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Question {
    private int questionId;
    private String questionTitle;
    private String questionText;
    private String questionType;
    private String questionInstructor;
    private ArrayList questionOptions;
    private ArrayList optionNumber;
    private Date questionDate;

    public int getQuestionId(){
        return questionId;
    }

    public void setQuestionId(int questionId) {
            this.questionId = questionId;
    }

    public String getQuestionTitle(){
        return questionTitle;
    }

    public void setQuestionTitle(String questionTitle) {
        this.questionTitle = questionTitle;
    }

    public String getQuestionText(){
        return questionText;
    }

    public void setQuestionText(String questionText) {
        this.questionText = questionText;
    }

    public String getQuestionType(){
        return questionType;
    }

    public void setQuestionType(String questionType) {
        this.questionType = questionType;
    }

    public String getQuestionInstructor(){
        return questionInstructor;
    }

    public void setQuestionInstructor(String questionInstructor) {
        this.questionInstructor = questionInstructor;
    }

    public ArrayList getQuestionOptions(){
        return questionOptions;
    }

    public void setQuestionOptions(ArrayList questionOptions) {
        this.questionOptions = questionOptions;
    }

    public ArrayList getOptionNumber() {
        return optionNumber;
    }

    public void setOptionNumber(ArrayList optionNumber) {
        this.optionNumber = optionNumber;
    }
    public Date getQuestionDate() {
        return questionDate;
    }

    public void setQuestionDate(Date questionDate) {
        this.questionDate = questionDate;
    }
}
