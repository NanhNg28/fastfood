package com.nanhng.FastFood.controller;

import com.nanhng.FastFood.dto.constant.ActiveStatus;
import com.nanhng.FastFood.dto.request.category.AddCategoryReq;
import com.nanhng.FastFood.dto.request.category.UpdateCategoryReq;
import com.nanhng.FastFood.dto.request.ids.IdsRequest;
import com.nanhng.FastFood.dto.response.BaseResponse;
import com.nanhng.FastFood.dto.response.category.CategoryDetailRes;
import com.nanhng.FastFood.dto.response.category.CategoryListRes;
import com.nanhng.FastFood.entity.category.Category;
import com.nanhng.FastFood.service.category.CategoryService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/")
public class CategoryController {
    private final CategoryService categoryService;

    @Operation(description = "admin add new category") //done
    @PostMapping("v1/category/add")
    public ResponseEntity<BaseResponse<Category>> addCategory(@Valid @RequestBody AddCategoryReq request) {
        return ResponseEntity.ok(new BaseResponse<>(categoryService.addCategory(request), "add category successfully"));
    }

    @Operation(description = "admin update category")//done
    @PostMapping("v1/category/update")
    public ResponseEntity<BaseResponse<Category>> updateCategory(@RequestBody @Valid UpdateCategoryReq request) {
        return ResponseEntity.ok(new BaseResponse<>(categoryService.updateCategory(request), "update category successfully"));
    }

    @Operation(description = "get detail category")//done
    @GetMapping("v1/category/detail/{id}")
    public ResponseEntity<BaseResponse<CategoryDetailRes>> getCategory(@PathVariable int id) {
        return ResponseEntity.ok(new BaseResponse<>(categoryService.getDetailCategory(id), "get category detail successfully"));
    }

    @Operation(description = "get all category")//done
    @GetMapping("v1/category/list")
    public ResponseEntity<BaseResponse<List<CategoryListRes>>> getAllCategory(@RequestParam int page,
                                                                              @RequestParam(required = false) String searchKeyword,
                                                                              @RequestParam(required = false) ActiveStatus status) {
        return ResponseEntity.ok(categoryService.getAllCategory(page, searchKeyword, status));
    }

    @Operation(description = "admin delete category")//done
    @PostMapping("v1/category/delete")
    public ResponseEntity<BaseResponse<List<Integer>>> deleteCategory(@Valid @RequestBody IdsRequest ids) {
        return ResponseEntity.ok(new BaseResponse<>(categoryService.deleteCategory(ids), "delete category successfully"));
    }
}
