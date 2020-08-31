import edu.uci.ics.jung.algorithms.layout.CircleLayout;
import edu.uci.ics.jung.algorithms.layout.Layout;
import edu.uci.ics.jung.graph.Graph;
import edu.uci.ics.jung.graph.OrderedSparseMultigraph;
import edu.uci.ics.jung.graph.util.EdgeType;
import edu.uci.ics.jung.visualization.VisualizationImageServer;
import edu.uci.ics.jung.visualization.VisualizationViewer;
import edu.uci.ics.jung.visualization.decorators.ToStringLabeller;
import edu.uci.ics.jung.visualization.renderers.Renderer;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Date;

public class GraphExample {
//    public static void main(String[] args) {
//        Graph<String, String> g = new OrderedSparseMultigraph<>();
//        g.addVertex("a1");
//        g.addVertex("a2");
//        g.addVertex("a3");
//        g.addEdge("Edge-A", "a1", "a2", EdgeType.DIRECTED);
//        g.addEdge("Edge-B", "a2", "a3", EdgeType.DIRECTED);
//        System.out.println(g.toString());
//
//
//
//        Layout<String, String> layout = new CircleLayout<>(g);
//
//        VisualizationViewer<String, String> vv = new VisualizationViewer<>(layout);
//        VisualizationImageServer<String, String> vis = new VisualizationImageServer<>(vv.getGraphLayout(), vv.getGraphLayout().getSize());
//
//        vis.setBackground(Color.WHITE);
//        vis.getRenderContext().setEdgeLabelTransformer(new ToStringLabeller());
//        vis.getRenderContext().setVertexLabelTransformer(new ToStringLabeller());
//        vis.getRenderer().getVertexLabelRenderer()
//                .setPosition(Renderer.VertexLabel.Position.CNTR);
//
//        BufferedImage image = (BufferedImage) vis.getImage(
//                new Point2D.Double(vv.getGraphLayout().getSize().getWidth() / 2,
//                        vv.getGraphLayout().getSize().getHeight() / 2),
//                new Dimension(vv.getGraphLayout().getSize()));
//
//        // Write image to a png file
//        File outputfile = new File("graph.png");
//
//        try {
//            ImageIO.write(image, "png", outputfile);
//        } catch (IOException e) {
//            // Exception handling
//        }
//
//        System.out.println(new Date("3030/02/10"));
//    }
}
