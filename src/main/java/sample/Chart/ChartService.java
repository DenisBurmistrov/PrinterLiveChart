package sample.Chart;

import javafx.scene.chart.XYChart;


public class ChartService {

    public void addInfo(XYChart.Series<Number, Number> lineCharts, Double xVariable, Double yVariable) {
        lineCharts.getData().add(new XYChart.Data<>(xVariable, yVariable));

    }
}