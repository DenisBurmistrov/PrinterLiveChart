package sample.SearchString;

import javafx.scene.chart.XYChart;
import org.joda.time.Interval;
import sample.Chart.ChartService;
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
    public void readString(String variableToPattern, String variableToY, XYChart.Series<Number, Number> lineOnChart) {
        try {
            ChartService chartService = new ChartService();
            BufferedReader br = new BufferedReader(new InputStreamReader(new
                    FileInputStream(LogChooser.classPath)));
            String strRead = null;
            Pattern pattern = mapOfPatterns.get(variableToPattern); //"P = \\d+"
            SearchString searchString = new SearchString();
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            int i = 0;
            Date firstDate = null;

            while ((strRead = br.readLine()) != null) {
                Matcher matcher = pattern.matcher(strRead);
                if (matcher.find() && !existString(strRead)) {

                    if(i == 0) {
                        firstDate = simpleDateFormat.parse
                                (strRead.replaceFirst("(\\d+)-(\\d+)-(\\d+) (\\d+):(\\d+):(\\d+).+", "$1-$2-$3 $4:$5:$6"));
                        Double firstVariableOfY = Double.parseDouble(strRead.replaceFirst(".*?" + variableToY + " = (\\d+)", "$1"));
                        chartService.addInfo(lineOnChart, 0D, firstVariableOfY);
                        i++;
                        continue;
                    }

                    searchString.getListOfFoundedStrings().add(strRead);

                    Double yVariable = Double.parseDouble(strRead.replaceFirst(".*?" + variableToY + " = (\\d+)", "$1"));
                    Date time = simpleDateFormat.parse
                            (strRead.replaceFirst("(\\d+)-(\\d+)-(\\d+) (\\d+):(\\d+):(\\d+).+", "$1-$2-$3 $4:$5:$6"));
                   /* searchString.getListOfVariables().add(variableY);
                    searchString.getListOfTime().add(time);*/
                   Double xVariable = (double)(time.getTime() - firstDate.getTime())/60000;
                   chartService.addInfo(lineOnChart, xVariable, yVariable);

                    //System.out.println("Нашли " + strRead);
                }
            }
            br.close();
        }
        catch (Exception e) {
            e.printStackTrace();
            System.err.println("Error file");

        }
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
