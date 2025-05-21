package com.nanhng.FastFood.repository.product;

import com.nanhng.FastFood.dto.constant.ActiveStatus;
import com.nanhng.FastFood.dto.response.product.ProductRes;
import com.nanhng.FastFood.entity.product.QProduct;
import com.nanhng.FastFood.repository.BaseRepository;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

import java.util.List;
import java.util.Objects;

import static com.nanhng.FastFood.util.Constant.PAGE_SIZE;

public class ProductRepositoryCustomImpl extends BaseRepository implements ProductRepositoryCustom {

    QProduct qProduct = QProduct.product;
    @PersistenceContext
    EntityManager entityManager;

    @Override
    public List<ProductRes> getAllProduct(int page,String keyword, ActiveStatus status) {

        JPAQueryFactory query = new JPAQueryFactory(entityManager);
        BooleanBuilder builder = new BooleanBuilder();
        builder.and(qProduct.deleted.eq(false));
        builder.and(qProduct.status.eq(Objects.requireNonNullElse(status, ActiveStatus.ACTIVE)));
        if(keyword!=null &&!keyword.isBlank()){
            builder.andAnyOf(qProduct.name.containsIgnoreCase(keyword));
        }
        return query.from(qProduct)
                .where(builder)
                .select(Projections.fields(ProductRes.class,
                        qProduct.name,
                        qProduct.price,
                        qProduct.category.name.as("categoryName")))
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

        Long res = query().from(qProduct)
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
    public void deleteByIds(List<Integer> ids) {

        JPAQueryFactory query = new JPAQueryFactory(entityManager);
        BooleanBuilder builder = new BooleanBuilder();

        builder.and(qProduct.deleted.eq(false));

        query.update(qProduct)
                .where(builder)
                .set(qProduct.deleted,true)
                .execute();
    }

    @Override
    public List<ProductRes> getAllProductByCategory(int categoryId, int page) {
        JPAQueryFactory query = new JPAQueryFactory(entityManager);
        BooleanBuilder builder = new BooleanBuilder();
        builder.and(qProduct.deleted.eq(false));
        builder.and(qProduct.status.eq(ActiveStatus.ACTIVE));
        builder.and(qProduct.category.id.eq(categoryId));

        return query.from(qProduct)
                .where(builder)
                .select(Projections.fields(ProductRes.class,
                        qProduct.name,
                        qProduct.price,
                        qProduct.category.name.as("categoryName")))
                .fetch();
    }
}
