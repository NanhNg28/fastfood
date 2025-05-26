package com.nanhng.FastFood.service.repository.otp;

import com.nanhng.FastFood.entity.otp.Otp;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OtpRepository extends JpaRepository<Otp, Integer> {
}
