package com.nanhng.FastFood.repository.banner;

import com.nanhng.FastFood.entity.banner.Banner;

import java.util.List;

public interface BannerRepositoryCustom {
    Banner findByIdToUpdate(Integer bannerId);
    List<Integer> getExistIds(List<Integer> ids);
    void deleteByIds(List<Integer> ids);
    long countRecord();
    List<Banner> getList(int page);
}
