package ru.ssu.solution.entities;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Function {

    private String sourceFunction;

    private List<String> arguments;

    public Function(String sourceFunction) {
        this.sourceFunction = sourceFunction;
        parseFunction();
    }

    public String getSourceFunction() {
        return sourceFunction;
    }

    public List<String> getArguments() {
        return arguments;
    }

    private void parseFunction() {
        String tmp = sourceFunction.substring(3, sourceFunction.lastIndexOf(')'));
        String[] arguments = tmp.split(";");
        this.arguments = new ArrayList<>(Arrays.asList(arguments));
    }

}
