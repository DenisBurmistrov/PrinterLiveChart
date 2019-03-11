package sample;

import javafx.scene.chart.XYChart;
import javafx.stage.FileChooser;
import sample.Chart.ChartService;
import sample.SearchString.EntityOfLists;
import sample.SearchString.SearchString;
import sample.checkFile.FileWatcher;

import java.io.File;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

public class LogChooser {

    public static String classPath;

    private SearchString searchString = new SearchString();

    private final FileChooser fileChooser = new FileChooser();

    ChartService chartService = new ChartService();

    public void configureFileChooser(final FileChooser fileChooser){
        fileChooser.setTitle("Chose log");
        fileChooser.setInitialDirectory(
                new File("C:/")
        );
    }

    public void openFile(File file, XYChart.Series<Number, Number> lineOnChart, String variableToPattern, String variableToY) {

        classPath = file.getPath();
        searchString.readString(variableToPattern, variableToY, lineOnChart);
        TimerTask task = new FileWatcher(new File(classPath)) {
            protected void onChange(File file) {
                // Выполнение при изменении файла
                searchString.readString(variableToPattern, variableToY, lineOnChart);


                System.out.println("File " + file.getName() + " have change !");

            }
        };

        java.util.Timer timer = new Timer();
        // Готовность к проверке файла
        timer.schedule(task, new Date(), 1000);
    }

    public FileChooser getFileChooser() {
        return fileChooser;
    }
}
