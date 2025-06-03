package com.nanhng.FastFood.repository.banner;

import com.nanhng.FastFood.dto.constant.ActiveStatus;
import com.nanhng.FastFood.dto.response.category.CategoryListRes;
import com.nanhng.FastFood.entity.banner.Banner;
import com.nanhng.FastFood.entity.banner.QBanner;
import com.nanhng.FastFood.repository.BaseRepository;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.transaction.Transactional;

import java.util.List;
import java.util.Objects;

import static com.nanhng.FastFood.util.Constant.PAGE_SIZE;

public class BannerRepositoryCustomImpl extends BaseRepository implements BannerRepositoryCustom {
    QBanner qBanner = QBanner.banner;

    @Override
    public Banner findByIdToUpdate(Integer bannerId) {
        BooleanBuilder builder = new BooleanBuilder();
        builder.and(qBanner.id.eq(bannerId));
        builder.and(qBanner.deleted.eq(false));

        return query().from(qBanner)
                .where(builder)
                .select(qBanner)
                .fetchOne();
    }

    @Override
    public List<Integer> getExistIds(List<Integer> ids) {
        BooleanBuilder builder = new BooleanBuilder();
        builder.and(qBanner.deleted.eq(false));
        builder.and(qBanner.id.in(ids));

        return query().from(qBanner)
                .where(builder)
                .select(qBanner.id)
                .fetch();
    }

    @Override
    @Transactional
    public void deleteByIds(List<Integer> ids) {
        BooleanBuilder builder = new BooleanBuilder();

        builder.and(qBanner.deleted.eq(false));
        builder.and(qBanner.id.in(ids));

        query().update(qBanner)
                .where(builder)
                .set(qBanner.deleted, true)
                .execute();
    }

    @Override
    public long countRecord() {
        BooleanBuilder builder = new BooleanBuilder();
        builder.and(qBanner.deleted.eq(false));

        Long res = query().from(qBanner)
                .where(builder)
                .select(qBanner.id.count())
                .fetchFirst();
        return res == null ? 0 : res;
    }

    @Override
    public List<Banner> getList(int page) {
        BooleanBuilder builder = new BooleanBuilder();
        builder.and(qBanner.deleted.eq(false));

        return query().from(qBanner)
                .where(builder)
                .select(qBanner)
                .offset(page*PAGE_SIZE)
                .limit(PAGE_SIZE)
                .fetch();
    }
}
