package com.nanhng.FastFood.repository.blog.blog_content;

import com.nanhng.FastFood.entity.blog.BlogContent;
import com.nanhng.FastFood.entity.blog.QBlogContent;
import com.nanhng.FastFood.repository.BaseRepository;
import com.querydsl.core.BooleanBuilder;

public class BlogContentRepositoryCustomImpl extends BaseRepository implements BlogContentRepositoryCustom {
    QBlogContent qBlogContent = QBlogContent.blogContent;

    @Override
    public BlogContent findByIdToUpdate(Integer id) {
        BooleanBuilder builder = new BooleanBuilder();
        builder.and(qBlogContent.id.eq(id));
        builder.and(qBlogContent.deleted.isFalse());

        return query().from(qBlogContent)
                .where(builder)
                .select(qBlogContent)
                .fetchOne();
    }
}
