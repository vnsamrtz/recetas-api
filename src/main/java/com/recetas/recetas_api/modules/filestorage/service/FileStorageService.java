package com.recetas.recetas_api.modules.filestorage.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@Service
public class FileStorageService {

    @Value("${app.upload.dir}")
    private String uploadDir;

    public String guardarArchivo(MultipartFile archivo) {
        try {
            Path carpeta = Paths.get(uploadDir);
            if (!Files.exists(carpeta)) {
                Files.createDirectories(carpeta);
            }

            String extension = "";
            String nombreOriginal = archivo.getOriginalFilename();
            if (nombreOriginal != null && nombreOriginal.contains(".")) {
                extension = nombreOriginal.substring(nombreOriginal.lastIndexOf("."));
            }
            String nombreUnico = UUID.randomUUID() + extension;

            Path destino = carpeta.resolve(nombreUnico);
            Files.copy(archivo.getInputStream(), destino);

            return nombreUnico;

        } catch (IOException e) {
            throw new RuntimeException("Error al guardar el archivo: " + e.getMessage());
        }
    }
}