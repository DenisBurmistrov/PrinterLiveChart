package sample.entity;

import javafx.scene.chart.XYChart;
import sample.service.ChartFillService;

import java.io.File;

public class BoxInfoForButton {

    private File file;

    private XYChart.Series<Number, Number> lineOnChart;

    private String variableToPattern;

    private ChartFillService chartFillService;

    public BoxInfoForButton(File file, XYChart.Series<Number, Number> lineOnChart, String variableToPattern,
                            ChartFillService chartFillService) {
        this.file = file;
        this.lineOnChart = lineOnChart;
        this.variableToPattern = variableToPattern;
        this.chartFillService = chartFillService;
    }

    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }

    public XYChart.Series<Number, Number> getLineOnChart() {
        return lineOnChart;
    }

    public void setLineOnChart(XYChart.Series<Number, Number> lineOnChart) {
        this.lineOnChart = lineOnChart;
    }

    public ChartFillService getChartFillService() {
        return chartFillService;
    }

    public void setChartFillService(ChartFillService chartFillService) {
        this.chartFillService = chartFillService;
    }

    public String getVariableToPattern() {
        return variableToPattern;
    }

    public void setVariableToPattern(String variableToPattern) {
        this.variableToPattern = variableToPattern;
    }
}
