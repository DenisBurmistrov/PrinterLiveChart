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
    private Button buttonP;

    @FXML
    private Button choseLogButton;

    @FXML Button someButton;

    public XYChart.Series<Number, Number> lineOnChart = new XYChart.Series<>();

    public static String classPath;

    LogChooser logChooser = new LogChooser();

    @FXML
    void initialize() {

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
        buttonP.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                lineOnChart.setName("P");
                liveChart.getData().add(lineOnChart);
                logChooser.openFile(new File(classPath), lineOnChart);
                /*lineOnChart.getData().add(new XYChart.Data<>(1, 20));
                lineOnChart.getData().add(new XYChart.Data<>(2, 100));
                lineOnChart.getData().add(new XYChart.Data<>(3, 80));
                lineOnChart.getData().add(new XYChart.Data<>(4, 180));
                lineOnChart.getData().add(new XYChart.Data<>(5, 20));
                lineOnChart.getData().add(new XYChart.Data<>(6, -10));*/
                //liveChart.getData().add(lineOnChart);
            }
        });

        someButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
               // lineOnChart.getData().add(new XYChart.Data<>(3, 5));
            }
        });
    }



}

