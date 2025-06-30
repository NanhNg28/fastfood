package com.nanhng.FastFood.service.category;

import com.nanhng.FastFood.dto.constant.RoleType;
import com.nanhng.FastFood.dto.request.category.AddCategoryImageReq;
import com.nanhng.FastFood.dto.request.category.AddCategoryReq;
import com.nanhng.FastFood.dto.request.category.UpdateCategoryReq;
import com.nanhng.FastFood.dto.request.ids.IdsRequest;
import com.nanhng.FastFood.dto.response.BaseResponse;
import com.nanhng.FastFood.dto.response.category.AddCategoryImageRes;
import com.nanhng.FastFood.dto.response.category.CategoryDetailRes;
import com.nanhng.FastFood.dto.response.category.CategoryListRes;
import com.nanhng.FastFood.entity.category.Category;
import com.nanhng.FastFood.entity.upload_file.UploadFile;
import com.nanhng.FastFood.entity.user.User;
import com.nanhng.FastFood.exception.LovelyException;
import com.nanhng.FastFood.repository.category.CategoryRepository;
import com.nanhng.FastFood.service.BaseService;
import com.nanhng.FastFood.repository.upload_file.UploadFileRepository;
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
    private final UploadFileRepository uploadFileRepository;

    @Override
    public Category addCategory(AddCategoryReq request) {
        User user = getUser(RoleType.ADMIN);

        if(categoryRepository.existByName(request.getName())) {
            throw new LovelyException("Danh mục đã tồn tại");
        }
        Category category = Category.builder()
                .name(request.getName())
                .description(request.getDescription())
                .imageId(request.getImageId())
                .deleted(false)
                .build();
        return categoryRepository.save(category);
    }

    @Override
    public Category updateCategory(UpdateCategoryReq request) {
        User user = getUser(RoleType.ADMIN);

        Category category = categoryRepository.findByIdToUpdate(request.getId());
        if(category.isDeleted()){
            throw new LovelyException("Không tìm thấy danh mục");
        }
        if(!request.getName().isBlank()){
            category.setName(request.getName());
        }
        if(request.getImageId()!=null){
            category.setImageId(request.getImageId());
        }
        category.setDescription(request.getDescription());
        return categoryRepository.save(category);
    }

    @Override
    public CategoryDetailRes getDetailCategory(int id) {
        Category category = categoryRepository.findById(id).orElseThrow(() -> new LovelyException("category not found"));
        UploadFile uploadFile = uploadFileRepository.findById(category.getImageId()).orElse(null);
        if(category.isDeleted()){
            throw new LovelyException(" Không tìm thấy danh mục ");
        }
        CategoryDetailRes categoryDetailRes = new CategoryDetailRes();
        categoryDetailRes.setId(category.getId());
        categoryDetailRes.setName(category.getName());
        categoryDetailRes.setDescription(category.getDescription());
        if(uploadFile != null){
            categoryDetailRes.setOriginUrl(uploadFile.getOriginFilePath());
            categoryDetailRes.setOriginName(uploadFile.getOriginalFileName());
        }
        return categoryDetailRes;
    }

    @Override
    public List<Integer> deleteCategory(IdsRequest request) {
        User user = getUser(RoleType.ADMIN);

        List<Integer> ids = request.getIds();
        List<Integer> existIds = categoryRepository.getExistIds(ids);
        List<Integer> notExistId = ids.stream().filter(id -> !existIds.contains(id)).toList();
        if(!notExistId.isEmpty()) {
            throw new LovelyException("Không tìm thấy id");
        }
        categoryRepository.deleteByIds(ids);
        return existIds;
    }

    @Override
    public BaseResponse<List<CategoryListRes>> getAllCategory(int page, String keyword) {
        long count = categoryRepository.countRecord(keyword);
        List<CategoryListRes> list = categoryRepository.findAll(page,keyword);
        return new BaseResponse<>(list,count,page);
    }

    @Override
    public AddCategoryImageRes addImageId(AddCategoryImageReq request) {
        User user = getUser(RoleType.ADMIN);

        Category category = categoryRepository.findById(request.getCategoryId()).orElseThrow(()-> new LovelyException("Không tìm thấy danh mục", HttpStatus.BAD_REQUEST));
        if(category.isDeleted()){
            throw new LovelyException("Không tìm thấy danh mục");
        }

        UploadFile uploadFile = uploadFileRepository.findById(request.getImageId()).orElseThrow(()-> new LovelyException("Không tìm thấy hình ảnh", HttpStatus.BAD_REQUEST));
        category.setImageId(uploadFile.getId());
        categoryRepository.save(category);
        return AddCategoryImageRes.builder()
                .name(category.getName())
                .categoryId(category.getId())
                .imageId(uploadFile.getId())
                .imagePath(uploadFile.getOriginFilePath())
                .build();
    }
}
