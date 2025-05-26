package com.nanhng.FastFood.service.repository.product;

import com.nanhng.FastFood.dto.constant.ActiveStatus;
import com.nanhng.FastFood.dto.response.product.ProductListRes;
import com.nanhng.FastFood.entity.product.QProduct;
import com.nanhng.FastFood.entity.upload_file.QUploadFile;
import com.nanhng.FastFood.service.repository.BaseRepository;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

import static com.nanhng.FastFood.util.Constant.PAGE_SIZE;

public class ProductRepositoryCustomImpl extends BaseRepository implements ProductRepositoryCustom {

    QProduct qProduct = QProduct.product;
    QUploadFile qUploadFile = QUploadFile.uploadFile;
    @PersistenceContext
    EntityManager entityManager;

    @Override
    public List<ProductListRes> getAllProduct(int page, String keyword, ActiveStatus status) {

        JPAQueryFactory query = new JPAQueryFactory(entityManager);
        BooleanBuilder builder = new BooleanBuilder();
        builder.and(qProduct.deleted.eq(false));
        builder.and(qProduct.status.eq(Objects.requireNonNullElse(status, ActiveStatus.ACTIVE)));
        if(keyword!=null &&!keyword.isBlank()){
            builder.andAnyOf(qProduct.name.containsIgnoreCase(keyword));
        }
        return query.from(qProduct).leftJoin(qUploadFile).on(qProduct.imageId.eq(qUploadFile.id))
                .where(builder)
                .select(Projections.fields(ProductListRes.class,
                        qProduct.id,
                        qProduct.name,
                        qProduct.price,
                        qProduct.category.name.as("categoryName"),
                        qUploadFile.thumbFilePath.as("thumbUrl"),
                        qUploadFile.thumbFileName.as("thumbName")
                        ))
                .offset(page*PAGE_SIZE)
                .limit(PAGE_SIZE)
                .fetch();
    }

    @Override
    public long totalRecord(String keyword, ActiveStatus status) {
        BooleanBuilder builder = new BooleanBuilder();
        builder.and(qProduct.deleted.eq(false));
        builder.and(qProduct.status.eq(Objects.requireNonNullElse(status, ActiveStatus.ACTIVE)));
        if(keyword!=null &&!keyword.isBlank()){
            builder.andAnyOf(qProduct.name.containsIgnoreCase(keyword));
        }

        Long res = query().from(qProduct).leftJoin(qUploadFile).on(qProduct.imageId.eq(qUploadFile.id))
                .where(builder)
                .select(qProduct.id.count())
                .fetchFirst();
        return res==null?0:res;
    }

    @Override
    public List<Integer> getExistIds(List<Integer> ids) {

        JPAQueryFactory query = new JPAQueryFactory(entityManager);
        BooleanBuilder builder = new BooleanBuilder();
        builder.and(qProduct.deleted.eq(false));
        builder.and(qProduct.id.in(ids));

        return query.from(qProduct)
                .where(builder)
                .select(qProduct.id)
                .fetch();
    }

    @Override
    @Transactional
    public void deleteByIds(List<Integer> ids) {

        JPAQueryFactory query = new JPAQueryFactory(entityManager);
        BooleanBuilder builder = new BooleanBuilder();

        builder.and(qProduct.deleted.eq(false));
        builder.and(qProduct.id.in(ids));

        query.update(qProduct)
                .where(builder)
                .set(qProduct.deleted,true)
                .execute();
    }

    @Override
    public long countAllProductByCategory(int categoryId) {
        BooleanBuilder builder = new BooleanBuilder();
        builder.and(qProduct.deleted.eq(false));
        builder.and(qProduct.status.eq(ActiveStatus.ACTIVE));
        builder.and(qProduct.category.id.eq(categoryId));

        Long count = query().from(qProduct).leftJoin(qUploadFile).on(qProduct.imageId.eq(qUploadFile.id))
                .where(builder)
                .select(qProduct.id.count())
                .fetchOne();
        return count==null?0:count;
    }

    @Override
    public List<ProductListRes> getAllProductByCategory(int categoryId, int page) {
        JPAQueryFactory query = new JPAQueryFactory(entityManager);
        BooleanBuilder builder = new BooleanBuilder();
        builder.and(qProduct.deleted.eq(false));
        builder.and(qProduct.status.eq(ActiveStatus.ACTIVE));
        builder.and(qProduct.category.id.eq(categoryId));

        return query.from(qProduct).leftJoin(qUploadFile).on(qProduct.imageId.eq(qUploadFile.id))
                .where(builder)
                .select(Projections.fields(ProductListRes.class,
                        qProduct.id,
                        qProduct.name,
                        qProduct.price,
                        qProduct.category.name.as("categoryName"),
                        qUploadFile.thumbFilePath.as("thumbUrl"),
                        qUploadFile.thumbFileName.as("thumbName")
                ))
                .fetch();
    }

    @Override
    public Boolean existByName(String name) {
        BooleanBuilder builder = new BooleanBuilder();
        builder.and(qProduct.deleted.eq(false));
        builder.and(qProduct.name.eq(name));

        return query().from(qProduct)
                .where(builder)
                .select(qProduct.id)
                .fetchOne()!=null;
    }

    @Override
    public Boolean existById(Integer id) {
        BooleanBuilder builder = new BooleanBuilder();
        builder.and(qProduct.deleted.eq(false));
        builder.and(qProduct.id.eq(id));

        return query().from(qProduct)
                .where(builder)
                .select(qProduct.id)
                .fetchOne()!=null;
    }
}
