package com.nanhng.FastFood.service.product;

import com.nanhng.FastFood.dto.constant.ActiveStatus;
import com.nanhng.FastFood.dto.constant.RoleType;
import com.nanhng.FastFood.dto.request.ids.IdsRequest;
import com.nanhng.FastFood.dto.request.product.AddProductImageReq;
import com.nanhng.FastFood.dto.request.product.AddProductReq;
import com.nanhng.FastFood.dto.request.product.UpdateProductReq;
import com.nanhng.FastFood.dto.response.BaseResponse;
import com.nanhng.FastFood.dto.response.product.AddProductImageRes;
import com.nanhng.FastFood.dto.response.product.ProductDetailRes;
import com.nanhng.FastFood.dto.response.product.ProductRes;
import com.nanhng.FastFood.entity.product.Product;
import com.nanhng.FastFood.entity.upload_file.UploadFile;
import com.nanhng.FastFood.entity.user.User;
import com.nanhng.FastFood.exception.LovelyException;
import com.nanhng.FastFood.repository.category.CategoryRepository;
import com.nanhng.FastFood.repository.product.ProductRepository;
import com.nanhng.FastFood.repository.upload_file.UploadFileRepository;
import com.nanhng.FastFood.service.BaseService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProductServiceImpl extends BaseService implements ProductService {
    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    private final UploadFileRepository uploadFileRepository;

    @Override
    public Product addProduct(AddProductReq request) {
        User user = getUser(RoleType.ADMIN);

        if(!categoryRepository.existsById(request.getCategoryId())) {
            throw new LovelyException("Category not found", HttpStatus.BAD_REQUEST);
        }
        if (productRepository.existsByName(request.getName())) {
            throw new LovelyException("product already exist", HttpStatus.BAD_REQUEST);
        }
        Product product = Product.builder()
                .name(request.getName())
                .price(request.getPrice())
                .categoryId(request.getCategoryId())
                .quantity(request.getQuantity())
                .shortDescription(request.getShortDescription())
                .longDescription(request.getLongDescription())
                .status(ActiveStatus.ACTIVE)
                .deleted(false)
                .build();
        return productRepository.save(product);
    }

    @Override
    public ProductDetailRes updateProduct(UpdateProductReq request) {
        User user = getUser(RoleType.ADMIN);

        log.info(request.getCategoryId().toString());
        if(!categoryRepository.existsById(request.getCategoryId())) {
            throw new LovelyException("Category not found", HttpStatus.BAD_REQUEST);
        }
        if(!productRepository.existsById(request.getId())) {
            throw new LovelyException("product not found", HttpStatus.BAD_REQUEST);
        }
        Product product = productRepository.findById(request.getId()).orElse(null);
        if(product == null) {
            throw new LovelyException("product not found", HttpStatus.BAD_REQUEST);
        }
        if(request.getName() != null &&!request.getName().isBlank()){
            product.setName(request.getName());
        }
        if(request.getPrice() != null){
            product.setPrice(request.getPrice());
        }
        if(request.getCategoryId() != null){
            product.setCategoryId(request.getCategoryId());
        }
        if(request.getQuantity() != null){
            product.setQuantity(request.getQuantity());
        }
        if(request.getShortDescription() != null &&!request.getShortDescription().isBlank()){
            product.setShortDescription(request.getShortDescription());
        }
        if(request.getLongDescription() != null &&!request.getLongDescription().isBlank()){
            product.setLongDescription(request.getLongDescription());
        }
        return getProductDetailRes(productRepository.save(product));
    }

    @Override
    public ProductDetailRes getDetailProduct(int id) {
        if(!productRepository.existsById(id)) {
            throw new LovelyException("product not found", HttpStatus.BAD_REQUEST);
        }
        Product product = productRepository.findById(id).get();
        return getProductDetailRes(product);
    }

    @Override
    public BaseResponse<List<ProductRes>> getListProduct(int page, String keyword, ActiveStatus status) {
        long record = productRepository.totalRecord(keyword,status);
        List<ProductRes> list =  productRepository.getAllProduct(page,keyword,status);
        return new BaseResponse<>(list,record,page);
    }

    @Override
    public List<Integer> deleteProductByIds(IdsRequest request) {
        User user = getUser(RoleType.ADMIN);

        List<Integer> ids = request.getIds();
        List<Integer> existIds = productRepository.getExistIds(ids);
        Integer notExistId = productRepository.getExistIds(ids).stream().filter(id -> !existIds.contains(id)).findFirst().orElse(null);
        if(notExistId != null) {
            throw new LovelyException("product not exist", HttpStatus.BAD_REQUEST);
        }
        productRepository.deleteByIds(existIds);
        return existIds;
    }

    @Override
    public List<ProductRes> getListProductByCategory(int categoryId, int page) {
        if(!categoryRepository.existsById(categoryId)) {
            throw new LovelyException("category not found", HttpStatus.BAD_REQUEST);
        }
        return productRepository.getAllProductByCategory(categoryId,page);
    }

    @Override
    public AddProductImageRes addImagePath(AddProductImageReq request) {
        User user = getUser(RoleType.ADMIN);

        Product product = productRepository.findById(request.getProductId()).orElseThrow(()-> new LovelyException("product not found", HttpStatus.BAD_REQUEST));
        UploadFile uploadFile = uploadFileRepository.findById(request.getImageId()).orElseThrow(()-> new LovelyException("image not found", HttpStatus.BAD_REQUEST));
        product.setImagePath(uploadFile.getOriginFilePath());
        productRepository.save(product);
        return AddProductImageRes.builder()
                .name(product.getName())
                .productId(product.getId())
                .imageId(uploadFile.getId())
                .imagePath(uploadFile.getOriginFilePath())
                .build();
    }

    private ProductDetailRes getProductDetailRes(Product product) {
        if(!categoryRepository.existsById(product.getCategoryId())) {
            throw new LovelyException("Category not found", HttpStatus.BAD_REQUEST);
        }
        return ProductDetailRes.builder()
                .name(product.getName())
                .price(product.getPrice())
                .categoryName(categoryRepository.findById(product.getCategoryId()).get().getName())
                .quantity(product.getQuantity())
                .shortDescription(product.getShortDescription())
                .longDescription(product.getLongDescription())
                .build();
    }
}
