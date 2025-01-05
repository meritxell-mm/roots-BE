package com.family.api.commons;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class UploadUtils {

    // Funció per desar els fitxers i fotos al sistema de fitxers
    public static String saveFileToDisk(MultipartFile file, String folderName) throws IOException {
        // Crear un directori per desar els fitxers si no existeix
        Path uploadDirectory = Paths.get(folderName);
        if (!Files.exists(uploadDirectory)) {
            Files.createDirectories(uploadDirectory);
        }

        // Obtenir el nom del fitxer
        String fileName = file.getOriginalFilename();
        Path filePath = uploadDirectory.resolve(fileName);

        // Desar el fitxer al directori
        Files.copy(file.getInputStream(), filePath);

        // Retornar la ruta del fitxer per guardar-la a la base de dades
        return filePath.toString();
    }
}
