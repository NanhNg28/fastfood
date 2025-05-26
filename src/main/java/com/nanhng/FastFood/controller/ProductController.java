package com.nanhng.FastFood.controller;

import com.nanhng.FastFood.dto.constant.ActiveStatus;
import com.nanhng.FastFood.dto.request.ids.IdsRequest;
import com.nanhng.FastFood.dto.request.product.AddProductImageReq;
import com.nanhng.FastFood.dto.request.product.AddProductReq;
import com.nanhng.FastFood.dto.request.product.UpdateProductReq;
import com.nanhng.FastFood.dto.response.BaseResponse;
import com.nanhng.FastFood.dto.response.product.AddProductImageRes;
import com.nanhng.FastFood.dto.response.product.ProductDetailRes;
import com.nanhng.FastFood.dto.response.product.ProductRes;
import com.nanhng.FastFood.entity.product.Product;
import com.nanhng.FastFood.service.product.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/")
public class ProductController {
    private final ProductService productService;

    @Operation(description = "add new food item")//done
    @PostMapping(path = "/v1/food/add")
    public ResponseEntity<Product> addFood(@Valid @RequestBody AddProductReq request) {
        return ResponseEntity.ok(productService.addProduct(request));
    }

    @Operation(description = "update exist food item")//done
    @PostMapping(path = "v1/food/update")
    public ResponseEntity<ProductDetailRes> updateFood(@Valid @RequestBody UpdateProductReq request) {
        return ResponseEntity.ok(productService.updateProduct(request));
    }

    @Operation(description = "get detail food")//done
    @GetMapping(path = "v1/food/detail/{id}")
    public ResponseEntity<ProductDetailRes> getFoodDetail(@PathVariable int id) {
        return ResponseEntity.ok(productService.getDetailProduct(id));
    }

    @Operation(description = "get list food")//done
    @GetMapping(path = "v1/food/list")
    public ResponseEntity<BaseResponse<List<ProductRes>>> getListFood(@RequestParam int page,
                                                        @RequestParam(required = false) String keyword,
                                                        @RequestParam(required = false)ActiveStatus status) {
        return ResponseEntity.ok(productService.getListProduct(page,keyword,status));
    }

    @Operation(description = "delete food")//done
    @PostMapping(path = "v1/food/delete")
    public ResponseEntity<BaseResponse<List<Integer>>> deleteFood(@Valid @RequestBody IdsRequest request) {
        return ResponseEntity.ok(new BaseResponse<>(productService.deleteProductByIds(request),"delete successfully"));
    }

    @Operation(description = "filter by category")//done
    @GetMapping(path = "v1/category/food")
    public ResponseEntity<BaseResponse<List<ProductRes>>> searchByCategory(@RequestParam(name =  "categoryId") int categoryId, @RequestParam int page) {
        return ResponseEntity.ok(new BaseResponse<>(productService.getListProductByCategory(categoryId,page),"Search successfully"));
    }

    @Operation(description = "add product image path")
    @PostMapping(path = "v1/food/image/add")
    public ResponseEntity<BaseResponse<AddProductImageRes>> addProductImage(@Valid @RequestBody AddProductImageReq request){
        return ResponseEntity.ok(new BaseResponse<>(productService.addImagePath(request),"add image successfully"));
    }
}
