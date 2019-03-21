package sample;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.VBox;
import javafx.stage.DirectoryChooser;
import sample.service.ChartFillService;
import sample.entity.BoxInfoForButton;
import sample.service.ExcelService;

import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.util.HashMap;

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
    public Button buttonCreateExcel;

    @FXML
    private Button buttonQAr2;

    @FXML
    private Button choseLogButton;

    @FXML
    Button buttonTempTable;

    private XYChart.Series<Number, Number> lineOnChart = new XYChart.Series<>();

    private ChartFillService chartFillService = new ChartFillService();

    private static String classPath;

    private LogChooser logChooser = new LogChooser();

    public HashMap<Integer, BoxInfoForButton> mapOfBoxes = new HashMap<>();

    public static Integer counter = 0;

    @FXML
    void initialize() {

        liveChart.getData().add(lineOnChart);
        liveChart.setAnimated(false);

        choseLogButton.setOnAction(
                e -> callFileChooser());

        buttonTempTable.setOnAction(event -> {
            try {
                counter = 1;
                updateChart();
            } catch (NullPointerException e) {
                callFileChooser();
            }
        });

        buttonTempDosat.setOnAction(event -> {
            try {
                counter = 2;
                updateChart();
            } catch (NullPointerException e) {
                callFileChooser();
            }
        });

        buttonAirFlowBuild.setOnAction(event -> {
            try {
                counter = 3;
                updateChart();
            } catch (NullPointerException e) {
                callFileChooser();
            }
        });

        buttonAirFlowOptics.setOnAction(event -> {
            try {
                counter = 4;
                updateChart();
            } catch (NullPointerException e) {
                callFileChooser();
            }
        });

        buttonPressureInFilter.setOnAction(event -> {
            try {
                counter = 5;
                updateChart();
            } catch (NullPointerException e) {
                callFileChooser();
            }
        });

        buttonPressureInCamera.setOnAction(event -> {
            try {
                counter = 6;
                updateChart();
            } catch (NullPointerException e) {
                callFileChooser();
            }
        });

        buttonTempOfCamera.setOnAction(event -> {
            try {
                counter = 7;
                updateChart();
            } catch (NullPointerException e) {
                callFileChooser();
            }
        });

        buttonOxygen1.setOnAction(event -> {
            try {
                counter = 8;
                updateChart();
            } catch (NullPointerException e) {
                callFileChooser();
            }
        });

        buttonOxygen2.setOnAction(event -> {
            try {
                counter = 9;
                updateChart();
            } catch (NullPointerException e) {
                callFileChooser();
            }
        });

        buttonQAr2.setOnAction(event -> {
            try {
                counter = 10;
                updateChart();
            } catch (NullPointerException e) {
                callFileChooser();
            }

        });

        buttonCreateExcel.setOnAction(event -> {
            try {
                String pathOut;
                DirectoryChooser directoryChooser = new DirectoryChooser();
                directoryChooser.setTitle("Выбор папки для сохранения лога");
                File selectedDirectory = directoryChooser.showDialog(vBox.getScene().getWindow());

                if(selectedDirectory != null){
                    pathOut = selectedDirectory.getPath();
                    System.out.println(pathOut);
                    ExcelService excelService = new ExcelService();


                    Thread thread = new Thread(() -> excelService.fillTable(ChartFillService.mapOfPatterns, pathOut, classPath));
                    thread.start();

                }

            } catch (NullPointerException e) {
                e.printStackTrace();
                callFileChooser();
            }
        });
    }

    private void updateChart() {
        lineOnChart.getData().clear();
        ChartFillService.listOfFoundedStrings.clear();
        BoxInfoForButton boxInfoForButton = mapOfBoxes.get(counter);
        lineOnChart.setName(boxInfoForButton.getVariableToPattern());
        logChooser.openFile(boxInfoForButton.getFile(), boxInfoForButton.getLineOnChart(), boxInfoForButton.getVariableToPattern(), boxInfoForButton.getChartFillService());
    }

    private void callFileChooser() {
        logChooser.configureFileChooser(logChooser.getFileChooser());
        File file = logChooser.getFileChooser().showOpenDialog(vBox.getScene().getWindow());
        if (file != null) {
            classPath = file.getPath();
            initBoxes(file);

            Thread thread = new Thread(() -> {
                final Path path = Paths.get(file.getParent());
                System.out.println(path);
                try (final WatchService watchService = FileSystems.getDefault().newWatchService()) {
                    path.register(watchService, StandardWatchEventKinds.ENTRY_MODIFY);
                    while (true) {
                        final WatchKey wk = watchService.take();
                        for (WatchEvent<?> event : wk.pollEvents()) {
                            //we only register "ENTRY_MODIFY" so the context is always a Path.
                            final Path changed = (Path) event.context();
                            if (changed.endsWith(file.getName())) {
                                Platform.runLater(this::updateChart);
                                System.out.println("file has changed");
                            }
                        }
                        // reset the key
                        boolean valid = wk.reset();
                        if (!valid) {
                            System.out.println("Key has been unregisterede");
                        }
                    }
                } catch (InterruptedException | IOException e) {
                    e.printStackTrace();
                }
            });
            thread.start();

        }
    }

    private void initBoxes(File file) {
        BoxInfoForButton boxInfoForButtonTtable = new BoxInfoForButton(file, lineOnChart, "Tстола", chartFillService);
        BoxInfoForButton boxInfoForButtonTdosator = new BoxInfoForButton(file, lineOnChart, "Tдозатора", chartFillService);
        BoxInfoForButton boxInfoForButtonQdown = new BoxInfoForButton(file, lineOnChart, "Qниз", chartFillService);
        BoxInfoForButton boxInfoForButtonQup = new BoxInfoForButton(file, lineOnChart, "Qверх", chartFillService);
        BoxInfoForButton boxInfoForButtonPfilter = new BoxInfoForButton(file, lineOnChart, "Pфильтра", chartFillService);
        BoxInfoForButton boxInfoForButtonPcam = new BoxInfoForButton(file, lineOnChart, "Pкам", chartFillService);
        BoxInfoForButton boxInfoForButtonTcam = new BoxInfoForButton(file, lineOnChart, "Tкам", chartFillService);
        BoxInfoForButton boxInfoForButtonQ21 = new BoxInfoForButton(file, lineOnChart, "O21", chartFillService);
        BoxInfoForButton boxInfoForButtonQ22 = new BoxInfoForButton(file, lineOnChart, "O22", chartFillService);
        BoxInfoForButton boxInfoForButtonQar = new BoxInfoForButton(file, lineOnChart, "QAr2", chartFillService);

        mapOfBoxes.put(1, boxInfoForButtonTtable);
        mapOfBoxes.put(2, boxInfoForButtonTdosator);
        mapOfBoxes.put(3, boxInfoForButtonQdown);
        mapOfBoxes.put(4, boxInfoForButtonQup);
        mapOfBoxes.put(5, boxInfoForButtonPfilter);
        mapOfBoxes.put(6, boxInfoForButtonPcam);
        mapOfBoxes.put(7, boxInfoForButtonTcam);
        mapOfBoxes.put(8, boxInfoForButtonQ21);
        mapOfBoxes.put(9, boxInfoForButtonQ22);
        mapOfBoxes.put(10, boxInfoForButtonQar);



    }

}






