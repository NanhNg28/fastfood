package com.nanhng.FastFood.controller;

import com.nanhng.FastFood.dto.response.BaseResponse;
import com.nanhng.FastFood.entity.upload_file.UploadFile;
import com.nanhng.FastFood.exception.LovelyException;
import com.nanhng.FastFood.service.upload_file.UploadFileService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/")
@RequiredArgsConstructor
public class UploadFileController {
    private final UploadFileService uploadFileService;

    @Value("${system.backend.url}")
    private String BACKEND_URL;

//    @Operation(summary = "admin add product image")//done
//    @PostMapping("api/v1/food/upload-image")
//    public ResponseEntity<UploadFile> uploadProductImage(@RequestParam("file") final MultipartFile file, @RequestParam("productId")Integer productId) {
//        if (file == null) {
//            throw new LovelyException("cant not upload empty image", HttpStatus.BAD_REQUEST);
//        }
//        if (file.getSize() > 1024 * 1024 * 20) {
//            throw new LovelyException("File size is too large, please choose file smaller than 20MB");
//        }
//        UploadFile uploadFile = uploadFileService.uploadImage(file);
//        AddProductImageReq request = new AddProductImageReq();
//        request.setProductId(productId);
//        request.setImageId(uploadFile.getId());
//        productService.addImageId(request);
//        return ResponseEntity.ok(uploadFile);
//    }
//
//    @Operation(summary = "admin add category image") //done
//    @PostMapping("api/v1/category/upload-image")
//    public ResponseEntity<UploadFile> uploadCategoryImage(@RequestParam("file") final MultipartFile file, @RequestParam("categoryId")Integer categoryId) {
//        if (file == null) {
//            throw new LovelyException("cant not upload empty image", HttpStatus.BAD_REQUEST);
//        }
//        if (file.getSize() > 1024 * 1024 * 20) {
//            throw new LovelyException("File size is too large, please choose file smaller than 20MB");
//        }
//        UploadFile uploadFile = uploadFileService.uploadImage(file);
//        AddCategoryImageReq request = new AddCategoryImageReq();
//        request.setCategoryId(categoryId);
//        request.setImageId(uploadFile.getId());
//        categoryService.addImageId(request);
//        return ResponseEntity.ok(uploadFile);
//    }

    @PostMapping("api/v1/media/upload-image")
    public ResponseEntity<BaseResponse<UploadFile>> uploadImage(@RequestParam("file") final MultipartFile file) {
        if (file == null) {
            throw new LovelyException("cant not upload empty image", HttpStatus.BAD_REQUEST);
        }
        if (file.getSize() > 1024 * 1024 * 20) {
            throw new LovelyException("File size is too large, please choose file smaller than 20MB");
        }
        UploadFile uploadFile = uploadFileService.uploadImage(file);
        return ResponseEntity.ok(new BaseResponse<>(uploadFile));
    }

    @PostMapping("api/v1/media/upload-image-list")
    public ResponseEntity<BaseResponse<List<UploadFile>>> uploadManyImage(@RequestParam("file") final List<MultipartFile> listFile) {
        if (listFile == null || listFile.isEmpty()) {
            throw new LovelyException("No files provided", HttpStatus.BAD_REQUEST);
        }
        for(MultipartFile file : listFile) {
            if (file == null) {
                throw new LovelyException("cant not upload empty image", HttpStatus.BAD_REQUEST);
            }
            if (file.getSize() > 1024 * 1024 * 20) {
                throw new LovelyException("File size is too large, please choose file smaller than 20MB");
            }
        }
        List<UploadFile> uploadFile = uploadFileService.uploadManyImage(listFile);
        return ResponseEntity.ok(new BaseResponse<>(uploadFile));
    }

    @GetMapping("image/{fileName:.+}")//done
    public ResponseEntity<InputStreamResource> getImage(@PathVariable final String fileName) throws Exception {
        return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG)
                .body(new InputStreamResource(uploadFileService.getInputStream("image/" + fileName)));
    }

    @PostMapping("v1/file/upload-image-for-froala-editor")
    public ResponseEntity<?> uploadImage2(@RequestParam("file") final MultipartFile file) {
        try {
            if (file == null) {
                throw new LovelyException("cant not upload empty image", HttpStatus.BAD_REQUEST);
            }
            if (file.getSize() > 1024 * 1024 * 20) {
                throw new Exception("File size is too large, please choose file smaller than 20MB");
            }
            UploadFile uploadFile = uploadFileService.uploadImage(file);
            Map<String, String> map = new HashMap<>();
            map.put("link", this.BACKEND_URL + "/image" + uploadFile.getOriginalFileName());
            return ResponseEntity.ok(map);
        } catch (Exception ex) {
            return ResponseEntity.badRequest().body(new BaseResponse<>(ex.getMessage()));
        }
    }
}
