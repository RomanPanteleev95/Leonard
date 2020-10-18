package ru.ssu.solution.services.impl;

import edu.uci.ics.jung.graph.util.EdgeType;
import ru.ssu.solution.entities.Function;
import ru.ssu.solution.services.FunctionBuilderService;

import java.util.ArrayList;
import java.util.List;

public class FunctionBuilderServiceImpl implements FunctionBuilderService {

    @Override
    public Function getFunctionFromTextFormatGraph(String graphDescription) {
        String[] description = graphDescription.split(" ");
        int vertexNumber = Integer.parseInt(description[0]);
        List<String> nodes = new ArrayList<>();
        for (int i = 0; i < vertexNumber; i++) {
            nodes.add("");
        }
        for (int i = 2; i < description.length; i+=2) {
            String nodeElements = nodes.get(Integer.parseInt(description[i]));
            if (!nodeElements.isEmpty()) {
                nodeElements += ",";
            }
            nodeElements += "x" + (Integer.parseInt(description[i + 1]) + 1);
            nodes.set(Integer.parseInt(description[i]), nodeElements);
        }
        for (int i = 0; i < nodes.size(); i++) {
            if (nodes.get(i).isEmpty()) {
                nodes.set(i, "E");
            }
        }
        String result = "f=(";
        for (String node : nodes) {
            result += node + ";";
        }
        result = result.substring(0, result.length() - 1) + ")";
        return new Function(result);
    }
}
