package com.nanhng.FastFood.repository.category;


import com.nanhng.FastFood.dto.constant.ActiveStatus;
import com.nanhng.FastFood.dto.response.category.CategoryListRes;
import com.nanhng.FastFood.entity.category.Category;

import java.util.List;

public interface CategoryRepositoryCustom {
    void deleteByIds(List<Integer> ids);
    List<CategoryListRes> findAll(int page, String keyword, ActiveStatus status);
    List<Integer> getExistIds(List<Integer> ids);
    long countRecord (String keyword, ActiveStatus status);
    Boolean existById(Integer id);
    Category findByIdToUpdate(Integer id);
    boolean existByName(String categoryName);
}
