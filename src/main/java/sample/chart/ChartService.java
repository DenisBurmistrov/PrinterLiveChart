package sample.chart;

import javafx.scene.chart.XYChart;
import javafx.scene.control.Tooltip;
import sample.Main;

public class ChartService {

    public void addInfo(XYChart.Series<Number, Number> lineCharts, Double xVariable, Double yVariable) {

        XYChart.Data<Number, Number> data = new XYChart.Data<>(xVariable, yVariable);
        lineCharts.getData().add(data);

        Tooltip t = new Tooltip("x: " + xVariable + "\ny: " + yVariable);
        Tooltip.install(data.getNode(), t);

        data.getNode().setOnMouseClicked(event -> t.show(data.getNode(),  event.getSceneX() + Main.stage.getX(), event.getSceneY() - 50));

        data.getNode().setOnMouseExited(event -> t.hide());
    }
}