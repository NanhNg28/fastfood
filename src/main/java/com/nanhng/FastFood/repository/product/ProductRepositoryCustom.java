package com.nanhng.FastFood.repository.product;


import com.nanhng.FastFood.dto.response.product.ProductListRes;

import java.util.List;

public interface ProductRepositoryCustom {
    List<ProductListRes> getAllProduct(int page, String keyword);
    List<Integer>getExistIds (List<Integer> ids);
    void deleteByIds(List<Integer> ids);
    long countAllProductByCategory(int categoryId);
    List<ProductListRes> getAllProductByCategory(int categoryId, int page);
    long totalRecord (String keyword);
    Boolean existByName(String name);
    Boolean existById(Integer id);
}
