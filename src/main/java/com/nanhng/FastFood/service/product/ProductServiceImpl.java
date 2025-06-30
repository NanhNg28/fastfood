package com.nanhng.FastFood.service.product;


import com.nanhng.FastFood.dto.constant.RoleType;
import com.nanhng.FastFood.dto.request.ids.IdsRequest;
import com.nanhng.FastFood.dto.request.product.AddProductImageReq;
import com.nanhng.FastFood.dto.request.product.AddProductReq;
import com.nanhng.FastFood.dto.request.product.UpdateProductReq;
import com.nanhng.FastFood.dto.response.BaseResponse;
import com.nanhng.FastFood.dto.response.product.AddProductImageRes;
import com.nanhng.FastFood.dto.response.product.ProductDetailRes;
import com.nanhng.FastFood.dto.response.product.ProductListRes;
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

        if (!categoryRepository.existById(request.getCategoryId())) {
            throw new LovelyException("Category not found", HttpStatus.BAD_REQUEST);
        }
        if (productRepository.existByName(request.getName())) {
            throw new LovelyException("product already exist", HttpStatus.BAD_REQUEST);
        }
        UploadFile image = uploadFileRepository.findById(request.getImageId()).orElse(null);
        Product product = Product.builder()
                .name(request.getName())
                .price(request.getPrice())
                .categoryId(request.getCategoryId())
                .quantity(request.getQuantity())
                .shortDescription(request.getShortDescription())
                .longDescription(request.getLongDescription())
                .imageId(request.getImageId())
                .image(image)
                .discountPercentage(request.getDiscountPercentage())
                .discountExpiryDate(request.getDiscountExpiryDate())
                .build();
        return productRepository.save(product);
    }

    @Override
    public ProductDetailRes updateProduct(UpdateProductReq request) {
        User user = getUser(RoleType.ADMIN);

        if (!categoryRepository.existById(request.getCategoryId())) {
            throw new LovelyException("Category not found", HttpStatus.BAD_REQUEST);
        }
        if (!productRepository.existById(request.getId())) {
            throw new LovelyException("product not found", HttpStatus.BAD_REQUEST);
        }
        Product product = productRepository.findById(request.getId()).orElse(null);
        if (product == null) {
            throw new LovelyException("product not found", HttpStatus.BAD_REQUEST);
        }
        if (request.getName() != null && !request.getName().isBlank()) {
            product.setName(request.getName());
        }
        if (request.getPrice() != null) {
            product.setPrice(request.getPrice());
        }
        if (request.getCategoryId() != null) {
            product.setCategoryId(request.getCategoryId());
        }
        if (request.getQuantity() != null) {
            product.setQuantity(request.getQuantity());
        }
        if (request.getShortDescription() != null && !request.getShortDescription().isBlank()) {
            product.setShortDescription(request.getShortDescription());
        }
        if (request.getLongDescription() != null && !request.getLongDescription().isBlank()) {
            product.setLongDescription(request.getLongDescription());
        }
        if (request.getImageId() != null) {
            product.setImageId(request.getImageId());
        }
        if(request.getDiscountPercentage()!=null){
            product.setDiscountPercentage(request.getDiscountPercentage());
        }
        if(request.getDiscountExpiryDate()!=null){
            product.setDiscountExpiryDate(request.getDiscountExpiryDate());
        }
        return getProductDetailRes(productRepository.save(product));
    }

    @Override
    public Product getDetailProduct(int id) {
        if (!productRepository.existById(id)) {
            throw new LovelyException("Không tìm thấy sản phẩm", HttpStatus.NOT_FOUND);
        }
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new LovelyException("Không tìm thấy sản phẩm", HttpStatus.NOT_FOUND));
        if (product.isDeleted()) {
            throw new LovelyException("Không tìm thấy sản phẩm", HttpStatus.NOT_FOUND);
        }
        UploadFile uploadFile = uploadFileRepository.findById(product.getImageId()).orElse(null);
       product.setImage(uploadFile);
        return product;
    }

    @Override
    public BaseResponse<List<ProductListRes>> getListProduct(int page, String keyword) {
        long record = productRepository.totalRecord(keyword);
        List<ProductListRes> list = productRepository.getAllProduct(page, keyword);
        return new BaseResponse<>(list, record, page);
    }

    @Override
    public List<Integer> deleteProductByIds(IdsRequest request) {
        User user = getUser(RoleType.ADMIN);

        List<Integer> ids = request.getIds();
        List<Integer> existIds = productRepository.getExistIds(ids);
        Integer notExistId = productRepository.getExistIds(ids).stream().filter(id -> !existIds.contains(id)).findFirst().orElse(null);
        if (notExistId != null) {
            throw new LovelyException("product not exist", HttpStatus.BAD_REQUEST);
        }
        productRepository.deleteByIds(existIds);
        return existIds;
    }

    @Override
    public BaseResponse<List<ProductListRes>> getListProductByCategory(int categoryId, int page) {
        if (!categoryRepository.existsById(categoryId)) {
            throw new LovelyException("Không tìm thấy danh mục", HttpStatus.BAD_REQUEST);
        }
        long count = productRepository.countAllProductByCategory(categoryId);
        List<ProductListRes> list = productRepository.getAllProductByCategory(categoryId, page);
        return new BaseResponse<>(list, count, page);
    }

    @Override
    public AddProductImageRes addImageId(AddProductImageReq request) {
        User user = getUser(RoleType.ADMIN);

        Product product = productRepository.findById(request.getProductId()).orElseThrow(() -> new LovelyException("Không tìm thấy sản phẩm", HttpStatus.BAD_REQUEST));
        UploadFile uploadFile = uploadFileRepository.findById(request.getImageId()).orElseThrow(() -> new LovelyException("Không tìm thấy hình ảnh", HttpStatus.BAD_REQUEST));
        product.setImageId(uploadFile.getId());
        productRepository.save(product);
        return AddProductImageRes.builder()
                .name(product.getName())
                .productId(product.getId())
                .imageId(uploadFile.getId())
                .imagePath(uploadFile.getOriginFilePath())
                .build();
    }

    private ProductDetailRes getProductDetailRes(Product product) {
        if (!categoryRepository.existsById(product.getCategoryId())) {
            throw new LovelyException("Category not found", HttpStatus.BAD_REQUEST);
        }
        ProductDetailRes response = ProductDetailRes.builder()
                .id(product.getId())
                .name(product.getName())
                .price(product.getPrice())
                .quantity(product.getQuantity())
                .shortDescription(product.getShortDescription())
                .longDescription(product.getLongDescription())
                .imageId(product.getImageId())
                .categoryId(categoryRepository.findById(product.getCategoryId()).get().getId())
                .build();

        UploadFile uploadFile = uploadFileRepository.findById(product.getImageId()).orElse(null);
        if (uploadFile != null) {
            response.setThumbUrl(uploadFile.getThumbFilePath());
            response.setThumbName(uploadFile.getThumbFileName());
        }
        return response;
    }
}
