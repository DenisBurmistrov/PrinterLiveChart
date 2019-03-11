package sample;

import java.io.File;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import sample.SearchString.SearchString;

public class Controller {

    @FXML
    public VBox vBox;

    @FXML
    public LineChart<Number, Number> liveChart;

    @FXML
    public NumberAxis xAxis;

    @FXML
    public NumberAxis yAxis;

    @FXML
    private Button buttonQAr2;

    @FXML
    private Button choseLogButton;

    @FXML Button someButton;

    public XYChart.Series<Number, Number> lineOnChart = new XYChart.Series<>();

    public static String classPath;

    private LogChooser logChooser = new LogChooser();

    @FXML
    void initialize() {

        liveChart.getData().add(lineOnChart);

        choseLogButton.setOnAction(
                new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(final ActionEvent e) {

                        logChooser.configureFileChooser(logChooser.getFileChooser());
                        File file = logChooser.getFileChooser().showOpenDialog(vBox.getScene().getWindow());
                        if (file != null) {
                            classPath = file.getPath();

                        }

                    }
                });
        buttonQAr2.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                lineOnChart.getData().clear();
                SearchString.listOfFoundedStrings.clear();
                lineOnChart.setName("P");
                logChooser.openFile(new File(classPath), lineOnChart,"QAr2", "QAr2");
            }
        });

        someButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
            }
        });
    }



}

