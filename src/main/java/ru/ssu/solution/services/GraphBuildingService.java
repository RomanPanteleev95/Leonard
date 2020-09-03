package ru.ssu.solution.services;

import edu.uci.ics.jung.graph.Graph;
import ru.ssu.solution.entities.Function;

public interface GraphBuildingService {

    public Graph buildGraphFromFunction(Function function);

}
