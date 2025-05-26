package com.nanhng.FastFood.service.repository.user;

import com.nanhng.FastFood.dto.constant.ActiveStatus;
import com.nanhng.FastFood.dto.request.user.UserRegisterReq;
import com.nanhng.FastFood.dto.response.user.UserListRes;
import com.nanhng.FastFood.entity.user.User;

import java.util.List;

public interface UserRepositoryCustom {
    User saveUser(UserRegisterReq request);
    User loginByUsername(String username);
    List<UserListRes> getAllProduct(int page, String keyword, ActiveStatus status);
    long totalRecord (String keyword, ActiveStatus status);
}
