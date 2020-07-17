package CSCI5308.GroupFormationTool.GroupFormation;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import CSCI5308.GroupFormationTool.SystemConfig;
import CSCI5308.GroupFormationTool.AccessControl.IUserPersistence;
import CSCI5308.GroupFormationTool.AccessControl.User;
import CSCI5308.GroupFormationTool.SurveyManager.Response;

public class FormGroupsCluster {
	private double similarity = 0.7;
	private static HashMap<Response, String> similaritylist = new HashMap<Response, String>();
	private ArrayList<User> studentlist = new ArrayList<User>();

	public static int[][] simiarityTwoDArray (HashMap<Long ,HashMap<Long, String>> userHashMap, String[] studentIds){
	    int hashmapsize = userHashMap.size();
	    int[][] twoD_array = new int[hashmapsize][hashmapsize];
	    for (int index=0; index<studentIds.length; index++) {
	        HashMap<Long, String> hashMapStudent1 = userHashMap.get(studentIds[index]);
	        for (int index2=0; index2< studentIds.length; index2++){
	            HashMap<Long, String> hashMapStudent2 = userHashMap.get(studentIds[index2]);
	            for (Map.Entry iterateStudent1Response : hashMapStudent1.entrySet()) {
	                String key3 = (String)iterateStudent1Response.getKey();
	                String response1= hashMapStudent1.get(key3);
	                String response2= hashMapStudent2.get(key3);
	                Double similarityScore = similarity(response1,response2);
	                if(similarityScore==1.0) {
	                    twoD_array[index][index2] = twoD_array[index][index2] + 1;
	                }
	            }
	        }
	    }
	    return twoD_array;
	}
	public static int[][] disSimiarityTwoDArray (HashMap<Long ,HashMap<Long, String>> userHashMap, String[] studentIds){
	    int hashmapsize = userHashMap.size();
	    int[][] twoD_array = new int[hashmapsize][hashmapsize];
	    for (int index=0; index<studentIds.length; index++) {
	        HashMap<Long, String> hashMapStudent1 = userHashMap.get(studentIds[index]);
	        for (int index2=0; index2< studentIds.length; index2++){
	            HashMap<Long, String> hashMapStudent2 = userHashMap.get(studentIds[index2]);
	            for (Map.Entry iterateStudent1Response : hashMapStudent1.entrySet()) {
	                String key3 = (String)iterateStudent1Response.getKey();
	                String response1= hashMapStudent1.get(key3);
	                String response2= hashMapStudent2.get(key3);
	                Double similarityScore = similarity(response1,response2);
	                if(similarityScore==0.0) {
	                    twoD_array[index][index2] = twoD_array[index][index2] + 1;
	                }
	            }
	        }
	    }
	    return twoD_array;
	}
	public static int[][] findMultipleChoicesimiarityTwoDArray (HashMap<Long ,HashMap<Long, String>> userHashMap, String[] studentIds){
	    int hashmapsize = userHashMap.size();
	    int[][] twoD_array = new int[hashmapsize][hashmapsize];
	    for (int index=0; index<studentIds.length; index++) {
	        HashMap<Long, String> hashMapStudent1 = userHashMap.get(studentIds[index]);
	        for (int index2=0; index2< studentIds.length; index2++){
	            HashMap<Long, String> hashMapStudent2 = userHashMap.get(studentIds[index2]);
	            for (Map.Entry iterateStudent1Response : hashMapStudent1.entrySet()) {
	                String key3 = (String)iterateStudent1Response.getKey();
	                String response1= hashMapStudent1.get(key3);
	                String response2= hashMapStudent2.get(key3);
	                Double similarityScore = similarity(response1,response2);
	                if(similarityScore>=0.7) {
	                    twoD_array[index][index2] = twoD_array[index][index2] + 1;
	                }
	            }
	        }
	    }
	    return twoD_array;
	}
	public static int[][] findMultipleChoiceDisSimiarityTwoDArray (HashMap<Long ,HashMap<Long, String>> userHashMap, String[] studentIds){
	    int hashmapsize = userHashMap.size();
	    int[][] twoD_array = new int[hashmapsize][hashmapsize];
	    for (int index=0; index<studentIds.length; index++) {
	        HashMap<Long, String> hashMapStudent1 = userHashMap.get(studentIds[index]);
	        for (int index2=0; index2< studentIds.length; index2++){
	            HashMap<Long, String> hashMapStudent2 = userHashMap.get(studentIds[index2]);
	            for (Map.Entry iterateStudent1Response : hashMapStudent1.entrySet()) {
	                String key3 = (String)iterateStudent1Response.getKey();
	                String response1= hashMapStudent1.get(key3);
	                String response2= hashMapStudent2.get(key3);
	                Double similarityScore = similarity(response1,response2);
	                if(similarityScore<0.7) {
	                    twoD_array[index][index2] = twoD_array[index][index2] + 1;
	                }
	            }
	        }
	    }
	    return twoD_array;
	}
	
