package com.nanhng.FastFood.service.user;

import com.nanhng.FastFood.dto.request.ids.IdsRequest;
import com.nanhng.FastFood.dto.request.otp.SendOtpReq;
import com.nanhng.FastFood.dto.request.user.*;
import com.nanhng.FastFood.dto.response.BaseResponse;
import com.nanhng.FastFood.dto.response.otp.SendOtpRes;
import com.nanhng.FastFood.dto.response.user.UserDetailRes;
import com.nanhng.FastFood.dto.response.user.UserListRes;
import com.nanhng.FastFood.entity.user.User;

import java.util.List;

public interface UserService {
    User addUser(AddUserReq req);

    UserDetailRes loginUser(UserLoginReq request);

    UserDetailRes registerUser(UserRegisterReq request);

    User updateProfileUser(UpdateProfileUserReq request);

    List<Integer> deleteUsers(IdsRequest request);

    User changePassword(UserChangePasswordReq request);

    BaseResponse<List<UserListRes>> getListUser(int page, String keyword);

    User getMyProfile();

    User getUserDetail(int id);
}
