package sample.Chart;

import javafx.scene.chart.XYChart;

import java.util.List;

public class ChartService {

    public void addInfo(XYChart.Series<Number, Number> lineCharts, List<Double> xVariables, List<Double> yVariables) {
    if(xVariables.size() == yVariables.size()) {
        for (int i = 0; i < xVariables.size(); i++) {
            lineCharts.getData().add(new XYChart.Data<>(xVariables.get(i), yVariables.get(i)));
        }
    }
    }

    public void clearInfo(XYChart.Series<Number, Number> lineCharts) {
        lineCharts.getData().clear();
    }
}
