package sample.entity;

import java.util.ArrayList;
import java.util.List;

public class BoxOfVariablesList {

    private List<Long> time = new ArrayList<>();

    private List<Double> variable = new ArrayList<>();

    public List<Long> getTime() {
        return time;
    }

    public void setTime(List<Long> time) {
        this.time = time;
    }

    public List<Double> getVariable() {
        return variable;
    }

    public void setVariable(List<Double> variable) {
        this.variable = variable;
    }
}