	public static HashMap<Response, String> findsimilar(String similarity, int groupsize, ArrayList<Response> responses) {
		for (int i = 0; i < responses.size(); i++) {
			for (int j = 0; j < responses.size(); j++) {
				double score = similarity(responses.get(i).getResponse(), responses.get(j).getResponse());
				String stringscore = String.valueOf(score);
				similaritylist.put(responses.get(i), stringscore);
			}
		}
		sortByValues(similaritylist);
		return similaritylist;
	}

	public static HashMap sortByValues(HashMap map) {
		List list = new LinkedList(map.entrySet());
		Collections.sort(list, new Comparator() {
			public int compare(Object o1, Object o2) {
				return ((Comparable) ((Map.Entry) (o1)).getValue()).compareTo(((Map.Entry) (o2)).getValue());
			}
		});

		HashMap sortedHashMap = new LinkedHashMap();
		for (Iterator it = list.iterator(); it.hasNext();) {
			Map.Entry entry = (Map.Entry) it.next();
			sortedHashMap.put(entry.getKey(), entry.getValue());
		}
		return sortedHashMap;
	}

	public static double similarity(String s1, String s2) {
		String longer = s1, shorter = s2;
		if (s2.length() > s1.length()) {
			longer = s2;
			shorter = s1;
		}
		int longerLength = longer.length();
		if (longerLength == 0) {
			return 1.0;
		}
		return (longerLength - editDistance(longer, shorter)) / (double) longerLength;
	}

	public static int editDistance(String s1, String s2) {
		s1 = s1.toLowerCase();
		s2 = s2.toLowerCase();
		int[] costs = new int[s2.length() + 1];
		for (int i = 0; i <= s1.length(); i++) {
			int lastValue = i;
			for (int j = 0; j <= s2.length(); j++) {
				if (i == 0)
					costs[j] = j;
				else {
					if (j > 0) {
						int newValue = costs[j - 1];
						if (s1.charAt(i - 1) != s2.charAt(j - 1))
							newValue = Math.min(Math.min(newValue, lastValue), costs[j]) + 1;
						costs[j - 1] = lastValue;
						lastValue = newValue;
					}
				}
			}
			if (i > 0)
				costs[s2.length()] = lastValue;
		}
		return costs[s2.length()];
	}

	public ArrayList<Long> findUniqueUser(ArrayList<Response> responses) {
		ArrayList<Long> uniqueUserList = new ArrayList<Long>();
		for (int i = 0; i < responses.size(); i++) {
			if (!uniqueUserList.contains(responses.get(i).getUserID())) {
				uniqueUserList.add(responses.get(i).getUserID());
			}
		}
		return uniqueUserList;
	}

	public static HashMap<Long, HashMap<Long, String>> getUserResponseByQuestion(ArrayList<Long> userlist,
			ArrayList<Response> responses) {
		HashMap<Long, HashMap<Long, String>> outerMap = new HashMap<Long, HashMap<Long, String>>();

		for (int i = 0; i < userlist.size(); i++) {
			HashMap<Long, String> innerMap = new HashMap<Long, String>();
			for (int j = 0; j < responses.size(); j++) {
				if (userlist.get(i).equals(responses.get(j).getUserID())) {
					innerMap.put(responses.get(j).getQuestionID(), responses.get(j).getResponse());
				}
			}
			outerMap.put(userlist.get(i), innerMap);
		}
		return outerMap;
	}

