package CSCI5308.GroupFormationTool.Algorithm;

import CSCI5308.GroupFormationTool.AccessControl.User;
import CSCI5308.GroupFormationTool.SystemConfig;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class FindSimilarity {

    private double similarity = 0.7;
    private HashMap <Response, String> similaritylist = new HashMap<Response,String>();
    private ArrayList<User> studentlist =new ArrayList<User>();

    public HashMap <Response,String> findsimilar(String similarity, int groupsize, ArrayList<Response> responses){
       for(int i=0; i< responses.size(); i++){
           for (int j=0; j< responses.size(); j++){
               double score= similarity(responses.get(i).getResponse(),responses.get(j).getResponse());
               String stringscore = String.valueOf(score);
               similaritylist.put(responses.get(i),stringscore);
           }
       }
        sortByValues(similaritylist);
        return similaritylist;
    }

    public static HashMap sortByValues(HashMap map) {
        List list = new LinkedList(map.entrySet());
        Collections.sort(list, new Comparator() {
            public int compare(Object o1, Object o2) {
                return ((Comparable) ((Map.Entry) (o1)).getValue())
                        .compareTo(((Map.Entry) (o2)).getValue());
            }
        });

        HashMap sortedHashMap = new LinkedHashMap();
        for (Iterator it = list.iterator(); it.hasNext();) {
            Map.Entry entry = (Map.Entry) it.next();
            sortedHashMap.put(entry.getKey(), entry.getValue());
        }
        return sortedHashMap;
    }

    private static void addStudenttoGroup(HashMap <Response, String> similaritylist,
                                                       ArrayList<User> studentlist){

    }

    public static double similarity(String s1, String s2) {
        String longer = s1, shorter = s2;
        if (s2.length() > s1.length()) {
            longer = s2; shorter = s1;
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
                            newValue = Math.min(Math.min(newValue, lastValue),
                                    costs[j]) + 1;
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

    public ArrayList<String> findUniqueUser(ArrayList<Response> responses){
        ArrayList<String> uniqueUserList = new ArrayList<String>();
        for (int i=0; i<responses.size(); i++){
            if (!uniqueUserList.contains(responses.get(i).getUserID())){
                uniqueUserList.add(responses.get(i).getUserID());
            }
        }
        return uniqueUserList;
    }

    public HashMap<String,HashMap<String,String>> getUserResponseByQuestion (ArrayList<String> userlist,
                                         ArrayList<Response> responses) {
        HashMap<String, HashMap<String, String>> outerMap = new HashMap<String, HashMap<String, String>>();

        for (int i=0; i<userlist.size(); i++){
            HashMap<String, String> innerMap = new HashMap<String, String>();
            for (int j=0; j<responses.size(); j++) {
                if (userlist.get(i).equals( responses.get(j).getUserID())) {
                    innerMap.put(responses.get(j).getQuestionID(),responses.get(j).getResponse());
                }
            }
            outerMap.put(userlist.get(i),innerMap);
        }
        return outerMap;
    }

    public int[][] simiarityTwoDArray (HashMap<String,HashMap<String, String>> userHashMap, String[] studentIds){
       int hashmapsize = userHashMap.size();
       int[][] twoD_array = new int[hashmapsize][hashmapsize];
       for (int index=0; index<studentIds.length; index++) {
           HashMap<String, String> hashMapStudent1 = userHashMap.get(studentIds[index]);
           for (int index2=0; index2< studentIds.length; index2++){
               HashMap<String, String> hashMapStudent2 = userHashMap.get(studentIds[index2]);
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
}
