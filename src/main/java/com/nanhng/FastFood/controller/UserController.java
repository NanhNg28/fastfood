package com.nanhng.FastFood.controller;

import com.nanhng.FastFood.dto.constant.ActiveStatus;
import com.nanhng.FastFood.dto.request.ids.IdsRequest;
import com.nanhng.FastFood.dto.request.user.UpdateProfileUserReq;
import com.nanhng.FastFood.dto.request.user.UserChangePasswordReq;
import com.nanhng.FastFood.dto.request.user.UserRegisterReq;
import com.nanhng.FastFood.dto.response.BaseResponse;
import com.nanhng.FastFood.dto.response.user.UserDetailRes;
import com.nanhng.FastFood.dto.response.user.UserListRes;
import com.nanhng.FastFood.entity.user.User;
import com.nanhng.FastFood.service.user.UserService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/")
public class UserController {

    private final UserService userService;

    @Operation(summary = "sua thong tin ca nhan user")
    @PostMapping("v1/user/update")
    public ResponseEntity<User> updateProfileUser(@Valid @RequestBody UpdateProfileUserReq request) {
        return ResponseEntity.ok(userService.updateProfileUser(request));
    }

    @Operation(summary = "doi mat khau cua toi")
    @PostMapping("v1/user/change-my-password")
    public ResponseEntity<BaseResponse<User>> userChangePassword(@Valid @RequestBody UserChangePasswordReq request){
        return ResponseEntity.ok(new BaseResponse<>(userService.changePassword(request),"change user`s password successfully"));
    }

    @Operation(summary = "xoa 1 hoac nhieu user")
    @PostMapping("v1/user/delete")
    public ResponseEntity<BaseResponse<List<Integer>>> deleteUsers(@RequestBody IdsRequest ids) {
        return ResponseEntity.ok(new BaseResponse<>(userService.deleteUsers(ids),"delete users successfully"));
    }

    @Operation(description = "add new user (user, admin, employee)")
    @PostMapping("v1/user/add")
    public ResponseEntity<BaseResponse<User>> RegisterUser(@Valid @RequestBody UserRegisterReq request){
        User user = userService.addUser(request);
        return ResponseEntity.ok(new BaseResponse<>(user,"success in adding new user"));
    }

    @Operation(description = "get list user")
    @GetMapping(path = "v1/user/list")
    public ResponseEntity<BaseResponse<List<UserListRes>>> getListFood(@RequestParam int page,
                                                                       @RequestParam(required = false) String keyword,
                                                                       @RequestParam(required = false) ActiveStatus status) {
        return ResponseEntity.ok(userService.getListUser(page,keyword,status));
    }

    @Operation(description = "get my profile")
    @GetMapping(path = "v1/user/my-profile")
    public ResponseEntity<BaseResponse<UserDetailRes>> getMyProfile() {
        return ResponseEntity.ok(new BaseResponse<>(userService.getMyProfile(),"getting my profile successfully"));
    }

    @Operation(description = "get user profile")
    @GetMapping(path = "v1/user/{id}")
    public ResponseEntity<BaseResponse<UserDetailRes>> getUserProfile(@PathVariable("id") int userId) {
        return ResponseEntity.ok(new BaseResponse<>(userService.getUserDetail(userId),"getting my profile successfully"));
    }
}
