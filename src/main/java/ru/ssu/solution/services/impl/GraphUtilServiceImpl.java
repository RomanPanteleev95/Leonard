package ru.ssu.solution.services.impl;

import ru.ssu.solution.services.GraphUtilService;

import java.util.ArrayList;
import java.util.List;

public class GraphUtilServiceImpl implements GraphUtilService {

    private List<Integer> colors;
    private List<List<Integer>> graphList;
    private boolean isBipartite = true;

    @Override
    public boolean isBipartiteGraph(List<List<Integer>> graphList) {
        this.graphList = graphList;
        isBipartite = true;
        colors = new ArrayList<>();
        for (int i = 0; i < graphList.size(); i++) {
            colors.add(0);
        }
        dfs(0, 1);
        if (colors.contains(0)) {
            isBipartite = false;
        }
        return isBipartite;
    }

    private void dfs(int vertex, int color) {
        colors.set(vertex, color);
        List<Integer> dijVertex = graphList.get(vertex);
        for (int i = 0; i < dijVertex.size(); i++) {
            if (dijVertex.get(i) != 0 && colors.get(i) == 0) {
                dfs(i, invert(color));
            } else {
                if (dijVertex.get(i) != 0 && i != vertex && colors.get(i) == color) {
                    isBipartite = false;
                    return;
                }
            }
        }
    }

    private int invert(int color) {
        if (color == 1) {
            return 2;
        }
        return 1;
    }
}
