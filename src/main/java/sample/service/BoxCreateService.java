package sample.service;

import sample.entity.BoxOfVariablesList;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class BoxCreateService {

    private List<String> listOfFoundedStrings = new ArrayList<>();
    private List<Double> listOfVariables = new ArrayList<>();
    private List<Date> listOfTime = new ArrayList<>();
    private List<Double> listOfDifferenceBetweenTime = new ArrayList<>();

    public static Map<String, Pattern> mapOfPatterns = ChartFillService.mapOfPatterns;

    public BoxCreateService() {
        listOfDifferenceBetweenTime.add(0d);
    }

    public BoxOfVariablesList fillBox(String variableToPattern, String pathRead) throws IOException, ParseException {

        BufferedReader br = new BufferedReader(new InputStreamReader(new
                FileInputStream(pathRead), StandardCharsets.UTF_8));

        String strRead = null;
        Pattern pattern = mapOfPatterns.get(variableToPattern);

        BoxOfVariablesList boxOfVariablesList = new BoxOfVariablesList();
        BoxCreateService boxCreateService = new BoxCreateService();

        while ((strRead = br.readLine()) != null) {
            System.out.println(strRead);
            Matcher matcher = pattern.matcher(strRead);
            if (matcher.find() && !existString(strRead)) {
                boxCreateService.getListOfFoundedStrings().add(strRead);
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                Double variableY = Double.parseDouble(strRead.replaceFirst(".*?" + variableToPattern + "=(.+)", "$1"));
                Date time = simpleDateFormat.parse
                        (strRead.replaceFirst("(\\d+)-(\\d+)-(\\d+) (\\d+):(\\d+):(\\d+).+", "$1-$2-$3 $4:$5:$6"));
                boxCreateService.getListOfVariables().add(variableY);
                boxCreateService.getListOfTime().add(time);
            }
        }
        doDifferentBetweenTime(boxCreateService.getListOfTime(), boxCreateService);
        System.out.println(boxCreateService.getListOfVariables());
        System.out.println((boxCreateService.getListOfDifferenceBetweenTime()));
        boxOfVariablesList.setVariable(boxCreateService.getListOfVariables());
        boxOfVariablesList.setTime(boxCreateService.getListOfDifferenceBetweenTime());
        br.close();
        return boxOfVariablesList;
    }

    private void doDifferentBetweenTime(List<Date> dates, BoxCreateService boxCreateService) {

        for (int i = 1; i < dates.size(); i++) {
            Double difference = (double) (dates.get(i).getTime() - dates.get(0).getTime())/60000;
            boxCreateService.getListOfDifferenceBetweenTime().add(difference);
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
