import edu.uci.ics.jung.algorithms.layout.CircleLayout;
import edu.uci.ics.jung.algorithms.layout.Layout;
import edu.uci.ics.jung.graph.Graph;
import edu.uci.ics.jung.graph.OrderedSparseMultigraph;
import edu.uci.ics.jung.graph.util.EdgeType;
import edu.uci.ics.jung.visualization.VisualizationImageServer;
import edu.uci.ics.jung.visualization.VisualizationViewer;
import edu.uci.ics.jung.visualization.decorators.ToStringLabeller;
import edu.uci.ics.jung.visualization.renderers.Renderer;
import ru.ssu.solution.services.FunctionBuilderService;
import ru.ssu.solution.services.GraphBuildingService;
import ru.ssu.solution.services.VisualizationGraphService;
import ru.ssu.solution.services.impl.FunctionBuilderServiceImpl;
import ru.ssu.solution.services.impl.GraphBuildingServiceImpl;
import ru.ssu.solution.services.impl.VisualizationGraphServiceImpl;
import sun.misc.BASE64Decoder;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.*;
import java.util.List;

public class GraphExample {
    public static void main(String[] args) throws IOException {
        GraphBuildingService graphBuildingService = new GraphBuildingServiceImpl();
        VisualizationGraphService visualizationGraphService = new VisualizationGraphServiceImpl();
        FunctionBuilderService functionBuilderService = new FunctionBuilderServiceImpl();

        Scanner systemScan = new Scanner(System.in);
        String graphFileName = systemScan.nextLine();
        Scanner scan = new Scanner(new FileInputStream("src/main/resources/graphs/"+graphFileName));

        int idx = 1;
        while (scan.hasNext()){
            String g = scan.nextLine();
            Graph graph = graphBuildingService.buildGraphFromTextFormat(g);
            String base64img = visualizationGraphService.getGraphImageInBase64(graph);

            BufferedImage image;
            byte[] imageByte;

            BASE64Decoder decoder = new BASE64Decoder();
            imageByte = decoder.decodeBuffer(base64img);
            ByteArrayInputStream bis = new ByteArrayInputStream(imageByte);
            image = ImageIO.read(bis);
            bis.close();

            File outputfile = new File("graph" + idx + ".png");
            ImageIO.write(image, "png", outputfile);
            System.out.println(idx++);

            String function = functionBuilderService.getFunctionFromTextFormatGraph(g);
            System.out.println(function);

        }


    }
}
