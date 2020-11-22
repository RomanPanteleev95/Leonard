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

@Controller
public class CommonController {

    private GraphBuildingService graphBuildingService = new GraphBuildingServiceImpl();
    private VisualizationGraphService visualizationGraphService = new VisualizationGraphServiceImpl();

    @PostMapping("/files")
    public String handleFileUpload(@RequestParam("file") MultipartFile file, @RequestParam(required = false, value = "isNeedImage") Boolean isNeedImage, Model model ) {
        if (!file.isEmpty()) {
            try {
                validateTextFile(file);
                List<LeonardGraph> graph = calculate(file);
                model.addAttribute("files", ControllerUtils.getFilesForDownload());
                if (isNeedImage != null) {
//                    model.addAttribute("graph", visualizationGraphService.getGraphImageInBase64(graph));
                }
                return "index";
            } catch (Exception e) {
                model.addAttribute("exceptionText", "SOME EXCEPTION");
                return "exception";
            }
        } else {
            return "exception";
        }
    }

    @GetMapping("/files")
    @PreAuthorize("hasAuthority('files:read')")
    public String downloadFiles(Model model) throws IOException {
        ControllerUtils с = new ControllerUtils();
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

    private void validateTextFile(MultipartFile textFile) throws Exception {
        if (!textFile.getContentType().equals("text/plain")) {
            throw new Exception("Неверный формат файла");
        }
    }

    private List<LeonardGraph> calculate(MultipartFile file) throws IOException {
        List<LeonardGraph> leonardGraphs = new ArrayList<>();
        byte[] bytes = file.getBytes();
        BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(new File(DOWNLOAD_FILE_DIRECTORY + file.getOriginalFilename() + "-result.txt")));
        stream.write(bytes);
        stream.close();
        File file1 = new File( DOWNLOAD_FILE_DIRECTORY + file.getOriginalFilename() + "-result.txt");
        FileReader reader = new FileReader(file1);
        BufferedReader bufferedReader = new BufferedReader(reader);

        StringBuilder functions = new StringBuilder();
        bufferedReader.lines().forEach(s -> {
            LeonardGraph leonardGraph = graphBuildingService.buildLeonardGraphFromTextFormat(s);
            leonardGraphs.add(leonardGraph);
            functions.append(leonardGraph.getGraphFunction().getSourceFunction()).append("\n");

            System.out.println();
        });
        FileWriter fileWriter = new FileWriter(file1);
        fileWriter.write(String.valueOf(functions));
        fileWriter.close();
        return leonardGraphs;
    }




}
