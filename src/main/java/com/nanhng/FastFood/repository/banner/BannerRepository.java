package com.nanhng.FastFood.repository.banner;

import com.nanhng.FastFood.entity.banner.Banner;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BannerRepository extends JpaRepository<Banner, Integer>, BannerRepositoryCustom {
}
