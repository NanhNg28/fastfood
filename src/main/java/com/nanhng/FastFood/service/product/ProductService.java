package com.nanhng.FastFood.service.product;

import com.nanhng.FastFood.dto.request.ids.IdsRequest;
import com.nanhng.FastFood.dto.request.product.AddProductImageReq;
import com.nanhng.FastFood.dto.request.product.AddProductReq;
import com.nanhng.FastFood.dto.request.product.UpdateProductReq;
import com.nanhng.FastFood.dto.response.BaseResponse;
import com.nanhng.FastFood.dto.response.product.AddProductImageRes;
import com.nanhng.FastFood.dto.response.product.ProductDetailRes;
import com.nanhng.FastFood.dto.response.product.ProductListRes;
import com.nanhng.FastFood.entity.product.Product;

import java.util.List;

public interface ProductService {
    Product addProduct(AddProductReq request);
    ProductDetailRes updateProduct(UpdateProductReq request);
    Product getDetailProduct(int id);
    BaseResponse<List<ProductListRes>> getListProduct(int page, String keyword);
    List<Integer> deleteProductByIds(IdsRequest request);
    BaseResponse<List<ProductListRes>> getListProductByCategory(int CategoryId, int page);
    AddProductImageRes addImageId(AddProductImageReq request);

}
