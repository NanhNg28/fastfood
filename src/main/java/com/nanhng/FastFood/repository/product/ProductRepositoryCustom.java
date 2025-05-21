package com.nanhng.FastFood.repository.product;


import com.nanhng.FastFood.dto.constant.ActiveStatus;
import com.nanhng.FastFood.dto.response.product.ProductRes;

import java.util.List;

public interface ProductRepositoryCustom {
    List<ProductRes> getAllProduct(int page,String keyword, ActiveStatus status);
    List<Integer>getExistIds (List<Integer> ids);
    void deleteByIds(List<Integer> ids);
    List<ProductRes> getAllProductByCategory(int categoryId, int page);
    long totalRecord (String keyword, ActiveStatus status);
}
