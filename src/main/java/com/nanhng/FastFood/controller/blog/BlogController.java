package com.nanhng.FastFood.controller.blog;

import com.nanhng.FastFood.dto.request.blog.blog.AddBlogReq;
import com.nanhng.FastFood.dto.request.blog.blog.UpdateBlogReq;
import com.nanhng.FastFood.dto.request.ids.IdsRequest;
import com.nanhng.FastFood.dto.response.BaseResponse;
import com.nanhng.FastFood.dto.response.blog.ListBlogRes;
import com.nanhng.FastFood.entity.blog.Blog;
import com.nanhng.FastFood.service.blog.blog.BlogService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/")
public class BlogController {

    private final BlogService blogService;

    //done
    @Operation(summary = "admin add new blog", description = "calling the upload many images api, " + //done
            "return list of images, map them to these blogs")
    @PostMapping("v1/blog/add")
    public ResponseEntity<BaseResponse<Blog>> addBlog(@Valid @RequestBody AddBlogReq request) {
        return ResponseEntity.ok(new BaseResponse<>(blogService.addBlog(request), "add blog successfully"));
    }

    @Operation(summary = "get blog detail") //done
    @GetMapping("v1/blog/detail/{id}")
    public ResponseEntity<BaseResponse<Blog>> getBlogDetail(@PathVariable("id") Integer id) {
        return ResponseEntity.ok(new BaseResponse<>(blogService.getBlogDetail(id), "add blog successfully"));
    }

    @Operation(summary = "get list blogs")//done
    @GetMapping("v1/blog/list")
    public ResponseEntity<BaseResponse<List<ListBlogRes>>> getBlogList(@RequestParam Integer page,
                                                                       @RequestParam(required = false) String keyword) {
        return ResponseEntity.ok(blogService.getBlogList(page,keyword));
    }

    @Operation(summary = "delete many blogs")
    @PostMapping("v1/blog/delete")
    public ResponseEntity<BaseResponse<List<Integer>>> deleteBlog(@RequestBody IdsRequest request) {
        return ResponseEntity.ok(new BaseResponse<>(blogService.deleteBlog(request), "delete blog successfully"));
    }

    @Operation(summary = "update blog")
    @PostMapping("v1/blog/update")
    public ResponseEntity<BaseResponse<Blog>> updateBlog(@RequestBody UpdateBlogReq request){
        return ResponseEntity.ok(new BaseResponse<>(blogService.updateBlog(request), "delete blog successfully"));
    }
}
