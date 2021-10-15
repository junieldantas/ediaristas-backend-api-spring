package com.junieldantas.ediaristas.services;

import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@Service
public class FileService {

    private final Path pastaUpload = Paths.get("uploads");

    public String salvar(MultipartFile file) throws IOException {
        if(!Files.exists(pastaUpload)){
            Files.createDirectories(pastaUpload);
        }

        var originalFileName = file.getOriginalFilename();
        var ext = originalFileName.split("\\.")[1];
        var fileName = UUID.randomUUID().toString() + "." + ext;

        Files.copy(file.getInputStream(), pastaUpload.resolve(fileName));

        return fileName;
    }

    public Resource carregar(String fileName) throws MalformedURLException {
        var fielaPath = pastaUpload.resolve(fileName);
        return new UrlResource(fielaPath.toUri());
    }
}
