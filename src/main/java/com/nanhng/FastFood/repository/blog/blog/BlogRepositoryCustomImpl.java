package com.nanhng.FastFood.repository.blog.blog;

import com.nanhng.FastFood.dto.response.blog.ListBlogRes;
import com.nanhng.FastFood.entity.blog.QBlog;
import com.nanhng.FastFood.entity.blog.QBlogContent;
import com.nanhng.FastFood.entity.upload_file.QUploadFile;
import com.nanhng.FastFood.repository.BaseRepository;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Projections;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.nanhng.FastFood.util.Constant.PAGE_SIZE;

public class BlogRepositoryCustomImpl extends BaseRepository implements BlogRepositoryCustom {
    QBlog qblog = QBlog.blog;
    QBlogContent qblogContent = QBlogContent.blogContent;
    QUploadFile qUploadFile = QUploadFile.uploadFile;

    @Override
    public long countBlog(String keyword) {
        BooleanBuilder builder = new BooleanBuilder();
        if(keyword != null && keyword.isEmpty()) {
            builder.and(qblog.title.containsIgnoreCase(keyword));
        }
        builder.and(qblog.deleted.isFalse());

        Long count = query().from(qblog)
                .where(builder)
                .select(qblog.id.count())
                .fetchOne();
        return count == null ? 0 : count;
    }

    @Override
    public List<ListBlogRes> getListBlog(int page, String keyword) {
        BooleanBuilder builder = new BooleanBuilder();
        if(keyword != null && keyword.isEmpty()) {
            builder.and(qblog.title.containsIgnoreCase(keyword));
        }
        builder.and(qblog.deleted.isFalse());

        return query().from(qblog)
                .join(qUploadFile).on(qblog.thumbnailImageId.eq(qUploadFile.id))
                .where(builder)
                .select(Projections.fields(ListBlogRes.class,
                        qblog.title,
                        qblog.authorId,
                        qblog.introduction,
                        qUploadFile.as("thumbnail")
                ))
                .limit(PAGE_SIZE)
                .offset(page * PAGE_SIZE)
                .fetch();
    }

    @Override
    public List<Integer> getExistIds(List<Integer> ids) {

        BooleanBuilder builder = new BooleanBuilder();
        builder.and(qblog.deleted.eq(false));
        builder.and(qblog.id.in(ids));

        return query().from(qblog)
                .where(builder)
                .select(qblog.id)
                .fetch();
    }

    @Override
    @Transactional
    public void deleteByIds(List<Integer> ids) {
        BooleanBuilder builder = new BooleanBuilder();

        builder.and(qblog.deleted.eq(false));
        builder.and(qblog.id.in(ids));

        query().update(qblog)
                .where(builder)
                .set(qblog.deleted, true)
                .execute();
    }
}
