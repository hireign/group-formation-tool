package CSCI5308.GroupFormationTool.GroupFormation;

import java.util.HashMap;

public class AlgorithmFacade {
	
	private static int[][] algorithmInputWrapper(String Type, String Strategy,
			HashMap<Long, HashMap<Long, String>> userHashmap, String[] userlist) {
		int[][] twoD_array = null;
		if (Type.equals("SIMILAR") && Strategy.equals("MCQ")) {
			twoD_array = FormGroupsCluster.simiarityTwoDArray(userHashmap, userlist);
		}
		if (Type.equals("DISSIMILAR") && Strategy.equals("MCQ")) {
			twoD_array = FormGroupsCluster.disSimiarityTwoDArray(userHashmap, userlist);
		}
		if (Type.equals("SIMILAR") && Strategy.equals("MCQMULTIPLE")) {
			twoD_array = FormGroupsCluster.findMultipleChoicesimiarityTwoDArray(userHashmap, userlist);
		}
		if (Type.equals("DISSIMILAR") && Strategy.equals("MCQMULTIPLE")) {
			twoD_array = FormGroupsCluster.findMultipleChoiceDisSimiarityTwoDArray(userHashmap, userlist);
		}
		return twoD_array;
	}
}
