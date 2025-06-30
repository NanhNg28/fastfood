package com.nanhng.FastFood.repository.blog.blog_content;

import com.nanhng.FastFood.entity.blog.BlogContent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BlogContentRepository extends JpaRepository<BlogContent, Integer>, BlogContentRepositoryCustom {

    List<BlogContent> findByBlogId(Integer blogId);
}
