package com.nanhng.FastFood.service.upload_file;

import com.nanhng.FastFood.entity.upload_file.UploadFile;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.List;

public interface UploadFileService {
    UploadFile uploadImage(MultipartFile file);
    InputStream getInputStream(final String fileName);
    List<UploadFile> uploadManyImage(List<MultipartFile> listFiles);
}
