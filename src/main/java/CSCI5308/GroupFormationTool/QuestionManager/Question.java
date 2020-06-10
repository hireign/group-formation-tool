package CSCI5308.GroupFormationTool.QuestionManager;

import java.util.ArrayList;
import java.util.List;

public class Question {
    private String questionTitle;
    private String questionText;
    private String questionType;
    private String questionInstructor;
//    private List<String> questionOptions = null;

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

//    public List<String> getQuestionOptions(){
//        return questionOptions;
//    }
//
//    public void setQuestionOptions(List<String> questionOptions){
//        this.questionOptions = questionOptions;
//    }

}
