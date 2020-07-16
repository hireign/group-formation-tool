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
    FindSimilarity findSimilarity = new FindSimilarity();
    IAlgorithmPersistence iAlgorithmPersistence = SystemConfig.instance().getAlgorithmDB();

    @RequestMapping(value = "/selectstudent", method = RequestMethod.POST)
    public String findGroupSimarity(Model model,
//            @RequestParam(name = SIMILARITY) String similarity,
//            @RequestParam(name = STUDENTNUMBER) String studentnumber,
//            @RequestParam(name = QUESTIONID) String questionid,
//            @RequestParam(name = GROUPSIZE) String groupsize)
            @ModelAttribute Response response)
    {
        ArrayList<Response> responses = iAlgorithmPersistence.GetResponseByCourseID("3");
        ArrayList<String> userlist= new ArrayList<String>();
        userlist=findSimilarity.findUniqueUser(responses);
        String[] testuserlist = new String[userlist.size()];
        for(int i=0;i<userlist.size();i++) {
            testuserlist[i] = userlist.get(i);
        }
        HashMap<String, HashMap<String,String>> userHashmap = findSimilarity.getUserResponseByQuestion(userlist,responses);

        int[][] twoD_array = findSimilarity.simiarityTwoDArray(userHashmap,testuserlist);
        Arrays.toString(twoD_array);
        System.out.println(Arrays.toString(twoD_array));
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
