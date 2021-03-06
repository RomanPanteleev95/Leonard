package ru.ssu.solution.services.impl;

import edu.uci.ics.jung.algorithms.layout.CircleLayout;
import edu.uci.ics.jung.algorithms.layout.Layout;
import edu.uci.ics.jung.graph.Graph;
import edu.uci.ics.jung.visualization.VisualizationImageServer;
import edu.uci.ics.jung.visualization.VisualizationViewer;
import edu.uci.ics.jung.visualization.decorators.ToStringLabeller;
import edu.uci.ics.jung.visualization.renderers.Renderer;
import ru.ssu.solution.services.VisualizationGraphService;
import ru.ssu.solution.utils.ControllerUtils;
import sun.misc.BASE64Decoder;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;

public class VisualizationGraphServiceImpl implements VisualizationGraphService {

    @Override
    public String getGraphImageInBase64(Graph graph, String text) throws IOException {
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

        Graphics g = image.getGraphics();
        g.setFont(new Font("TimesRoman", Font.PLAIN, 25));
        g.setColor(Color.BLACK);
        g.drawString(text,7, 55);
        g.dispose();

        return ControllerUtils.getImgBase64(image);
    }

    public File getPngImageGraph(Graph graph, String text) throws IOException {
//        String base64Image = "data:image/png;base64," + getGraphImageInBase64(graph, text);
        BufferedImage image = null;
        byte[] imageByte;

        BASE64Decoder decoder = new BASE64Decoder();
        imageByte = decoder.decodeBuffer(getGraphImageInBase64(graph, text));
        ByteArrayInputStream bis = new ByteArrayInputStream(imageByte);
        image = ImageIO.read(bis);
        bis.close();

        File outputfile = new File("image.png");
        ImageIO.write(image, "png", outputfile);
        return outputfile;
    }
}
