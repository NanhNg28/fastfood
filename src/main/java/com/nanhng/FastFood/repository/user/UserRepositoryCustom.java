package com.nanhng.FastFood.repository.user;

import com.nanhng.FastFood.dto.request.user.UserRegisterReq;
import com.nanhng.FastFood.dto.response.user.UserListRes;
import com.nanhng.FastFood.entity.user.User;

import java.util.List;

public interface UserRepositoryCustom {
    User saveUser(UserRegisterReq request);
    User loginByUsername(String username);
    List<UserListRes> getAllProduct(int page, String keyword);
    long totalRecord (String keyword);
}
