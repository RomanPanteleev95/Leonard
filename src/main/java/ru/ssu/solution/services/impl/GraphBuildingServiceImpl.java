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
        int adgeNumber = 1;
        for (int i = 0; i < function.getArguments().size(); i++) {
            resultGraph.addVertex("a" + (i+1));
            String[] nodes = function.getArguments().get(i).split(",");
            for (String s : nodes) {
                resultGraph.addEdge("Edge-" + adgeNumber, "a" + (i+1), "a" + s.charAt(1), EdgeType.DIRECTED);
                adgeNumber++;
            }
        }

        return resultGraph;
    }

}
