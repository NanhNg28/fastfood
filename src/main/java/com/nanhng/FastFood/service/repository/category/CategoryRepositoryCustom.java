package com.nanhng.FastFood.service.repository.category;


import com.nanhng.FastFood.dto.constant.ActiveStatus;
import com.nanhng.FastFood.entity.category.Category;

import java.util.List;

public interface CategoryRepositoryCustom {
    void deleteByIds(List<Integer> ids);
    List<Category> findAll(int page,String keyword, ActiveStatus status);
    List<Integer> getExistIds(List<Integer> ids);
    long countRecord (String keyword, ActiveStatus status);
    Boolean existById(Integer id);
}
