package com.nanhng.FastFood.service.category;

import com.nanhng.FastFood.dto.constant.ActiveStatus;
import com.nanhng.FastFood.dto.request.category.AddCategoryReq;
import com.nanhng.FastFood.dto.request.category.UpdateCategoryReq;
import com.nanhng.FastFood.dto.request.ids.IdsRequest;
import com.nanhng.FastFood.dto.response.BaseResponse;
import com.nanhng.FastFood.entity.category.Category;

import java.util.List;

public interface CategoryService {
    Category addCategory(AddCategoryReq request);
    Category updateCategory(UpdateCategoryReq request);
    Category getDetailCategory(int id);
    List<Integer> deleteCategory(IdsRequest ids);
    BaseResponse<List<Category>> getAllCategory(int page, String searchKeyword, ActiveStatus status);
}
