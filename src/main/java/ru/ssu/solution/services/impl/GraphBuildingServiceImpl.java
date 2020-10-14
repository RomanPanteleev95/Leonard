package ru.ssu.solution.services.impl;

import edu.uci.ics.jung.graph.Graph;
import edu.uci.ics.jung.graph.OrderedSparseMultigraph;
import edu.uci.ics.jung.graph.util.EdgeType;
import ru.ssu.solution.entities.Function;
import ru.ssu.solution.services.GraphBuildingService;

public class GraphBuildingServiceImpl implements GraphBuildingService {
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
    public Graph buildGraphFromTextFormat(String graphDescription) {
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

}
