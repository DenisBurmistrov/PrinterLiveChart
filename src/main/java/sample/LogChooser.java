package sample;

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

    public void openFile(File file) {

        classPath = file.getPath();
        EntityOfLists entityOfLists = searchString.readString("P", "P", null);
        chartService.addInfo(Controller.lineOnChart,
                entityOfLists.getListOfDifferenceBetweenTime(), entityOfLists.getListOfVariables());
        TimerTask task = new FileWatcher(new File(classPath)) {
            protected void onChange(File file) {
                // Выполнение при изменении файла



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
