package com.nanhng.FastFood.service.repository.product;


import com.nanhng.FastFood.dto.constant.ActiveStatus;
import com.nanhng.FastFood.dto.response.product.ProductListRes;

import java.util.List;

public interface ProductRepositoryCustom {
    List<ProductListRes> getAllProduct(int page, String keyword, ActiveStatus status);
    List<Integer>getExistIds (List<Integer> ids);
    void deleteByIds(List<Integer> ids);
    long countAllProductByCategory(int categoryId);
    List<ProductListRes> getAllProductByCategory(int categoryId, int page);
    long totalRecord (String keyword, ActiveStatus status);
    Boolean existByName(String name);
    Boolean existById(Integer id);
}
