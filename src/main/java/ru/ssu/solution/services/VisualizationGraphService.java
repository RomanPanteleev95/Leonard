package ru.ssu.solution.services;

import edu.uci.ics.jung.graph.Graph;

import java.io.IOException;

public interface VisualizationGraphService {

    public String getGraphImageInBase64(Graph graph, String text) throws IOException;

}
