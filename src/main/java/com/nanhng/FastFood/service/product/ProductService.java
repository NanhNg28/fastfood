package com.nanhng.FastFood.service.product;

import com.nanhng.FastFood.dto.constant.ActiveStatus;
import com.nanhng.FastFood.dto.request.ids.IdsRequest;
import com.nanhng.FastFood.dto.request.product.AddProductImageReq;
import com.nanhng.FastFood.dto.request.product.AddProductReq;
import com.nanhng.FastFood.dto.request.product.UpdateProductReq;
import com.nanhng.FastFood.dto.response.BaseResponse;
import com.nanhng.FastFood.dto.response.product.AddProductImageRes;
import com.nanhng.FastFood.dto.response.product.ProductDetailRes;
import com.nanhng.FastFood.dto.response.product.ProductRes;
import com.nanhng.FastFood.entity.product.Product;

import java.util.List;

public interface ProductService {
    Product addProduct(AddProductReq request);
    ProductDetailRes updateProduct(UpdateProductReq request);
    ProductDetailRes getDetailProduct(int id);
    BaseResponse<List<ProductRes>> getListProduct(int page, String keyword, ActiveStatus status);
    List<Integer> deleteProductByIds(IdsRequest request);
    List<ProductRes> getListProductByCategory(int CategoryId, int page);
    AddProductImageRes addImagePath(AddProductImageReq request);

}
