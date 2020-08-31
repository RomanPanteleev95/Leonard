package ru.sgu.solution.services;

import edu.uci.ics.jung.graph.Graph;
import ru.sgu.solution.entities.Function;

public interface GraphBuildingService {

    public Graph buildGraphFromFunction(Function function);

}
