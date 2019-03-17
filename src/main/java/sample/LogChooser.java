package sample;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.chart.XYChart;
import javafx.stage.FileChooser;
import javafx.util.Duration;
import sample.Chart.ChartService;
import sample.SearchString.SearchString;
import sample.checkFile.FileWatcher;

import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

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
