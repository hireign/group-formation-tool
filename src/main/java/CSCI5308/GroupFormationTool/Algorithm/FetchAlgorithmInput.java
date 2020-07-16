package CSCI5308.GroupFormationTool.Algorithm;

import CSCI5308.GroupFormationTool.AccessControl.User;
import CSCI5308.GroupFormationTool.Database.CallStoredProcedure;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FetchAlgorithmInput implements IAlgorithmPersistence{
    public ArrayList<Response> findResponseWithSelectedQuestion (String questionId, String surveyId){
        ArrayList<Response> responses = new ArrayList<Response>();
        CallStoredProcedure proc = null;
        try
        {
            proc = new CallStoredProcedure("spFindUsersWithQuestionID(?,?)");
            proc.setParameter(1, questionId);
            proc.setParameter(4,surveyId);
            ResultSet results = proc.executeWithResults();
            if (null != results)
            {
                while (results.next())
                {
                    String questionID = results.getString(1);
                    String userID = results.getString(2);
                    String response = results.getString(3);
                    String surveyID = results.getString(4);
                    Response r = new Response();
                    r.setQuestionID(questionID);
                    r.setUserID(userID);
                    r.setResponse(response);
                    r.setResponse(surveyID);
                    responses.add(r);
                }
            }
        }
        catch (SQLException e)
        {
            // Logging needed.
        }
        finally {
            if (null != proc) {
                proc.cleanup();
            }
        }
            return responses;
    }

    public String findSurveyIDeWithCourseID(String courseId){
        String surveyID = "";
        CallStoredProcedure proc = null;
        try
        {
            proc = new CallStoredProcedure("spfindSurveyIDeWithCourseID(?)");
            proc.setParameter(1, courseId);
            ResultSet results = proc.executeWithResults();
            if (null != results)
            {

                surveyID = results.getString(2);
            }
        }
        catch (SQLException e)
        {
            // Logging needed.
        }
        finally {
            if (null != proc) {
                proc.cleanup();
            }
        }
        return surveyID;

    }
    public ArrayList <Response> GetResponseByCourseID (String courseID){
        ArrayList <Response> responses = new ArrayList<Response>();
        CallStoredProcedure proc = null;
        try
        {
            proc = new CallStoredProcedure("spGetResponseByCourseID(?)");
            proc.setParameter(1, courseID);
            ResultSet results = proc.executeWithResults();
            if (null != results)
            {
                while (results.next())
                {
                    long id = results.getLong(1);
                    String questionID = results.getString(2);
                    String userID = results.getString(3);
                    String response = results.getString(4);
                    String surveyID = results.getString(5);
                    Response r = new Response();
                    r.setId(id);
                    r.setQuestionID(questionID);
                    r.setUserID(userID);
                    r.setResponse(response);
                    r.setSurveyID(surveyID);
                    responses.add(r);
                }
            }
        }
        catch (SQLException e)
        {
            // Logging needed.
        }
        finally {
            if (null != proc) {
                proc.cleanup();
            }
        }
        return responses;
    }
}
