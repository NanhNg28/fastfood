package com.nanhng.FastFood.repository.category;

import com.nanhng.FastFood.dto.constant.ActiveStatus;
import com.nanhng.FastFood.dto.response.category.CategoryListRes;
import com.nanhng.FastFood.entity.category.QCategory;
import com.nanhng.FastFood.entity.upload_file.QUploadFile;
import com.nanhng.FastFood.repository.BaseRepository;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

import java.util.List;
import java.util.Objects;

import static com.nanhng.FastFood.util.Constant.PAGE_SIZE;

public class CategoryRepositoryCustomImpl extends BaseRepository implements CategoryRepositoryCustom {
    QCategory qCategory = QCategory.category;
    QUploadFile qUploadFile = QUploadFile.uploadFile;
    @PersistenceContext
    EntityManager entityManager;

    @Override
    @Transactional
    public void deleteByIds(List<Integer> ids) {
        BooleanBuilder builder = new BooleanBuilder();

        builder.and(qCategory.deleted.eq(false));
        builder.and(qCategory.id.in(ids));

        query().update(qCategory)
                .where(builder)
                .set(qCategory.deleted, true)
                .execute();
    }

    @Override
    public List<CategoryListRes> findAll(int page, String keyword, ActiveStatus status) {
        JPAQueryFactory query = new JPAQueryFactory(entityManager);
        BooleanBuilder builder = new BooleanBuilder();
        builder.and(qCategory.status.eq(Objects.requireNonNullElse(status, ActiveStatus.ACTIVE)));
        builder.and(qCategory.deleted.eq(false));
        if (keyword != null && !keyword.isBlank()) {
            builder.andAnyOf(qCategory.name.containsIgnoreCase(keyword));
        }
        return query.from(qCategory).leftJoin(qUploadFile).on(qCategory.imageId.eq(qUploadFile.id))
                .where(builder)
                .select(Projections.fields(CategoryListRes.class,
                        qCategory.id,
                        qCategory.name,
                        qCategory.description,
                        qUploadFile.thumbFilePath.as("thumbUrl"),
                        qUploadFile.thumbFileName.as("thumbName")
                ))
                .fetch();
    }

    @Override
    public List<Integer> getExistIds(List<Integer> ids) {
        JPAQueryFactory query = new JPAQueryFactory(entityManager);
        BooleanBuilder builder = new BooleanBuilder();
        builder.and(qCategory.deleted.eq(false));
        builder.and(qCategory.id.in(ids));

        return query.from(qCategory).leftJoin(qUploadFile).on(qCategory.imageId.eq(qUploadFile.id))
                .where(builder)
                .select(qCategory.id)
                .fetch();
    }

    @Override
    public long countRecord(String keyword, ActiveStatus status) {
        BooleanBuilder builder = new BooleanBuilder();
        builder.and(qCategory.deleted.eq(false));
        builder.and(qCategory.status.eq(Objects.requireNonNullElse(status, ActiveStatus.ACTIVE)));
        if (keyword != null && !keyword.isBlank()) {
            builder.andAnyOf(qCategory.name.containsIgnoreCase(keyword));
        }

        Long res = query().from(qCategory)
                .where(builder)
                .select(qCategory.id.count())
                .fetchFirst();
        return res == null ? 0 : res;
    }

    @Override
    public Boolean existById(Integer id) {
        BooleanBuilder builder = new BooleanBuilder();
        builder.and(qCategory.deleted.eq(false));
        builder.and(qCategory.id.eq(id));

        return query().from(qCategory)
                .where(builder)
                .select(qCategory.id)
                .fetchOne() != null;
    }
}
