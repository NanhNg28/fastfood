package com.nanhng.FastFood.repository.blog.blog;

import com.nanhng.FastFood.entity.blog.Blog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BlogRepository extends JpaRepository<Blog, Integer>, BlogRepositoryCustom {
}
