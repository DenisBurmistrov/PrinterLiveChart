package sample;

import javafx.scene.chart.XYChart;
import javafx.stage.FileChooser;
import sample.SearchString.SearchString;

import java.io.File;

public class LogChooser {

    public static String classPath;

    private final FileChooser fileChooser = new FileChooser();

    public void configureFileChooser(final FileChooser fileChooser){
        fileChooser.setTitle("Chose log");
        fileChooser.setInitialDirectory(
                new File("C:/")
        );
    }

    public void openFile(File file, XYChart.Series<Number, Number> lineOnChart, String variableToPattern, SearchString searchString) {

        classPath = file.getPath();
        searchString.readString(variableToPattern, lineOnChart);

    }

    public FileChooser getFileChooser() {
        return fileChooser;
    }
}
