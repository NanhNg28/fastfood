package com.nanhng.FastFood.service.user;

import com.nanhng.FastFood.dto.constant.ActiveStatus;
import com.nanhng.FastFood.dto.constant.RoleType;
import com.nanhng.FastFood.dto.request.ids.IdsRequest;
import com.nanhng.FastFood.dto.request.user.UpdateProfileUserReq;
import com.nanhng.FastFood.dto.request.user.UserChangePasswordReq;
import com.nanhng.FastFood.dto.request.user.UserLoginReq;
import com.nanhng.FastFood.dto.request.user.UserRegisterReq;
import com.nanhng.FastFood.dto.response.BaseResponse;
import com.nanhng.FastFood.dto.response.user.UserDetailRes;
import com.nanhng.FastFood.dto.response.user.UserListRes;
import com.nanhng.FastFood.entity.address.Address;
import com.nanhng.FastFood.entity.user.User;
import com.nanhng.FastFood.exception.LovelyException;
import com.nanhng.FastFood.service.repository.address.AddressRepository;
import com.nanhng.FastFood.service.repository.user.UserRepository;
import com.nanhng.FastFood.security.JwtToKenProvider;
import com.nanhng.FastFood.service.BaseService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl extends BaseService implements UserService {


    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final AddressRepository addressRepository;
    private final JwtToKenProvider jwtToKenProvider;

    @Override
    public User addUser(UserRegisterReq request) {

        if(userRepository.existsUserByUsername(request.getUsername())){
            throw new LovelyException("username already exist", HttpStatus.BAD_REQUEST);
        }
        if(userRepository.existsUserByPhone(request.getPhone())){
            throw new LovelyException("phone number already in use", HttpStatus.BAD_REQUEST);
        }
        User user = User.builder()
                .username(request.getUsername())
                .password(passwordEncoder.encode(request.getPassword()))
                .phone(request.getPhone())
                .email(request.getEmail())
                .status(ActiveStatus.ACTIVE)
                .role(RoleType.CUSTOMER)
                .deleted(false)
                .build();
        Address address = Address.builder()
                .city(request.getCity())
                .street(request.getStreet())
                .status(ActiveStatus.ACTIVE)
                .build();
        addressRepository.save(address);
        return userRepository.save(user);
    }

    @Override
    public UserDetailRes loginUser(UserLoginReq request) {

        User user = userRepository.loginByUsername(request.getUsername());
        if(user == null || !passwordEncoder.matches(request.getPassword(), user.getPassword())){
            throw new LovelyException("username or password incorrect", HttpStatus.UNAUTHORIZED);
        }
        if(user.getStatus() != ActiveStatus.ACTIVE){
            throw new LovelyException("account not active", HttpStatus.UNAUTHORIZED);
        }
        return UserDetailRes.builder()
                .username(user.getUsername())
                .phone(user.getPhone())
                .email(user.getEmail())
                .status(user.getStatus())
                .role(user.getRole())
                .authToken(jwtToKenProvider.generateToken(user.getId()))
                .build();
    }

    @Override
    public UserDetailRes registerUser(UserRegisterReq request) {
        if(userRepository.existsUserByUsername(request.getUsername())){
            throw new LovelyException("username already exist", HttpStatus.BAD_REQUEST);
        }
        if(userRepository.existsUserByPhone(request.getPhone())){
            throw new LovelyException("phone number already in use", HttpStatus.BAD_REQUEST);
        }
        Address address = Address.builder()
                .city(request.getCity())
                .street(request.getStreet())
                .status(ActiveStatus.ACTIVE)
                .build();
        addressRepository.save(address);
        User user = User.builder()
                .username(request.getUsername())
                .password(passwordEncoder.encode(request.getPassword()))
                .phone(request.getPhone())
                .email(request.getEmail())
                .status(ActiveStatus.ACTIVE)
                .role(RoleType.CUSTOMER)
                .deleted(false)
                .addressId(address.getId())
                .build();
        userRepository.save(user);

        return UserDetailRes.builder()
                .username(user.getUsername())
                .phone(user.getPhone())
                .email(user.getEmail())
                .status(ActiveStatus.ACTIVE)
                .role(user.getRole())
                .city(address.getCity())
                .street(address.getStreet())
                .addressId(address.getId())
                .authToken(jwtToKenProvider.generateToken(user.getId()))
                .build();
    }

    @Override
    public User updateProfileUser(UpdateProfileUserReq request) {
        User user = getUser();
        if(request.getAddressId() != null) {
            user.setAddressId(request.getAddressId());
        }
        return userRepository.save(user);
    }

    @Override
    public List<Integer> deleteUsers(IdsRequest request) {
        User user = getUser(RoleType.ADMIN);

        List<Integer> ids = request.getIds();
        List<Integer> existIds = userRepository.findAllById(ids).stream().map(User::getId).toList();
        Integer notExistId = ids.stream().filter(id -> !existIds.contains(id)).findFirst().orElse(null);
        if(notExistId != null) {
            throw new LovelyException("cant found cart item",HttpStatus.BAD_REQUEST);
        }
        userRepository.deleteAllByIdInBatch(request.getIds());
        return existIds;
    }

    @Override
    public User changePassword(UserChangePasswordReq request) {
        User user = getUser();
        if(request.getUserId()!=user.getId()){
            throw new LovelyException("Can not change password of other user", HttpStatus.UNAUTHORIZED);
        }
        if(!passwordEncoder.matches(request.getOldPassword(), user.getPassword())){
            throw new LovelyException("Old password incorrect", HttpStatus.BAD_REQUEST);
        }
        user.setPassword(passwordEncoder.encode(request.getNewPassword()));
        return userRepository.save(user);
    }

    @Override
    public BaseResponse<List<UserListRes>> getListUser(int page, String keyword, ActiveStatus status) {
        long record = userRepository.totalRecord(keyword,status);
        List<UserListRes> list =  userRepository.getAllProduct(page,keyword,status);
        return new BaseResponse<>(list,record,page);
    }

    @Override
    public User getMyProfile() {
        User user = getUser();

        return user;
    }

    @Override
    public User getUserDetail(int id) {
        User user = getUser(RoleType.ADMIN);

        User userFound = userRepository.findById(id).orElse(null);
        if(userFound == null){
            throw new LovelyException("user not found", HttpStatus.NOT_FOUND);
        }
        return userFound;
    }

    private UserDetailRes getUserDetailRes(User user){
        UserDetailRes userDetailRes = UserDetailRes.builder()
                .id(user.getId())
                .username(user.getUsername())
                .phone(user.getPhone())
                .email(user.getEmail())
                .status(user.getStatus())
                .role(user.getRole())
                .build();
        if(user.getAddressId() != null){
            Address address = addressRepository.findById(user.getAddressId()).orElse(null);
            if(address != null) {
                userDetailRes.setCity(address.getCity());
                userDetailRes.setStreet(address.getStreet());
            }
        }
        return userDetailRes;
    }
}
