package ru.ssu.solution.services.impl;

import edu.uci.ics.jung.graph.Graph;
import edu.uci.ics.jung.graph.OrderedSparseMultigraph;
import edu.uci.ics.jung.graph.util.EdgeType;
import ru.ssu.solution.entities.Function;
import ru.ssu.solution.entities.LeonardGraph;
import ru.ssu.solution.services.FunctionBuilderService;
import ru.ssu.solution.services.GraphBuildingService;

import java.util.ArrayList;
import java.util.List;

public class GraphBuildingServiceImpl implements GraphBuildingService {

    private FunctionBuilderService functionBuilderService = new FunctionBuilderServiceImpl();

    @Override
    public Graph<String, String> buildGraphFromFunction(Function function) {
        Graph<String, String> resultGraph = new OrderedSparseMultigraph<>();
        int edgeNumber = 1;
        for (int i = 0; i < function.getArguments().size(); i++) {
            resultGraph.addVertex("a" + (i+1));
            String[] nodes = function.getArguments().get(i).split(",");
            for (String s : nodes) {
                resultGraph.addEdge("Edge-" + edgeNumber, "a" + (i+1), "a" + s.charAt(1), EdgeType.DIRECTED);
                edgeNumber++;
            }
        }

        return resultGraph;
    }

    @Override
    public LeonardGraph buildLeonardGraphFromTextFormat(String graphDescription) {
        LeonardGraph leonardGraph = new LeonardGraph();
        leonardGraph.setVisualizationGraph(getVisualizationGraph(graphDescription));
        leonardGraph.setGraphFunction(functionBuilderService.getFunctionFromTextFormatGraph(graphDescription));
//        leonardGraph.setArrayGraph(getArrayGraph(graphDescription));
//        leonardGraph.setGraphList(graphList(graphDescription));
        return leonardGraph;
    }

    private Graph getVisualizationGraph(String graphDescription) {
        Graph<String, String> resultGraph = new OrderedSparseMultigraph<>();
        String[] description = graphDescription.split(" ");
        int vertexNumber = Integer.parseInt(description[0]);

        for (int i = 0; i < vertexNumber; i++) {
            resultGraph.addVertex("x" + (i+1));
        }

        int edgeNumber = 1;
        for (int i = 2; i < description.length; i+=2) {
            resultGraph.addEdge("Edge-" + edgeNumber,
                    "x" + (Integer.parseInt(description[i]) + 1),
                    "x" + (Integer.parseInt(description[i + 1]) + 1),
                    EdgeType.DIRECTED);
            edgeNumber++;
        }

        return resultGraph;
    }

    private int[][] getArrayGraph(String graphDescription) {
        String[] description = graphDescription.split(" ");
        int vertexNumber = Integer.parseInt(description[0]);
        int[][] graphArray = new int[vertexNumber][vertexNumber];

        for (int i = 0; i < vertexNumber; i++) {
            for (int j = 0; j < vertexNumber; j++) {
                graphArray[i][j] = 0;
            }
        }

        for (int i = 2; i < description.length; i += 2) {
            int sourceVertex = Integer.parseInt(description[i]);
            int targetVertex = Integer.parseInt(description[i + 1]);
            graphArray[sourceVertex][targetVertex] = 1;
        }

        return graphArray;
    }

    private List<List<Integer>> graphList(String graphDescription) {
        String[] description = graphDescription.split(" ");
        int vertexNumber = Integer.parseInt(description[0]);
        List<List<Integer>> graphList = new ArrayList<>();
        for (int i = 0; i < vertexNumber; i++) {
            List<Integer> dijVertexes = new ArrayList<>();
            for (int j = 0; j < vertexNumber; j++) {
                dijVertexes.add(0);
            }
            graphList.add(dijVertexes);
        }

        for (int i = 2; i < description.length; i += 2) {
            int sourceVertex = Integer.parseInt(description[i]);
            int targetVertex = Integer.parseInt(description[i + 1]);
            graphList.get(sourceVertex).set(targetVertex, 1);
        }

        return graphList;
    }

}
