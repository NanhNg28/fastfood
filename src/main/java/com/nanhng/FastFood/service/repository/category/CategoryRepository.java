package com.nanhng.FastFood.service.repository.category;

import com.nanhng.FastFood.entity.category.Category;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer>, CategoryRepositoryCustom {
    boolean existsByName(@NotNull String name);

    Iterable<Integer> id(int id);

}
