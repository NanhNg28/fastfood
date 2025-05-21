package com.nanhng.FastFood.repository.category;


import com.nanhng.FastFood.dto.constant.ActiveStatus;
import com.nanhng.FastFood.entity.category.Category;

import java.util.List;

public interface CategoryRepositoryCustom {
    void deleteById(List<Integer> ids);
    List<Category> findAll(int page,String keyword, ActiveStatus status);
    List<Integer> findAllByIds(List<Integer> ids);
    long countRecord (String keyword, ActiveStatus status);

}
