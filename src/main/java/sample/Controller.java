package sample;

import javafx.fxml.FXML;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import sample.SearchString.SearchString;

import java.io.File;

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
    public Button buttonTempDosat;

    @FXML
    public Button buttonAirFlowBuild;

    @FXML
    public Button buttonAirFlowOptics;

    @FXML
    public Button buttonPressureInFilter;

    @FXML
    public Button buttonPressureInCamera;

    @FXML
    public Button buttonTempOfCamera;

    @FXML
    public Button buttonOxygen1;

    @FXML
    public Button buttonOxygen2;

    @FXML
    public Button allCharts;

    @FXML
    private Button buttonQAr2;

    @FXML
    private Button choseLogButton;

    @FXML
    Button buttonTempTable;

    public XYChart.Series<Number, Number> lineOnChart = new XYChart.Series<>();

    public static String classPath;

    private LogChooser logChooser = new LogChooser();

    @FXML
    void initialize() {

        liveChart.getData().add(lineOnChart);
        liveChart.setAnimated(false);

        choseLogButton.setOnAction(
                e -> callFileChooser());

        buttonTempTable.setOnAction(event -> {
            try {
                lineOnChart.getData().clear();
                SearchString.listOfFoundedStrings.clear();
                lineOnChart.setName("Tстола");
                logChooser.openFile(new File(classPath), lineOnChart, "Tстола", "Tстола");
            } catch (NullPointerException e) {
                callFileChooser();
            }
        });

        buttonTempDosat.setOnAction(event -> {
            try {
                lineOnChart.getData().clear();
                SearchString.listOfFoundedStrings.clear();
                lineOnChart.setName("Tдозатора");
                logChooser.openFile(new File(classPath), lineOnChart, "Tдозатора", "Tдозатора");
            } catch (NullPointerException e) {
                callFileChooser();
            }
        });

        buttonAirFlowBuild.setOnAction(event -> {
            try {
                lineOnChart.getData().clear();
                SearchString.listOfFoundedStrings.clear();
                lineOnChart.setName("Qниз");
                logChooser.openFile(new File(classPath), lineOnChart, "Qниз", "Qниз");
            } catch (NullPointerException e) {
                callFileChooser();
            }
        });

        buttonAirFlowOptics.setOnAction(event -> {
            try {
                lineOnChart.getData().clear();
                SearchString.listOfFoundedStrings.clear();
                lineOnChart.setName("Qверх");
                logChooser.openFile(new File(classPath), lineOnChart, "Qверх", "Qверх");
            } catch (NullPointerException e) {
                callFileChooser();
            }
        });

        buttonPressureInFilter.setOnAction(event -> {
            try {
                lineOnChart.getData().clear();
                SearchString.listOfFoundedStrings.clear();
                lineOnChart.setName("Pфильтра");
                logChooser.openFile(new File(classPath), lineOnChart, "Pфильтра", "Pфильтра");
            } catch (NullPointerException e) {
                callFileChooser();
            }
        });

        buttonPressureInCamera.setOnAction(event -> {
            try {
                lineOnChart.getData().clear();
                SearchString.listOfFoundedStrings.clear();
                lineOnChart.setName("Pкам");
                logChooser.openFile(new File(classPath), lineOnChart, "Pкам", "Pкам");
            } catch (NullPointerException e) {
                callFileChooser();
            }
        });

        buttonTempOfCamera.setOnAction(event -> {
            try {
                lineOnChart.getData().clear();
                SearchString.listOfFoundedStrings.clear();
                lineOnChart.setName("Tкам");
                logChooser.openFile(new File(classPath), lineOnChart, "Tкам", "Tкам");
            } catch (NullPointerException e) {
                callFileChooser();
            }
        });

        buttonOxygen1.setOnAction(event -> {
            try {
                lineOnChart.getData().clear();
                SearchString.listOfFoundedStrings.clear();
                lineOnChart.setName("O21");
                logChooser.openFile(new File(classPath), lineOnChart, "O21", "O21");
            } catch (NullPointerException e) {
                callFileChooser();
            }
        });

        buttonOxygen2.setOnAction(event -> {
            try {
                lineOnChart.getData().clear();
                SearchString.listOfFoundedStrings.clear();
                lineOnChart.setName("O22");
                logChooser.openFile(new File(classPath), lineOnChart, "O22", "O22");
            } catch (NullPointerException e) {
                callFileChooser();
            }
        });

        buttonQAr2.setOnAction(event -> {
            try {

                lineOnChart.getData().clear();
                SearchString.listOfFoundedStrings.clear();
                lineOnChart.setName("QAr2");
                logChooser.openFile(new File(classPath), lineOnChart, "QAr2", "QAr2");
            } catch (NullPointerException e) {
                callFileChooser();
            }

        });
    }

    private void callFileChooser() {
        logChooser.configureFileChooser(logChooser.getFileChooser());
        File file = logChooser.getFileChooser().showOpenDialog(vBox.getScene().getWindow());
        if (file != null) {
            classPath = file.getPath();

        }
    }
}






