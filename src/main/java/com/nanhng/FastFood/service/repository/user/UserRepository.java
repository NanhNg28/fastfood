package com.nanhng.FastFood.service.repository.user;

import com.nanhng.FastFood.entity.user.User;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer>,UserRepositoryCustom {
    boolean existsUserByPhone(String phone);
    boolean existsUserByUsername(@NotNull String username);
}
