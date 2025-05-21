package com.nanhng.FastFood.controller;

import com.nanhng.FastFood.entity.upload_file.UploadFile;
import com.nanhng.FastFood.exception.LovelyException;
import com.nanhng.FastFood.service.upload_file.UploadFileService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@Slf4j
@RestController
@RequestMapping("/")
@RequiredArgsConstructor
public class UploadFileController {
    private final UploadFileService uploadFileService;

    @PostMapping("api/v1/media/upload-image")
    public ResponseEntity<UploadFile> uploadImage(@RequestParam("file") final MultipartFile file) {
        if (file == null) {
            throw new LovelyException("cant not upload empty image", HttpStatus.BAD_REQUEST);
        }
        if (file.getSize() > 1024 * 1024 * 20) {
            throw new LovelyException("File size is too large, please choose file smaller than 20MB");
        }
        UploadFile uploadFile = uploadFileService.uploadImage(file);
        return ResponseEntity.ok(uploadFile);
    }

    @GetMapping("image/{fileName:.+}")
    public ResponseEntity<InputStreamResource> getImage(@PathVariable final String fileName) throws Exception {
        return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG)
                .body(new InputStreamResource(uploadFileService.getInputStream("image/" + fileName)));
    }
}
