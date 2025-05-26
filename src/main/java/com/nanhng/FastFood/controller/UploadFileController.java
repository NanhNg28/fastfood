package com.nanhng.FastFood.controller;

import com.nanhng.FastFood.dto.request.category.AddCategoryImageReq;
import com.nanhng.FastFood.dto.request.product.AddProductImageReq;
import com.nanhng.FastFood.entity.upload_file.UploadFile;
import com.nanhng.FastFood.exception.LovelyException;
import com.nanhng.FastFood.service.category.CategoryService;
import com.nanhng.FastFood.service.product.ProductService;
import com.nanhng.FastFood.service.upload_file.UploadFileService;
import io.swagger.v3.oas.annotations.Operation;
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
    private final ProductService productService;
    private final CategoryService categoryService;

    @Operation(summary = "add product image")//done
    @PostMapping("api/v1/food/upload-image")
    public ResponseEntity<UploadFile> uploadProductImage(@RequestParam("file") final MultipartFile file, @RequestParam("productId")Integer productId) {
        if (file == null) {
            throw new LovelyException("cant not upload empty image", HttpStatus.BAD_REQUEST);
        }
        if (file.getSize() > 1024 * 1024 * 20) {
            throw new LovelyException("File size is too large, please choose file smaller than 20MB");
        }
        UploadFile uploadFile = uploadFileService.uploadImage(file);
        AddProductImageReq request = new AddProductImageReq();
        request.setProductId(productId);
        request.setImageId(uploadFile.getId());
        productService.addImageId(request);
        return ResponseEntity.ok(uploadFile);
    }

    @Operation(summary = "add category image") //done
    @PostMapping("api/v1/category/upload-image")
    public ResponseEntity<UploadFile> uploadCategoryImage(@RequestParam("file") final MultipartFile file, @RequestParam("categoryId")Integer categoryId) {
        if (file == null) {
            throw new LovelyException("cant not upload empty image", HttpStatus.BAD_REQUEST);
        }
        if (file.getSize() > 1024 * 1024 * 20) {
            throw new LovelyException("File size is too large, please choose file smaller than 20MB");
        }
        UploadFile uploadFile = uploadFileService.uploadImage(file);
        AddCategoryImageReq request = new AddCategoryImageReq();
        request.setCategoryId(categoryId);
        request.setImageId(uploadFile.getId());
        categoryService.addImageId(request);
        return ResponseEntity.ok(uploadFile);
    }

    @GetMapping("image/{fileName:.+}")//done
    public ResponseEntity<InputStreamResource> getImage(@PathVariable final String fileName) throws Exception {
        return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG)
                .body(new InputStreamResource(uploadFileService.getInputStream("image/" + fileName)));
    }
}
