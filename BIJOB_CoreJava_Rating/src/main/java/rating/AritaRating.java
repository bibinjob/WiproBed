package main.java.rating;

import java.rmi.server.ExportException;
import java.util.*;

public class AritaRating {

    List<List<String>> AssignmentList = new ArrayList<>();
    String[] assignmentCategory;
    Map<String, Integer> AssignmentWeightMap = new HashMap<String, Integer>();

    public String loadData() {
        try {
            Properties prop = new Properties();
            prop.load(AritaRating.class.getClassLoader().getResourceAsStream("Aritha.properties"));
            String student = prop.getProperty("assignmentCategory");
            String subject = prop.getProperty("assignmentWeight");
            String assignment = prop.getProperty("assignments");
            System.out.println(student);
            System.out.println(subject);
            System.out.println(assignment);

            assignmentCategory = prop.get("assignmentCategory").toString().split(",");
            String[] assignmentWeight = prop.get("assignmentWeight").toString().split(",");
            String[] assignmentArray = assignment.split("~");



            for (int i = 0; i < assignmentCategory.length; i++) {
                AssignmentWeightMap.put(assignmentCategory[i], Integer.parseInt(assignmentWeight[i]));
                System.out.println("" + assignmentCategory[i] + "-" + Integer.parseInt(assignmentWeight[i]));
            }


            for (int i = 0; i < assignmentArray.length; i++) {
                String[] itemArray = assignmentArray[i].toString().split(",");
                List<String> itemList = new ArrayList<>();
                for (int j = 0; j < 6; j++) {
                    itemList.add(itemArray[j]);
                }
                AssignmentList.add(itemList);


                System.out.println(itemList);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    public String result(String item, String value) {
        if (item.equalsIgnoreCase("subject")) {
            loadStudent(value);
        } else if (item.equalsIgnoreCase("student")) {

        } else {
            System.out.println("Invalid Entry");
        }
        return null;
    }

    public String loadStudent(String value) {
        loadData();
        System.out.println("Load Student=======================" + AssignmentList.size());
        List<List<String>> StudentList = new ArrayList<>();
        Set<String> subjectSet = new HashSet<>();
        ListIterator<List<String>>
                iterator = AssignmentList.listIterator();
        while (iterator.hasNext()) {
            List<String> temp = iterator.next();
            if (temp.get(1).equalsIgnoreCase(value)) {
                subjectSet.add(temp.get(2));
                // System.out.println("Student LIst> "+temp.get(2)+temp.get(3).split("_")[0]+"~"+temp.get(5));
                List<String> subList = new ArrayList<>();

                subList.add(temp.get(2));
                subList.add(temp.get(3).split("_")[0]);
                subList.add(temp.get(5));
                System.out.println(subList);
                StudentList.add(subList);
            }
        }

        System.out.println("StudentList Size>" + StudentList.size());
        System.out.println("subjectSet Size>" + subjectSet.size());


        System.out.println("   ");
        System.out.println("  ARITHA RATING  ");
        System.out.println("Student Name:" + value);
        for (Iterator<String> it = subjectSet.iterator(); it.hasNext(); ) {
            String subject = it.next();
            System.out.println("SUBJECT: " + subject);


            for (int x = 0; x < assignmentCategory.length; x++) {
                int catValueSum =0;
                int count =0;
                ListIterator<List<String>>
                        studIterator = StudentList.listIterator();
                while (studIterator.hasNext()) {
                    List<String> temp2 = studIterator.next();
                    if (temp2.get(0).equalsIgnoreCase(subject)) {


                        if (assignmentCategory[x].equalsIgnoreCase(temp2.get(1))) {
                            //System.out.println(assignmentCategory[x] + "<>" + temp2.get(2));
                            catValueSum = catValueSum +Integer.parseInt(temp2.get(2));
                            count = count +1;
                        }
                    }
                }

                int generalWeight = AssignmentWeightMap.get(assignmentCategory[x]);
                //System.out.println(assignmentCategory[x]+ "weight ="+ generalWeight);
               //System.out.println(assignmentCategory[x]+"******"+catValueSum);
                float score=0;
                String score1= "";
                if(count>0) {
                    score= ((generalWeight / count) * catValueSum) / 100;
                    score1 = ""+score;
                }else{
                    score1 = "NA";
                }
                System.out.println(assignmentCategory[x]+" Score :"+score1);
            }
        }
        return null;

    }



    public String sha256hex(String INPUT) {
        return INPUT;
    }

}
