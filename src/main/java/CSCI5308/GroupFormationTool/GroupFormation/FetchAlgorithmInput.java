package CSCI5308.GroupFormationTool.GroupFormation;

import CSCI5308.GroupFormationTool.LoggerInterface;
import CSCI5308.GroupFormationTool.SystemConfig;
import CSCI5308.GroupFormationTool.Database.CallStoredProcedure;
import CSCI5308.GroupFormationTool.SurveyManager.Response;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class FetchAlgorithmInput implements IAlgorithmPersistence{
	private static LoggerInterface logger = SystemConfig.instance().getLogger();
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
                    long questionID = results.getLong(1);
                    long userID = results.getLong(2);
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
        	logger.error(FetchAlgorithmInput.class.toString(), String.format("action=findResponseWithSelectedQuestion error=%s", e.getMessage()));
        }
        finally {
            if (null != proc) {
                proc.cleanup();
            }
        }
            return responses;
    }

    public String findSurveyIDeWithCourseID(Long courseId){
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
        	logger.error(FetchAlgorithmInput.class.toString(), String.format("action=findSurveyIDeWithCourseID error=%s", e.getMessage()));
        }
        finally {
            if (null != proc) {
                proc.cleanup();
            }
        }
        return surveyID;

    }
    public ArrayList <Response> GetResponseByCourseID (Long courseID){
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
                    Long questionID = results.getLong(2);
                    Long userID = results.getLong(3);
                    String response = results.getString(4);
                    Long surveyID = results.getLong(5);
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
        	logger.error(FetchAlgorithmInput.class.toString(), String.format("action=GetResponseByCourseID error=%s", e.getMessage()));
        }
        finally {
            if (null != proc) {
                proc.cleanup();
            }
        }
        return responses;
    }
}
