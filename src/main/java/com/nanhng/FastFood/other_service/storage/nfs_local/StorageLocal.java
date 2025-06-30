package com.nanhng.FastFood.other_service.storage.nfs_local;

import com.nanhng.FastFood.other_service.storage.StorageResource;
import com.nanhng.FastFood.exception.LovelyException;
import io.github.classgraph.Resource;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Slf4j
public class StorageLocal implements StorageResource {

    private final StorageNfsConfig config;

    public StorageLocal(StorageNfsConfig config) {
        this.config = config;
    }

    @Override
    public InputStream readResource(String path) {
        path = fixPath(path);
        String src = String.format("%s/%s",config.getDirectory(), path);
        InputStream in;
        try {
            in = new BufferedInputStream(new FileInputStream(src));
            return in;
        } catch (FileNotFoundException e) {
            log.error("File not found : {}", path);
            throw new LovelyException("File not found!", HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public String writeResource(InputStream inputStream, String path) {
        path = fixPath(path);
        String src = String.format("%s/%s", config.getDirectory(), path);
        File file = new File(src);
        try {
            FileUtils.copyInputStreamToFile(inputStream, file);
        } catch (IOException e) {
            log.error("Write file error : {}", src);
            throw new LovelyException("Write file error", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return String.format("%s%s", config.getHostname(), path);
    }

    @Override
    public boolean deleteFile(String file) {
        file = fixPath(file);
        String src = String.format("%s/%s", config.getDirectory(), file);
        try {
            Path path;
            path = Paths.get(src);
            Files.deleteIfExists(path);
        } catch (IOException e) {
            log.info("Can not remove temporary files");
            return false;
        }
        return true;
    }

    private String fixPath(String path) {
        if (path.startsWith("/")) {
            path = path.substring(1);
        }
        return path;
    }

}
