package ru.sgu.solution.controllers;

import edu.uci.ics.jung.graph.Graph;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import ru.sgu.solution.entities.Function;
import ru.sgu.solution.services.GraphBuildingService;
import ru.sgu.solution.services.VisualizationGraphService;
import ru.sgu.solution.services.impl.GraphBuildingServiceImpl;
import ru.sgu.solution.services.impl.VisualizationGraphServiceImpl;
import ru.sgu.solution.utils.ControllerUtils;

import java.io.*;

import static ru.sgu.solution.Constants.Paths.DOWNLOAD_FILE_DIRECTORY;

@Controller
public class GraphController {

    private GraphBuildingService graphBuildingService = new GraphBuildingServiceImpl();
    private VisualizationGraphService visualizationGraphService = new VisualizationGraphServiceImpl();

    @GetMapping("/test")
    public String test() {
        return "index";
    }

    @PostMapping("/files")
    public String handleFileUpload(@RequestParam("file") MultipartFile file, @RequestParam(required = false, value = "isNeedImage") Boolean isNeedImage, Model model ) {
        if (!file.isEmpty()) {
            try {
                validateTextFile(file);
                Graph<String, String> graph = calculate(file);
                model.addAttribute("files", ControllerUtils.getFilesForDownload());
                if (isNeedImage != null) {
                    model.addAttribute("graph", visualizationGraphService.getGraphImageInBase64(graph));
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
    public String downloadFiles(Model model) throws IOException {
        ControllerUtils с = new ControllerUtils();
        model.addAttribute("files", ControllerUtils.getFilesForDownload());
//        model.addAttribute("graph", img);
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


    private void validateTextFile(MultipartFile textFile) throws Exception {
        if (!textFile.getContentType().equals("text/plain")) {
            throw new Exception("Неверный формат файла");
        }
    }

    private Graph calculate(MultipartFile file) throws IOException {
        byte[] bytes = file.getBytes();
        BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(new File(DOWNLOAD_FILE_DIRECTORY + file.getOriginalFilename() + "-result.txt")));
        stream.write(bytes);
        stream.close();
        File file1 = new File( DOWNLOAD_FILE_DIRECTORY + file.getOriginalFilename() + "-result.txt");
        FileReader reader = new FileReader(file1);
        BufferedReader bufferedReader = new BufferedReader(reader);
        String functionStr = bufferedReader.readLine();
        Function function = new Function(functionStr);
        Graph<String, String> graph = graphBuildingService.buildGraphFromFunction(function);
        FileWriter fileWriter = new FileWriter(file1);
        fileWriter.write(graph.toString());
        fileWriter.close();
        return graph;
    }




}
