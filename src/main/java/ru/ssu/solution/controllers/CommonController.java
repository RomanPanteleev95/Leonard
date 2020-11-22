package ru.ssu.solution.controllers;

import edu.uci.ics.jung.graph.Graph;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.ssu.solution.entities.Function;
import ru.ssu.solution.entities.LeonardGraph;
import ru.ssu.solution.services.GraphBuildingService;
import ru.ssu.solution.services.VisualizationGraphService;
import ru.ssu.solution.services.impl.GraphBuildingServiceImpl;
import ru.ssu.solution.services.impl.VisualizationGraphServiceImpl;
import ru.ssu.solution.utils.ControllerUtils;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

import static ru.ssu.solution.Constants.Paths.DOWNLOAD_FILE_DIRECTORY;
import static ru.ssu.solution.Constants.Paths.GRAPHS_DIRECTORY;

@Controller
public class CommonController {

    private GraphBuildingService graphBuildingService = new GraphBuildingServiceImpl();
    private VisualizationGraphService visualizationGraphService = new VisualizationGraphServiceImpl();

    @PostMapping("/files")
    public String handleFileUpload(@RequestParam(value = "vertexes") String vertexCnt, @RequestParam(required = false, value = "isNeedImage") Boolean isNeedImage, Model model ) {
            try {
                List<LeonardGraph> leonardGraphs = calculate(vertexCnt);
                model.addAttribute("files", ControllerUtils.getFilesForDownload());
                if (isNeedImage != null && Integer.parseInt(vertexCnt) < 4) {
                    List<String> graphsImage = new ArrayList<>();
                    for (LeonardGraph leonardGraph : leonardGraphs) {
                        graphsImage.add(visualizationGraphService.getGraphImageInBase64(leonardGraph.getVisualizationGraph(),
                                leonardGraph.getGraphFunction().getSourceFunction()));
                    }
                    model.addAttribute("graph", graphsImage);
                }
                if (isNeedImage != null&& Integer.parseInt(vertexCnt) < 4) {

                }
                return "index";
            } catch (Exception e) {
                model.addAttribute("exceptionText", "SOME EXCEPTION");
                return "exception";
            }
    }

    @GetMapping("/files")
    public String downloadFiles(Model model) throws IOException {
        model.addAttribute("files", ControllerUtils.getFilesForDownload());
        return "index";
    }

    @GetMapping("/download/{fileName}")
    public ResponseEntity<InputStreamResource> downloadFile(@PathVariable String fileName) throws IOException {
        File file = new File(DOWNLOAD_FILE_DIRECTORY + fileName);
        InputStreamResource resource = new InputStreamResource(new FileInputStream(file));

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=" + file.getName())
                .contentType(MediaType.IMAGE_JPEG).contentLength(file.length()).body(resource);

    }

    @GetMapping("/users")
    @PreAuthorize("hasAuthority('users:read')")
    public String getUsers() {
        return "users";
    }

    @GetMapping("/")
    public String welcome() {
        return "redirect:auth/sign_in";
    }

    private void validateTextFile(MultipartFile textFile) throws Exception {
        if (!textFile.getContentType().equals("text/plain")) {
            throw new Exception("Неверный формат файла");
        }
    }

    private List<LeonardGraph> calculate(String vertexCnt) throws IOException {
        List<LeonardGraph> leonardGraphs = new ArrayList<>();
        File file1 = new File( GRAPHS_DIRECTORY + "g" + vertexCnt + "dT.txt");
        FileReader reader = new FileReader(file1);
        BufferedReader bufferedReader = new BufferedReader(reader);

        StringBuilder functions = new StringBuilder();
        bufferedReader.lines().forEach(s -> {
            LeonardGraph leonardGraph = graphBuildingService.buildLeonardGraphFromTextFormat(s);
            leonardGraphs.add(leonardGraph);
            functions.append(leonardGraph.getGraphFunction().getSourceFunction()).append("\n");

            System.out.println();
        });
        FileWriter fileWriter = new FileWriter(DOWNLOAD_FILE_DIRECTORY + "functions_for_" + vertexCnt + "_vertexes_graphs.txt");
        fileWriter.write(String.valueOf(functions));
        fileWriter.close();
        return leonardGraphs;
    }




}
