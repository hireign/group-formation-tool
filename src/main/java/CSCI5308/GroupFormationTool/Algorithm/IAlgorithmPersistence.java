package CSCI5308.GroupFormationTool.Algorithm;

import java.util.ArrayList;

public interface IAlgorithmPersistence {
    public ArrayList<Response> findResponseWithSelectedQuestion(String questionID);
}
