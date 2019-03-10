package sample.SearchString;

import javafx.scene.chart.XYChart;
import org.joda.time.Interval;
import sample.LogChooser;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SearchString {

    /** Массив для внутреннего кеширование строк */
    private List<String> listOfFoundedStrings = new ArrayList<>();
    private List<Double> listOfVariables = new ArrayList<>();
    private List<Date> listOfTime = new ArrayList<>();
    private List<Double> listOfDifferenceBetweenTime = new ArrayList<>();


    public SearchString() {
        listOfDifferenceBetweenTime.add(0d);
    }

    public static Map<String, Pattern> mapOfPatterns = new LinkedHashMap<>();


    /** Чтение строки из файла */
    public EntityOfLists readString(String variableToPattern, String variableToY, XYChart.Series<Number, Number> lineCharts) {
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(new
                    FileInputStream(LogChooser.classPath)));
            String strRead = null;
            Pattern pattern = mapOfPatterns.get(variableToPattern); //"P = \\d+"
            EntityOfLists entityOfLists = new EntityOfLists();
            SearchString searchString = new SearchString();

            while ((strRead = br.readLine()) != null) {
                Matcher matcher = pattern.matcher(strRead);
                if (matcher.find() && !existString(strRead)) {
                    searchString.getListOfFoundedStrings().add(strRead);
                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    Double variableY = Double.parseDouble(strRead.replaceFirst(".*?" + variableToY + " = (\\d+)", "$1"));
                    Date time = simpleDateFormat.parse
                            (strRead.replaceFirst("(\\d+)-(\\d+)-(\\d+) (\\d+):(\\d+):(\\d+).+", "$1-$2-$3 $4:$5:$6"));
                    searchString.getListOfVariables().add(variableY);
                    searchString.getListOfTime().add(time);
                    //System.out.println("Нашли " + strRead);
                }
            }
            doDifferentBetweenTime(searchString.getListOfTime(), searchString);
            entityOfLists.setListOfVariables(searchString.getListOfVariables());
            entityOfLists.setListOfDifferenceBetweenTime(searchString.getListOfDifferenceBetweenTime());
            br.close();
            return entityOfLists;
        }
        catch (Exception e) {
            e.printStackTrace();
            System.err.println("Error file");

        }
        return null;
    }

    private void doDifferentBetweenTime(List<Date> dates, SearchString searchString) {

        for (int i = 1; i < dates.size(); i++) {
            Double difference = (double) (dates.get(i).getTime() - dates.get(0).getTime())/60000;
            searchString.getListOfDifferenceBetweenTime().add(difference);
        }
        System.out.println(searchString.getListOfDifferenceBetweenTime());
    }

    public static void initPatterns() {
        Pattern patternP = Pattern.compile("P = \\d+");
        mapOfPatterns.put("P", patternP);

    }

    private boolean existString(String foundedString) {
        for(String s : listOfFoundedStrings){
            if (s.equals(foundedString))
                return true;
        }
        return false;
    }

    public List<String> getListOfFoundedStrings() {
        return listOfFoundedStrings;
    }

    public List<Double> getListOfVariables() {
        return listOfVariables;
    }

    public List<Date> getListOfTime() {
        return listOfTime;
    }

    public List<Double> getListOfDifferenceBetweenTime() {
        return listOfDifferenceBetweenTime;
    }
}
