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
    public static List<String> listOfFoundedStrings = new ArrayList<>();

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
                        Double firstVariableOfY = Double.parseDouble(strRead.replaceFirst(".*?" + variableToY + "=(.+)", "$1"));
                        chartService.addInfo(lineOnChart, 0D, firstVariableOfY);
                        i++;
                        continue;
                    }

                    listOfFoundedStrings.add(strRead);

                    Double yVariable = Double.parseDouble(strRead.replaceFirst(".*?" + variableToY + "=(.+)", "$1"));
                    Date time = simpleDateFormat.parse
                            (strRead.replaceFirst("(\\d+)-(\\d+)-(\\d+) (\\d+):(\\d+):(\\d+).+", "$1-$2-$3 $4:$5:$6"));
                   Double xVariable = (double)(time.getTime() - firstDate.getTime())/60000;
                   chartService.addInfo(lineOnChart, xVariable, yVariable);
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
        Pattern patternQAr2 = Pattern.compile("QAr2=.+");
        Pattern patternTtable = Pattern.compile("Tстола=.+");
        mapOfPatterns.put("QAr2", patternQAr2);


    }

    private boolean existString(String foundedString) {
        for(String s : listOfFoundedStrings){
            if (s.equals(foundedString))
                return true;
        }
        return false;
    }
}
