package CSCI5308.GroupFormationTool.Algorithm;

import CSCI5308.GroupFormationTool.AccessControl.User;
import CSCI5308.GroupFormationTool.Database.CallStoredProcedure;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FetchAlgorithmInput implements IAlgorithmPersistence{
    public ArrayList<Response> findResponseWithSelectedQuestion (String questionId){
        ArrayList<Response> responses = new ArrayList<Response>();
        CallStoredProcedure proc = null;
        try
        {
            proc = new CallStoredProcedure("spFindUsersWithoutCourseRole(?)");
            proc.setParameter(1, questionId);
            ResultSet results = proc.executeWithResults();
            if (null != results)
            {
                while (results.next())
                {
                    String questionID = results.getString(1);
                    String userID = results.getString(2);
                    String response = results.getString(3);
                    Response r = new Response();
                    r.setQuestionID(questionID);
                    r.setUserID(userID);
                    r.setResponse(response);
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
