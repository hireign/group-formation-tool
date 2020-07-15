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
import org.springframework.web.bind.annotation.*;
import CSCI5308.GroupFormationTool.SystemConfig;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

@Controller
public class AlgorithmController {

    private final String SIMILARITY = "";
    private final String STUDENTNUMBER = "";
    private final String QUESTIONID = "";
    private final String GROUPSIZE = "";
//    private ArrayList<Response> responses = new ArrayList<Response>();
    IAlgorithmPersistence iAlgorithmPersistence = SystemConfig.instance().getAlgorithmDB();

    @RequestMapping(value = "/selectstudent", method = RequestMethod.POST)
    public String findGroupSimarity(Model model,
//            @RequestParam(name = SIMILARITY) String similarity,
//            @RequestParam(name = STUDENTNUMBER) String studentnumber,
//            @RequestParam(name = QUESTIONID) String questionid,
//            @RequestParam(name = GROUPSIZE) String groupsize)
            @ModelAttribute Response response)
    {
        ArrayList<Response> responses = iAlgorithmPersistence.findResponseWithSelectedQuestion(response.getSurveyID(),response.getQuestionID());
        System.out.println(responses);
        return "index";

    }

    @GetMapping("/selectstudent")
    public String findGroupSimarity(Model model)
    {
        model.addAttribute("response", new Response());
        return "survey/selectstudent";
    }

//    public HashMap <> findSimilarityScore() {
//
//    }
}
