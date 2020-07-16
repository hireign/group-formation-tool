package CSCI5308.GroupFormationTool.Algorithm;

import java.awt.*;
import java.util.ArrayList;

public interface IAlgorithmPersistence {
    public ArrayList<Response> GetResponseByCourseID(String CourseID);
}
