package ru.sgu.solution.services;

import edu.uci.ics.jung.graph.Graph;

import java.io.IOException;

public interface VisualizationGraphService {

    public String getGraphImageInBase64(Graph graph) throws IOException;

}
