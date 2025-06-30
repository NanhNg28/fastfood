package com.nanhng.FastFood.repository.blog.blog_content;

import com.nanhng.FastFood.entity.blog.BlogContent;

public interface BlogContentRepositoryCustom {
    BlogContent findByIdToUpdate(Integer id);
}
