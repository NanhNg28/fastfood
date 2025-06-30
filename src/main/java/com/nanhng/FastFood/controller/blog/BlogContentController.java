package com.nanhng.FastFood.controller.blog;

import com.nanhng.FastFood.dto.request.blog.blog_content.AddBlogContentReq;
import com.nanhng.FastFood.dto.request.blog.blog_content.UpdateBlogContentReq;
import com.nanhng.FastFood.dto.request.ids.IdsRequest;
import com.nanhng.FastFood.dto.response.BaseResponse;
import com.nanhng.FastFood.entity.blog.Blog;
import com.nanhng.FastFood.entity.blog.BlogContent;
import com.nanhng.FastFood.service.blog.blog_service.BlogContentService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/")
public class BlogContentController {
    private final BlogContentService blogContentService;

    @Operation(summary = "add blog content")
    @PostMapping("v1/blog-cotent/add")
    public ResponseEntity<BaseResponse<BlogContent>> addBlogContent(@RequestBody @Valid AddBlogContentReq request) {
        return ResponseEntity.ok(new BaseResponse<>(blogContentService.add(request),"success"));
    }

    @Operation(summary = "update blog content")
    @PostMapping("v1/blog-cotent/update")
    public ResponseEntity<BaseResponse<BlogContent>> updateBlogContent(@RequestBody @Valid UpdateBlogContentReq request) {
        return ResponseEntity.ok(new BaseResponse<>(blogContentService.update(request),"success"));
    }

    @Operation(summary = "delete blog content")
    @PostMapping("v1/blog-content/delete")
    public ResponseEntity<BaseResponse<List<Integer>>> deleteBlogContent(@RequestBody @Valid IdsRequest request){
        return ResponseEntity.ok(new BaseResponse<>(blogContentService.delete(request),"success"));
    }
}
