package com.nanhng.FastFood.controller;

import com.nanhng.FastFood.dto.request.banner.AddBannerReq;
import com.nanhng.FastFood.dto.request.banner.UpdateBannerReq;
import com.nanhng.FastFood.dto.request.ids.IdsRequest;
import com.nanhng.FastFood.dto.response.BaseResponse;
import com.nanhng.FastFood.entity.banner.Banner;
import com.nanhng.FastFood.service.banner.BannerService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/")
public class BannerController {

    private final BannerService bannerService;

    @Operation(description = "admin add new category") //done
    @PostMapping("v1/banner/add")
    public ResponseEntity<BaseResponse<Banner>> addBanner(@Valid @RequestBody AddBannerReq request) {
        return ResponseEntity.ok(new BaseResponse<>(bannerService.add(request), "add banner successfully"));
    }

    @Operation(description = "admin update category") //done
    @PostMapping("v1/banner/update")
    public ResponseEntity<BaseResponse<Banner>> updateBanner(@Valid @RequestBody UpdateBannerReq request) {
        return ResponseEntity.ok(new BaseResponse<>(bannerService.update(request), "add banner successfully"));
    }

    @Operation(description = "admin delete new category") //done
    @PostMapping("v1/banner/delete")
    public ResponseEntity<BaseResponse<List<Integer>>> deleteBanner(@Valid @RequestBody IdsRequest request) {
        return ResponseEntity.ok(new BaseResponse<>(bannerService.delete(request), "add banner successfully"));
    }

    @Operation(description = "get list banner")
    @GetMapping("v1/banner/list")
    public ResponseEntity<BaseResponse<List<Banner>>> getAllCategory() {
        return ResponseEntity.ok(bannerService.getList());
    }
}
