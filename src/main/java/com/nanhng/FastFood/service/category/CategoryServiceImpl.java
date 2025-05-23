package com.nanhng.FastFood.service.category;

import com.nanhng.FastFood.dto.constant.ActiveStatus;
import com.nanhng.FastFood.dto.constant.RoleType;
import com.nanhng.FastFood.dto.request.category.AddCategoryReq;
import com.nanhng.FastFood.dto.request.category.UpdateCategoryReq;
import com.nanhng.FastFood.dto.request.ids.IdsRequest;
import com.nanhng.FastFood.dto.response.BaseResponse;
import com.nanhng.FastFood.entity.category.Category;
import com.nanhng.FastFood.entity.user.User;
import com.nanhng.FastFood.exception.LovelyException;
import com.nanhng.FastFood.repository.category.CategoryRepository;
import com.nanhng.FastFood.service.BaseService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class CategoryServiceImpl extends BaseService implements CategoryService {

    private final CategoryRepository categoryRepository;

    @Override
    public Category addCategory(AddCategoryReq request) {
        User user = getUser(RoleType.ADMIN);

        if(categoryRepository.existsByName(request.getName())) {
            throw new LovelyException("category already exists");
        }
        Category category = Category.builder()
                .name(request.getName())
                .description(request.getDescription())
                .deleted(false)
                .status(ActiveStatus.ACTIVE)
                .build();
        return categoryRepository.save(category);
    }

    @Override
    public Category updateCategory(UpdateCategoryReq request) {
        User user = getUser(RoleType.ADMIN);

        Category category = categoryRepository.findById(request.getId()).orElseThrow(() -> new LovelyException("category not found"));
        if(category.isDeleted()){
            throw new LovelyException("category not found");
        }
        if(!request.getName().isBlank()){
            category.setName(request.getName());
        }
            category.setDescription(request.getDescription());
        return categoryRepository.save(category);
    }

    @Override
    public Category getDetailCategory(int id) {
        Category category = categoryRepository.findById(id).orElseThrow(() -> new LovelyException("category not found"));
        if(category.isDeleted()){
            throw new LovelyException("category not found");
        }
        return category;
    }

    @Override
    public List<Integer> deleteCategory(IdsRequest request) {
        User user = getUser(RoleType.ADMIN);

        List<Integer> ids = request.getIds();
        List<Integer> existIds = categoryRepository.getAllIdToCheckExist(ids);
        List<Integer> notExistId = ids.stream().filter(id -> !existIds.contains(id)).toList();
        if(!notExistId.isEmpty()) {
            throw new LovelyException("category already exists");
        }
        categoryRepository.deleteCategory(ids);
        return existIds;
    }

    @Override
    public BaseResponse<List<Category>> getAllCategory(int page, String keyword, ActiveStatus status) {
        long count = categoryRepository.countRecord(keyword, status);
        List<Category> list = categoryRepository.findAll(page,keyword,status);
        return new BaseResponse<>(list,count,page);
    }
}
