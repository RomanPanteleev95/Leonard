import edu.uci.ics.jung.graph.Graph;
import ru.ssu.solution.entities.Function;
import ru.ssu.solution.entities.LeonardGraph;
import ru.ssu.solution.services.FunctionBuilderService;
import ru.ssu.solution.services.GraphBuildingService;
import ru.ssu.solution.services.GraphUtilService;
import ru.ssu.solution.services.VisualizationGraphService;
import ru.ssu.solution.services.impl.FunctionBuilderServiceImpl;
import ru.ssu.solution.services.impl.GraphBuildingServiceImpl;
import ru.ssu.solution.services.impl.GraphUtilServiceImpl;
import ru.ssu.solution.services.impl.VisualizationGraphServiceImpl;
import sun.misc.BASE64Decoder;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.*;

public class GraphExample {
    public static void main(String[] args) throws IOException {
        GraphBuildingService graphBuildingService = new GraphBuildingServiceImpl();
        VisualizationGraphService visualizationGraphService = new VisualizationGraphServiceImpl();
        FunctionBuilderService functionBuilderService = new FunctionBuilderServiceImpl();
        GraphUtilService graphUtilService = new GraphUtilServiceImpl();

        Scanner systemScan = new Scanner(System.in);
        String graphFileName = systemScan.nextLine();
        Scanner scan = new Scanner(new FileInputStream("src/main/resources/graphs/"+graphFileName));

        int idx = 1;
        while (scan.hasNext()){
            String g = scan.nextLine();
            LeonardGraph leonardGraph = graphBuildingService.buildLeonardGraphFromTextFormat(g);
            String base64img = visualizationGraphService.getGraphImageInBase64(leonardGraph.getVisualizationGraph());

            BufferedImage image;
            byte[] imageByte;

            BASE64Decoder decoder = new BASE64Decoder();
            imageByte = decoder.decodeBuffer(base64img);
            ByteArrayInputStream bis = new ByteArrayInputStream(imageByte);
            image = ImageIO.read(bis);
            bis.close();

            File outputfile = new File("graph" + idx + ".png");
            ImageIO.write(image, "png", outputfile);
//            System.out.println(idx++);
            idx++;
//            List<List<Integer>> graphList = leonardGraph.getGraphList();
//            for (int i = 0; i < graphList.size(); i++) {
//                for (int j = 0; j < graphList.size(); j++) {
//                    System.out.print(graphList.get(i).get(j) + " ");
//                }
//                System.out.println();
//            }
            System.out.println(leonardGraph.getGraphFunction().getSourceFunction());

//            System.out.println();

        }



    }
}
