package CSCI5308.GroupFormationTool.Algorithm;

import CSCI5308.GroupFormationTool.AccessControl.User;
import CSCI5308.GroupFormationTool.Courses.Course;
import CSCI5308.GroupFormationTool.Courses.ICoursePersistence;
import CSCI5308.GroupFormationTool.Courses.ICourseUserRelationshipPersistence;
import CSCI5308.GroupFormationTool.Courses.Role;
import CSCI5308.GroupFormationTool.QuestionManager.Question;
import CSCI5308.GroupFormationTool.QuestionManager.QuestionType;
import CSCI5308.GroupFormationTool.SystemConfig;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import CSCI5308.GroupFormationTool.SystemConfig;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

@Controller
public class AlgorithmController {

    private final String SIMILARITY = "similar";
    private final String STUDENTNUMBER = "4";
    private final String QUESTIONID = "1";
    private final String GROUPSIZE = "3";
    private ArrayList<Response> responses = new ArrayList<Response>();

    @RequestMapping(value = "/selectstudent", method = RequestMethod.POST)
    public String findGroupSimarity(
            @RequestParam(name = SIMILARITY) String similarity,
            @RequestParam(name = STUDENTNUMBER) String studentnumber,
            @RequestParam(name = QUESTIONID) String questionid,
            @RequestParam(name = GROUPSIZE) String groupsize)
    {
//        IAlgorithmPersistence algorithmPersistence = SystemConfig.instance()

        return similarity;
    }


//    public HashMap <> findSimilarityScore() {
//
//    }
}