	public static int[][] simiarityTwoDArray(HashMap<Long, HashMap<Long, String>> userHashmap, Long[] testuserlist) {
		int hashmapsize = userHashmap.size();
		int[][] twoD_array = new int[hashmapsize][hashmapsize];
		for (int index = 0; index < testuserlist.length; index++) {
			HashMap<Long, String> hashMapStudent1 = userHashmap.get(testuserlist[index]);
			for (int index2 = 0; index2 < testuserlist.length; index2++) {
				HashMap<Long, String> hashMapStudent2 = userHashmap.get(testuserlist[index2]);
				for (Map.Entry iterateStudent1Response : hashMapStudent1.entrySet()) {
					long key3 = (long) iterateStudent1Response.getKey();
					String response1 = hashMapStudent1.get(key3);
					String response2 = hashMapStudent2.get(key3);
					if(response1 == null) {
						response1 = "";
					}
					if(response2 == null) {
						response2 = "";
					}
					Double similarityScore = similarity(response1, response2);
					if (similarityScore == 1.0) {
						twoD_array[index][index2] = twoD_array[index][index2] + 1;
					}
				}
			}

		}
		return twoD_array;
	}

	static Integer[][] groupFormation(long groupSize, int[][] distanceMatrix) {
		// find the largest similarity value in 2D array
		// add the index of that two d array to a group
		// create another two array of existing group
		// repeat
		int student_number = distanceMatrix.length;
		long totalGroups = student_number / groupSize;
		// int[] remaniningIndex = new int[student_number];
		Integer[][] formedGroups = new Integer[(int) totalGroups][(int) groupSize];

		for (int i = 0; i < totalGroups; i++) {
			for (int j = 0; j < groupSize; j++) {
				formedGroups[i][j] = -9999;
			}
		}

		int[][] tempDistanceMatrix = distanceMatrix;

		for (int k = 0; k < totalGroups; k++) {
			int currentGroupSize = 0;
			int maxI = 0, maxJ = 0;
			while (currentGroupSize < groupSize) {
				int maxDistance = -10;
				ArrayList<Integer> group = new ArrayList<Integer>();
				for (int i = 0; i < tempDistanceMatrix.length; i++) {
					for (int j = 0; j < tempDistanceMatrix[0].length; j++) {
						if (i > j && maxDistance < tempDistanceMatrix[i][j]
								&& valueExistsInFormedGroup(formedGroups, i, j)) {
							maxDistance = distanceMatrix[i][j];
							maxI = i;
							maxJ = j;

							if (i == maxI || i == maxJ) {
								if (i == maxI) {
									tempDistanceMatrix[maxI][j] = -10;
								} else if (i == maxJ) {
									tempDistanceMatrix[maxJ][j] += distanceMatrix[maxI][j];
								}
								continue;
							}
						}
					}
				}
				maxDistance = 0;
//				group.add(maxI);
//				group.add(maxJ);
//				currentGroupSize += 2;

				formedGroups[k][currentGroupSize] = maxI;
				currentGroupSize += 1;
				if (currentGroupSize < groupSize) {
					formedGroups[k][currentGroupSize] = maxJ;
					currentGroupSize += 1;
				}
			}
		}

		return formedGroups;
	}

	private static boolean valueExistsInFormedGroup(Integer[][] formedGroups, int i, int j) {
		if (formedGroups[0][0] == null) {
			return true;
		}
		for (int jj = 0; jj < formedGroups[0].length; jj++) {

			if ((i > j) && (formedGroups[0][jj] != null && formedGroups[0][jj] == i || formedGroups[0][jj] == j)) {
				return false;
			}
		}

		return true;
	}
	
	public static List<Group> convertToUserList(Long[] testuserlist, Integer[][] formedGroups) throws NumberFormatException, Exception{
    	IUserPersistence userDB = SystemConfig.instance().getUserDB();
    	List<Group> groups = new ArrayList<Group>();
    	
    	
    	for(int i=0; i<formedGroups.length; i++) {
    		Group currentGroup = new Group();
    		List<User> groupList = new ArrayList<User>();
    		for(int j=0; j<formedGroups[0].length; j++) {
    			User user = new User();
    			userDB.loadUserByID(Long.valueOf(testuserlist[formedGroups[i][j]]), user);
    			groupList.add(user);
    		}
    		currentGroup.setStudents(groupList);
    		currentGroup.setGroupID(i+1);
    		groups.add(currentGroup);
    	}
		return groups;
    }
}
