package sample.entity;

import java.util.ArrayList;
import java.util.List;

public class BoxOfVariablesList {

    private List<Double> time = new ArrayList<>();

    private List<Double> variable = new ArrayList<>();

    public List<Double> getTime() {
        return time;
    }

    public void setTime(List<Double> time) {
        this.time = time;
    }

    public List<Double> getVariable() {
        return variable;
    }

    public void setVariable(List<Double> variable) {
        this.variable = variable;
    }
}
