package sample;

import javafx.scene.chart.XYChart;
import sample.SearchString.SearchString;

import java.io.File;

public class BoxInfoForButton {

    private File file;

    private XYChart.Series<Number, Number> lineOnChart;

    private String variableToPattern;

    private SearchString searchString;

    public BoxInfoForButton(File file, XYChart.Series<Number, Number> lineOnChart, String variableToPattern,
                            SearchString searchString) {
        this.file = file;
        this.lineOnChart = lineOnChart;
        this.variableToPattern = variableToPattern;
        this.searchString = searchString;
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

    public SearchString getSearchString() {
        return searchString;
    }

    public void setSearchString(SearchString searchString) {
        this.searchString = searchString;
    }

    public String getVariableToPattern() {
        return variableToPattern;
    }

    public void setVariableToPattern(String variableToPattern) {
        this.variableToPattern = variableToPattern;
    }
}
