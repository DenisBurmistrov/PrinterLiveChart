package sample;

import javafx.scene.chart.XYChart;
import javafx.stage.FileChooser;
import sample.service.chart.ChartFillService;

import java.io.File;

public class LogChooser {

    public static String classPath;

    private final FileChooser fileChooser = new FileChooser();

    public void configureFileChooser(final FileChooser fileChooser){
        fileChooser.setTitle("Выберете Log");
        fileChooser.setInitialDirectory(
                new File("C:/")
        );
    }

    public void openFile(File file, XYChart.Series<Number, Number> lineOnChart, String variableToPattern, ChartFillService chartFillService) {

        classPath = file.getPath();
        chartFillService.readString(variableToPattern, lineOnChart);

    }

    public FileChooser getFileChooser() {
        return fileChooser;
    }
}
