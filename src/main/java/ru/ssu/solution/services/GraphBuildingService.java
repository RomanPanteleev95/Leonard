package ru.ssu.solution.services;

import edu.uci.ics.jung.graph.Graph;
import ru.ssu.solution.entities.Function;
import ru.ssu.solution.entities.LeonardGraph;

public interface GraphBuildingService {

    public Graph buildGraphFromFunction(Function function);

    public LeonardGraph buildLeonardGraphFromTextFormat(String graphDescription);

}
