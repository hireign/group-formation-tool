package CSCI5308.GroupFormationTool.GroupFormation;

import java.util.ArrayList;

import CSCI5308.GroupFormationTool.SurveyManager.Response;

public interface IAlgorithmPersistence {
    public ArrayList<Response> findResponseWithSelectedQuestion(String questionID,String surveyID);
    public ArrayList<Response> GetResponseByCourseID(Long CourseID);
}
