package ru.ssu.solution.entities;

import edu.uci.ics.jung.graph.Graph;

import java.util.List;

public class LeonardGraph {

    private Function graphFunction;

    private Graph visualizationGraph;

    private int[][] arrayGraph;

    private List<List<Integer>> graphList;

    public Function getGraphFunction() {
        return graphFunction;
    }

    public void setGraphFunction(Function graphFunction) {
        this.graphFunction = graphFunction;
    }

    public Graph getVisualizationGraph() {
        return visualizationGraph;
    }

    public void setVisualizationGraph(Graph visualizationGraph) {
        this.visualizationGraph = visualizationGraph;
    }

    public int[][] getArrayGraph() {
        return arrayGraph;
    }

    public void setArrayGraph(int[][] arrayGraph) {
        this.arrayGraph = arrayGraph;
    }

    public List<List<Integer>> getGraphList() {
        return graphList;
    }

    public void setGraphList(List<List<Integer>> graphList) {
        this.graphList = graphList;
    }
}
