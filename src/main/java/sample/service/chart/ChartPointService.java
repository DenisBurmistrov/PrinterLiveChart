package sample.service.chart;

import javafx.scene.chart.XYChart;
import javafx.scene.control.Tooltip;
import sample.Main;

import java.util.HashMap;
import java.util.Map;

public class ChartPointService {

    private static Map<String, String> mapOfDimensions = new HashMap<>();

    public void addInfo(XYChart.Series<Number, Number> lineCharts, Long xVariable, Double yVariable, String variableOfPattern) {

        XYChart.Data<Number, Number> data = new XYChart.Data<>(xVariable, yVariable);
        lineCharts.getData().add(data);

        Tooltip t = new Tooltip("x: " + xVariable + " мин" + "\ny: " + yVariable + " " + mapOfDimensions.get(variableOfPattern));
        Tooltip.install(data.getNode(), t);
        data.getNode().setStyle("-fx-background-radius: 4px;"
                + "-fx-padding: 4px ;"
                + "");

        data.getNode().setOnMouseClicked(event -> t.show(data.getNode(),  event.getSceneX() + Main.stage.getX(), event.getSceneY() - 50));

        data.getNode().setOnMouseExited(event -> t.hide());
    }

    public static void initDimensions() {
        String dimensionTtable = "°С";
        String dimensionTdosat = "°С";
        String dimensionQairBuil = "л/мин";
        String dimensionQairOpt = "л/мин";
        String dimensionPressureInFilter = "кПа";
        String dimensionPressureInCamera = "кПа";
        String dimensionTempCam = "°С";
        String dimensionOxygen1 = "%";
        String dimensionOxygen2 = "%";
        String dimensionQAr2 = "л/мин";

        mapOfDimensions.put("Tстола", dimensionTtable);
        mapOfDimensions.put("Tдозатора", dimensionTdosat);
        mapOfDimensions.put("Qниз", dimensionQairBuil);
        mapOfDimensions.put("Qверх", dimensionQairOpt);
        mapOfDimensions.put("Pфильтра", dimensionPressureInFilter);
        mapOfDimensions.put("Pкам", dimensionPressureInCamera);
        mapOfDimensions.put("Tкам", dimensionTempCam);
        mapOfDimensions.put("O21", dimensionOxygen1);
        mapOfDimensions.put("O22", dimensionOxygen2);
        mapOfDimensions.put("QAr2", dimensionQAr2);
    }
}