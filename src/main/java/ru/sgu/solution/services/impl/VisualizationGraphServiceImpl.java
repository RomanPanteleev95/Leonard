package ru.sgu.solution.services.impl;

import edu.uci.ics.jung.algorithms.layout.CircleLayout;
import edu.uci.ics.jung.algorithms.layout.Layout;
import edu.uci.ics.jung.graph.Graph;
import edu.uci.ics.jung.visualization.VisualizationImageServer;
import edu.uci.ics.jung.visualization.VisualizationViewer;
import edu.uci.ics.jung.visualization.decorators.ToStringLabeller;
import edu.uci.ics.jung.visualization.renderers.Renderer;
import ru.sgu.solution.services.VisualizationGraphService;
import ru.sgu.solution.utils.ControllerUtils;

import java.awt.*;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class VisualizationGraphServiceImpl implements VisualizationGraphService {

    @Override
    public String getGraphImageInBase64(Graph graph) throws IOException {
        Layout<String, String> layout = new CircleLayout<>(graph);

        VisualizationViewer<String, String> vv = new VisualizationViewer<>(layout);
        VisualizationImageServer<String, String> vis = new VisualizationImageServer<>(vv.getGraphLayout(), vv.getGraphLayout().getSize());

        vis.setBackground(Color.WHITE);
        vis.getRenderContext().setEdgeLabelTransformer(new ToStringLabeller());
        vis.getRenderContext().setVertexLabelTransformer(new ToStringLabeller());
        vis.getRenderer().getVertexLabelRenderer()
                .setPosition(Renderer.VertexLabel.Position.CNTR);

        BufferedImage image = (BufferedImage) vis.getImage(
                new Point2D.Double(vv.getGraphLayout().getSize().getWidth() / 2,
                        vv.getGraphLayout().getSize().getHeight() / 2),
                new Dimension(vv.getGraphLayout().getSize()));

        return ControllerUtils.getImgBase64(image);
    }
}
