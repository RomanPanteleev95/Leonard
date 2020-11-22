package ru.ssu.solution.utils;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Base64;

import static ru.ssu.solution.Constants.Paths.DOWNLOAD_FILE_DIRECTORY;

public class ControllerUtils {

    public static ArrayList<String> getFilesForDownload() {
        return getFileList(DOWNLOAD_FILE_DIRECTORY);
//        return new ArrayList<>();
    }

    private static ArrayList<String> getFileList(String path) {
        File dir = new File(path);
        File[] files = dir.listFiles();
        ArrayList<String> filePaths = new ArrayList();
        for (File file : files) {
            filePaths.add(file.getName());
        }
        return filePaths;
    }

    public static String getImgBase64(BufferedImage bufferedImage) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream =  new ByteArrayOutputStream();
        ImageIO.write(bufferedImage, "png", byteArrayOutputStream);
        byteArrayOutputStream.flush();
        byte[] imageInByte = byteArrayOutputStream.toByteArray();
        byteArrayOutputStream.close();
        return Base64.getEncoder().encodeToString(imageInByte);
    }


}
