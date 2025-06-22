package in.request.utils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class TextFileReader {

    public static String from(String relativePath) {
        try {
            return new String(Files.readAllBytes(Paths.get("src/test/resources/" + relativePath)));
        } catch (IOException e) {
            throw new RuntimeException("No se pudo leer el archivo: " + relativePath, e);
        }
    }
}

