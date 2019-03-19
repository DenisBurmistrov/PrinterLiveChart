package sample.service;

import sample.LogChooser;
import sample.SearchString.SearchString;

import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class BoxCreate {

    private List<String> listOfFoundedStrings = new ArrayList<>();
    private List<Double> listOfVariables = new ArrayList<>();
    private List<Date> listOfTime = new ArrayList<>();
    private List<Double> listOfDifferenceBetweenTime = new ArrayList<>();

    public static Map<String, Pattern> mapOfPatterns = SearchString.mapOfPatterns;

    public BoxCreate() {
        listOfDifferenceBetweenTime.add(0d);
    }

    public BoxOfVariablesList fillBox(String variableToPattern) throws IOException, ParseException {

        BufferedReader br = new BufferedReader(new InputStreamReader(new
                FileInputStream(LogChooser.classPath)));

        String strRead = null;
        Pattern pattern = mapOfPatterns.get(variableToPattern);

        BoxOfVariablesList boxOfVariablesList = new BoxOfVariablesList();
        BoxCreate boxCreate = new BoxCreate();

        while ((strRead = br.readLine()) != null) {
            Matcher matcher = pattern.matcher(strRead);
            if (matcher.find() && !existString(strRead)) {
                boxCreate.getListOfFoundedStrings().add(strRead);
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                Double variableY = Double.parseDouble(strRead.replaceFirst(".*?" + variableToPattern + " = (\\d+)", "$1"));
                Date time = simpleDateFormat.parse
                        (strRead.replaceFirst("(\\d+)-(\\d+)-(\\d+) (\\d+):(\\d+):(\\d+).+", "$1-$2-$3 $4:$5:$6"));
                boxCreate.getListOfVariables().add(variableY);
                boxCreate.getListOfTime().add(time);
            }
        }
        doDifferentBetweenTime(boxCreate.getListOfTime(), boxCreate);
        boxOfVariablesList.setVariable(boxCreate.getListOfVariables());
        boxOfVariablesList.setTime(boxCreate.getListOfDifferenceBetweenTime());
        br.close();
        return boxOfVariablesList;
    }

    private void doDifferentBetweenTime(List<Date> dates, BoxCreate boxCreate) {

        for (int i = 1; i < dates.size(); i++) {
            Double difference = (double) (dates.get(i).getTime() - dates.get(0).getTime())/60000;
            boxCreate.getListOfDifferenceBetweenTime().add(difference);
        }
    }

    private boolean existString(String foundedString) {
        for(String s : listOfFoundedStrings){
            if (s.equals(foundedString))
                return true;
        }
        return false;
    }

    private List<String> getListOfFoundedStrings() {
        return listOfFoundedStrings;
    }

    private List<Double> getListOfVariables() {
        return listOfVariables;
    }

    private List<Date> getListOfTime() {
        return listOfTime;
    }

    private List<Double> getListOfDifferenceBetweenTime() {
        return listOfDifferenceBetweenTime;
    }
}
